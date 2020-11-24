package com.huxley.plagiarism.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.huxley.plagiarism.web.api.dto.SubmissionDTO;

@Entity
@Table(name = "submissions")
public class Submission implements Serializable {

	private static final long serialVersionUID = 2985258000626780278L;
	
	@Id
	@SequenceGenerator(name = "sequence-generator", schema = "plagiarism", sequenceName = "seq_submissions", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "private_id")
	private String privateId;
	
	@Column(name = "original_filename")
	private String originalFilename;
	
	@Column(name = "filename")
	private String filename;
	
	@Column(name = "tokens")
	private String tokens;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private SubmissionStatus submissionStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "submission_date")
	private Date submissionDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "processing_date")
	private Date processingDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "language")
	private Language language;
	
	@Transient
	private InputStream inputStream;	
	
	@Transient
	private String fileContent;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@OneToMany(mappedBy = "sourceSubmission", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Comparison> sourceComparisons = new HashSet<>();
	
	@OneToMany(mappedBy = "targetSubmission", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Comparison> targetComparisons = new HashSet<>();

	public Submission() {
		super();
	}

	public Submission(Long id) {
		super();
		this.id = id;
	}
	
	public Submission(Long id, String privateId) {
		super();
		this.id = id;
		this.privateId = privateId;
	}
	
	public Submission(Group group, String privateId, String originalFilename, InputStream inputStream, Language language) {
		super();
		this.group = group;
		this.privateId = privateId;
		this.originalFilename = originalFilename;
		this.submissionStatus = SubmissionStatus.WAITING;
		this.language = language;
		this.inputStream = inputStream;
		this.submissionDate = new Date();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submission other = (Submission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Submission [id=" + id + "]";
	}

	public static SubmissionDTO convertToDTO(Submission submission) {
		SubmissionDTO submissionDTO = new SubmissionDTO();
		submissionDTO.setId(submission.getId());
		submissionDTO.setPrivateId(submission.getPrivateId());
		submissionDTO.setFilename(submission.getOriginalFilename());
		submissionDTO.setGroup(Group.convertoToDTO(submission.getGroup()));
		submissionDTO.setLanguage(submission.getLanguage().getName());
		submissionDTO.setStatus(submission.getSubmissionStatus().toString());
		submissionDTO.setFileContent(submission.getFileContent());
		submissionDTO.setSubmissionDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(submission.getSubmissionDate()));
		if (submission.getProcessingDate() != null) {
			submissionDTO.setProcessingDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(submission.getProcessingDate()));			
		}
		return submissionDTO;
	}
	
	public static List<SubmissionDTO> convertToDTO(List<Submission> submissions) {
		List<SubmissionDTO> submissionDTOList = new ArrayList<>();
		for (Submission submission : submissions) {					
			submissionDTOList.add(Submission.convertToDTO(submission));
		}
		return submissionDTOList;
	}
	
	private void createIfNotExists(String dirPath) {
		if (!new File(dirPath).exists()) {
			synchronized (this) {
				if (!new File(dirPath).exists()) {					
					if (!new File(dirPath).mkdir()) {
						throw new PlagiarismException("Cannot create directory. " + dirPath);
					}
				}
			}
		}	
	}

	private String generateFilename(String filePath) {
		String yearDirectory = String.valueOf(File.separatorChar) + Calendar.getInstance().get(Calendar.YEAR);
		String monthDirectory = yearDirectory + File.separatorChar + (Calendar.getInstance().get(Calendar.MONTH) + 1);
		String dayDirectory = monthDirectory + File.separatorChar + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		this.createIfNotExists(filePath + yearDirectory);
		this.createIfNotExists(filePath + monthDirectory);
		this.createIfNotExists(filePath + dayDirectory);
		
		do {
			String filename = dayDirectory + File.separatorChar + UUID.randomUUID().toString();
			if (!new File(filename).exists()) {
				return filename;
			}
		} while (true);
	}
	
	public void saveFile(String filePath) {
		String filename  = this.generateFilename(filePath);
		
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(filePath + filename);
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = this.inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			this.filename = filename;
		} catch (IOException e) {
			throw new PlagiarismException("Cannot save the file.", e);
		} finally {
			if (outputStream != null) {
				try { outputStream.close(); } catch (IOException e) { }
			}
		}
	}
	
	public void readFile(String path) {
		try {
			Charset nioCharset = StandardCharsets.UTF_8;
			byte[] encoded = Files.readAllBytes(Paths.get(path + this.filename));
			String asd = nioCharset.decode(ByteBuffer.wrap(encoded)).toString();

			this.fileContent = new String(asd.getBytes(StandardCharsets.UTF_8.displayName())).replaceAll("\u0000", "");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrivateId() {
		return privateId;
	}

	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public SubmissionStatus getSubmissionStatus() {
		return submissionStatus;
	}

	public void setSubmissionStatus(SubmissionStatus submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Set<Comparison> getComparisons() {
		Set<Comparison> comparisons = new HashSet<>();
		comparisons.addAll(this.sourceComparisons);
		comparisons.addAll(this.targetComparisons);
		return comparisons;
	}

}
