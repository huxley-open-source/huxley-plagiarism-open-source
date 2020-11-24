package com.huxley.plagiarism.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huxley.plagiarism.dao.GroupDao;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.User;

@Repository
public class HibernateGroupDao extends HibernateGenericDao<Group, Long> implements GroupDao {

	@Override
	public Group get(User user, Long id) {
		return (Group) super.getSession().createCriteria(super.getEntityClass())
			.add(Restrictions.eq("user", user))
			.add(Restrictions.eq("id", id))
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> list(User user, Group group, Integer offset, Integer maxResults) {
		Criteria criteria = super.getSession().createCriteria(super.getEntityClass())
				.add(Restrictions.eq("user", user))
				.add(Example.create(group))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.addOrder(Order.asc("name"));
		
		if (group.getParentGroup() != null) {
			criteria.add(Restrictions.eq("parentGroup", group.getParentGroup()));
		}
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> listWithNoParent(User user, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
				.add(Restrictions.eq("user", user))
				.add(Restrictions.isNull("parentGroup"))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.addOrder(Order.asc("name"))
				.list();
	}

	@Override
	public Long count(User user, Group group) {
		Criteria criteria = getSession().createCriteria(getEntityClass())
			.setProjection(Projections.rowCount())
			.add(Restrictions.eq("user", user));
		
		if (group.getParentGroup() != null) {
			criteria.add(Restrictions.eq("parentGroup", group.getParentGroup()));
		}
		
		return (Long) criteria.uniqueResult();
	}
	
	@Override
	public Long countWithNoParent(User user) {
		return (Long) getSession().createCriteria(getEntityClass())
				.setProjection(Projections.rowCount())
				.add(Restrictions.isNull("parentGroup"))
				.add(Restrictions.eq("user", user))
				.uniqueResult();
	}

}
