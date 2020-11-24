package com.huxley.plagiarism.dao;

import com.huxley.plagiarism.domain.User;

public interface UserDao extends GenericDao<User, Long> {
	
	User get(String username);

}
