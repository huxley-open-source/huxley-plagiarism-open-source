package com.huxley.plagiarism.dao.hibernate;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.User;

@Repository
public class HibernateUserDao extends HibernateGenericDao<User, Long> implements UserDao {
	
	@Override
	public User get(String username) {
		return (User) super.getSession().createCriteria(super.getEntityClass())
			.add(Restrictions.eq("username", username))
			.uniqueResult();
	}

}
