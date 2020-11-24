package com.huxley.plagiarism.web.api.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GroupDTO implements Serializable {

	private static final long serialVersionUID = 3425507434431210424L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("private_id")
	private String privateId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("total_of_submissions")
	private Integer totalOfSubmissions;
	
	@JsonProperty("parent_group")
	private GroupDTO parentGroup;
	
	public GroupDTO() {
		super();
	}

	public GroupDTO(Long id) {
		super();
		this.id = id;
	}
	
	public GroupDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalOfSubmissions() {
		return totalOfSubmissions;
	}

	public void setTotalOfSubmissions(Integer totalOfSubmissions) {
		this.totalOfSubmissions = totalOfSubmissions;
	}

	public GroupDTO getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(GroupDTO parentGroup) {
		this.parentGroup = parentGroup;
	}

}
