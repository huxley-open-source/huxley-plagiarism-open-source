package com.huxley.plagiarism.web.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.huxley.plagiarism.domain.ComparisonStatus;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ComparisonDTO implements Serializable {

	private static final long serialVersionUID = 2589246161120324231L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("source_submission")
	private SubmissionDTO sourceSubmission;
	
	@JsonProperty("target_submission")
	private SubmissionDTO targetSubmission;
	
	@JsonProperty("creation_date")
	private String creationDate;
	
	@JsonProperty("processing_date")
	private String processingDate;
	
	@JsonProperty("status")
	private ComparisonStatus status;
	
	@JsonProperty("similarity")
	private BigDecimal similarity;
	
	@JsonProperty("plagiarism")
	private Boolean plagiarism;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubmissionDTO getSourceSubmission() {
		return sourceSubmission;
	}

	public void setSourceSubmission(SubmissionDTO sourceSubmission) {
		this.sourceSubmission = sourceSubmission;
	}

	public SubmissionDTO getTargetSubmission() {
		return targetSubmission;
	}

	public void setTargetSubmission(SubmissionDTO targetSubmission) {
		this.targetSubmission = targetSubmission;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(String processingDate) {
		this.processingDate = processingDate;
	}

	public ComparisonStatus getStatus() {
		return status;
	}

	public void setStatus(ComparisonStatus status) {
		this.status = status;
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
