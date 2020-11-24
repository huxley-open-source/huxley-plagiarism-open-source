package com.huxley.plagiarism.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.huxley.plagiarism.web.api.dto.ComparisonDTO;

@Entity
@Table(name = "comparisons")
public class Comparison implements Serializable {
	
	private static final long serialVersionUID = 2828635751409909936L;

	@Id
	@SequenceGenerator(name = "sequence-generator", schema = "plagiarism", sequenceName = "seq_comparisons", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "target_submission_id")
	private Submission targetSubmission;
	
	@ManyToOne
	@JoinColumn(name = "source_submission_id")
	private Submission sourceSubmission;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ComparisonStatus comparisonStatus = ComparisonStatus.WAITING;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "processing_date")
	private Date processingDate;
	
	@Column(name = "similarity")
	private BigDecimal similarity;
	
	@Column(name = "plagiarism")
	private Boolean plagiarism;
	
	public Comparison() {
		super();
	}
	
	public Comparison(Long id) {
		super();
		this.id = id;
	}

	public Comparison(Submission sourceSubmission, Submission targetSubmission) {
		super();
		this.sourceSubmission = sourceSubmission;
		this.targetSubmission = targetSubmission;
		this.creationDate = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((sourceSubmission == null) ? 0 : sourceSubmission.hashCode());
		result = prime
				* result
				+ ((targetSubmission == null) ? 0 : targetSubmission.hashCode());
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
		Comparison other = (Comparison) obj;
		if (sourceSubmission == null) {
			if (other.sourceSubmission != null)
				return false;
		} else if (!sourceSubmission.equals(other.sourceSubmission))
			return false;
		if (targetSubmission == null) {
			if (other.targetSubmission != null)
				return false;
		} else if (!targetSubmission.equals(other.targetSubmission))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comparison [id=" + id + "]";
	}
	
	public static ComparisonDTO convertToDTO(Comparison comparison) {
		ComparisonDTO comparisonDTO = new ComparisonDTO();
		comparisonDTO.setId(comparison.getId());
		comparisonDTO.setSourceSubmission(Submission.convertToDTO(comparison.getSourceSubmission()));	
		comparisonDTO.setTargetSubmission(Submission.convertToDTO(comparison.getTargetSubmission()));
		comparisonDTO.setSimilarity(comparison.getSimilarity());
		comparisonDTO.setStatus(comparison.getComparisonStatus());
		comparisonDTO.setCreationDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(comparison.getCreationDate()));
		if (comparison.getProcessingDate() != null) {
			comparisonDTO.setProcessingDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(comparison.getProcessingDate()));			
		}
		comparisonDTO.setPlagiarism(comparison.getPlagiarism());
		
		return comparisonDTO;
	}
	
	public static List<ComparisonDTO> convertToDTO(List<Comparison> comparisons) {
		List<ComparisonDTO> comparisonDTOList = new ArrayList<>();
		
		for (Comparison comparison : comparisons) {
			comparisonDTOList.add(Comparison.convertToDTO(comparison));
		}
		
		return comparisonDTOList;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Submission getTargetSubmission() {
		return targetSubmission;
	}

	public void setTargetSubmission(Submission targetSubmission) {
		this.targetSubmission = targetSubmission;
	}

	public Submission getSourceSubmission() {
		return sourceSubmission;
	}

	public void setSourceSubmission(Submission sourceSubmission) {
		this.sourceSubmission = sourceSubmission;
	}

	public ComparisonStatus getComparisonStatus() {
		return comparisonStatus;
	}

	public void setComparisonStatus(ComparisonStatus comparisonStatus) {
		this.comparisonStatus = comparisonStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public BigDecimal getSimilarity() {
		return similarity;
	}

	public void setSimilarity(BigDecimal similarity) {
		this.similarity = similarity;
	}

	public Boolean getPlagiarism() {
		return plagiarism;
	}

	public void setPlagiarism(Boolean plagiarism) {
		this.plagiarism = plagiarism;
	}

}
