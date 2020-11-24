package com.huxley.plagiarism.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.huxley.plagiarism.web.api.dto.GroupDTO;

@Entity
@Table(name = "groups")
public class Group implements Serializable {

	private static final long serialVersionUID = 1503396374523505218L;
	
	@Id
	@SequenceGenerator(name = "sequence-generator", schema = "plagiarism", sequenceName = "seq_groups", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "private_id")
	private String privateId;
	
	@Column(name = "name")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "user_id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "parent_group_id")
	private Group parentGroup;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "group_id")
	@OrderBy("submissionDate")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Submission> submissions = new ArrayList<>();

	public Group() {
		super();
	}

	public Group(Long id) {
		super();
		this.id = id;
	}
	
	public Group(User user, String privateId, String name, Group parentGroup) {
		super();
		this.user = user;
		this.privateId = privateId;
		this.name = name;
		this.creationDate = new Date();
		this.parentGroup = parentGroup;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + "]";
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static GroupDTO convertoToDTO(Group group) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getId());
		groupDTO.setPrivateId(group.getPrivateId());
		groupDTO.setName(group.getName());
		groupDTO.setTotalOfSubmissions(group.submissions.size());
		if (group.getParentGroup() != null) {
			GroupDTO parentGroupDTO = new GroupDTO(group.getParentGroup().getId(), group.getParentGroup().getName());
			groupDTO.setParentGroup(parentGroupDTO);
		}
		return groupDTO;
	}
	
	public static List<GroupDTO> convertoToDTO(List<Group> groups) {
		List<GroupDTO> groupDTOList = new ArrayList<>();
		
		for (Group group : groups) {			
			groupDTOList.add(Group.convertoToDTO(group));
		}
		
		return groupDTOList;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

}
