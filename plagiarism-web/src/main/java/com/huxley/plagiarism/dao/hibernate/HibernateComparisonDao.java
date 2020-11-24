package com.huxley.plagiarism.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huxley.plagiarism.dao.ComparisonDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.ComparisonStatus;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.User;

@Repository
public class HibernateComparisonDao extends HibernateGenericDao<Comparison, Long> implements ComparisonDao {

	@Override
	public Comparison get(User user, Long id) {
		return (Comparison) super.getSession().createCriteria(super.getEntityClass())
				.createAlias("sourceSubmission", "source")
				.createAlias("source.group", "group")
				.add(Restrictions.eq("group.user", user))
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comparison> list(ComparisonStatus comparisonStatus, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
				.setFetchMode("sourceSubmission", FetchMode.SELECT)
				.setFetchMode("targetSubmission", FetchMode.SELECT)
				.add(Restrictions.eq("comparisonStatus", comparisonStatus))
				.addOrder(Order.asc("creationDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comparison> list(User user, Comparison comparison, Integer offset, Integer maxResults) {
		Criteria criteria = super.getSession().createCriteria(super.getEntityClass())
				.createAlias("sourceSubmission", "source")
				.createAlias("source.group", "groupSource")
				.add(Restrictions.eq("groupSource.user", user))
				.addOrder(Order.asc("creationDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults);
		
		if (comparison.getSourceSubmission() != null) {
			criteria.add(Restrictions.eq("sourceSubmission", comparison.getSourceSubmission()));			
		}
		
		if (comparison.getTargetSubmission() != null) {
			criteria.add(Restrictions.eq("targetSubmission", comparison.getTargetSubmission()));			
		}
				
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comparison> list(Comparison comparison, Integer offset, Integer maxResults) {
		Criteria criteria = super.getSession().createCriteria(super.getEntityClass())
				.createAlias("sourceSubmission", "source")
				.createAlias("source.group", "groupSource")
				.addOrder(Order.asc("creationDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults);
		
		if (comparison.getSourceSubmission() != null) {
			criteria.add(Restrictions.eq("sourceSubmission", comparison.getSourceSubmission()));			
		}
		
		if (comparison.getTargetSubmission() != null) {
			criteria.add(Restrictions.eq("targetSubmission", comparison.getTargetSubmission()));			
		}
				
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comparison> list(User user, Group group, BigDecimal threshold, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
				.createAlias("sourceSubmission", "source")
				.createAlias("source.group", "groupSource")
				.add(Restrictions.eq("groupSource.user", user))
				.add(Restrictions.eq("source.group", group))
				.add(Restrictions.ge("similarity", threshold))
				.addOrder(Order.asc("creationDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}

}