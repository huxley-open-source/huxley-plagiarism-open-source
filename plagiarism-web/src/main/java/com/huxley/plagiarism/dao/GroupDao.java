package com.huxley.plagiarism.dao;

import java.util.List;

import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.User;

public interface GroupDao extends GenericDao<Group, Long> {
	
	Group get(User user, Long id);
	
	List<Group> list(User user, Group group, Integer offset, Integer maxResults);
	
	List<Group> listWithNoParent(User user, Integer offset, Integer maxResults);
	
	Long count(User user, Group group);
	
	Long countWithNoParent(User user);
	
}
