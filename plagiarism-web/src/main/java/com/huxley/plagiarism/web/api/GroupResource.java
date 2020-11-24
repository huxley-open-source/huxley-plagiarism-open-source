package com.huxley.plagiarism.web.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huxley.plagiarism.dao.GroupDao;
import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.User;
import com.huxley.plagiarism.web.api.dto.GroupDTO;
import com.huxley.plagiarism.web.api.dto.SubmissionDTO;

@Controller
@RequestMapping(value = "api/groups")
public class GroupResource implements Serializable {

	private static final long serialVersionUID = -8229273746066829672L;
	
	private static final Integer MAX_RESULTS = 30;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createGroup(Authentication authentication, 
			@RequestBody GroupDTO groupDTO) {
		
		User user = this.userDao.get(authentication.getName());
		
		Group parentGroup = null;
		
		if (groupDTO.getParentGroup() != null && groupDTO.getParentGroup().getId() != null) {
			parentGroup = new Group(groupDTO.getParentGroup().getId());
		}
		
		Group group = new Group(user, groupDTO.getPrivateId(), groupDTO.getName(), parentGroup);
		
		if (!this.groupDao.list(user, group, 0, 1).isEmpty()) {
			return new ResponseEntity<GroupDTO>(Group.convertoToDTO(group), HttpStatus.CONFLICT);
		}

		this.groupDao.save(group);
		
		return new ResponseEntity<GroupDTO>(new GroupDTO(group.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(Authentication authentication, 
			@PathVariable("id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		Group group = this.groupDao.get(user, id);

		if (group == null) {
			new ResponseEntity<String>("Group not found.", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<GroupDTO>(Group.convertoToDTO(group), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list(Authentication authentication, 
			@RequestParam(value = "id", required = false) Long id, 
			@RequestParam(value = "private_id", required = false) String privateId, 
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "parent_group_id", required = false) Long parentGroupId,
			@RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset) {
		
		User user = this.userDao.get(authentication.getName());
		
		List<Group> groups = null;
		
		if (id != null || privateId != null || name != null) {
			Group group = new Group();
			group.setId(id);
			group.setPrivateId(privateId);
			group.setName(name);
			
			groups = this.groupDao.list(user, group, offset, MAX_RESULTS);
			
			return new ResponseEntity<List<GroupDTO>>(Group.convertoToDTO(groups), HttpStatus.OK);
		}
		
		groups = this.groupDao.listWithNoParent(user, offset, MAX_RESULTS);
		
		return new ResponseEntity<List<GroupDTO>>(Group.convertoToDTO(groups), HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}/submissions", method = RequestMethod.GET)
	public ResponseEntity<?> getSubmissions(Authentication authentication, 
			@PathVariable(value = "id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		
		Group group = this.groupDao.get(user, id);
		
		if (group == null) {
			return new ResponseEntity<String>("Group not found.", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<SubmissionDTO>>(Submission.convertToDTO(new ArrayList<>(group.getSubmissions())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(Authentication authentication, 
			@PathVariable("id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		
		Group group = this.groupDao.get(user, id);
		
		if (group == null) {
			return new ResponseEntity<String>("Group not found.", HttpStatus.NOT_FOUND);
		}
		
		this.groupDao.delete(group);
		
		return new ResponseEntity<String>("Success.", HttpStatus.OK);
	}
	
}
