package com.huxley.plagiarism.web.api.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SubmissionDTO implements Serializable {
	
	private static final long serialVersionUID = -3111859682468372096L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("private_id")
	private String privateId;
	
	@JsonProperty("filename")
	private String filename;
	
	@JsonProperty("language")
	private String language;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("file_content")
	private String fileContent;
	
	@JsonProperty("submission_date")
	private String submissionDate;
	
	@JsonProperty("processing_date")
	private String processingDate;
	
	@JsonProperty("group")
	private GroupDTO group;

	public SubmissionDTO() {
		super();
	}

	public SubmissionDTO(Long id) {
		super();
		this.id = id;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(String processingDate) {
		this.processingDate = processingDate;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

}
