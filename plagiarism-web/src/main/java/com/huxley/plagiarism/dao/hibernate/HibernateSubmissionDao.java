package com.huxley.plagiarism.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huxley.plagiarism.dao.SubmissionDao;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.SubmissionStatus;
import com.huxley.plagiarism.domain.User;

@Repository
public class HibernateSubmissionDao extends HibernateGenericDao<Submission, Long> implements SubmissionDao {
	
	@Override
	public Submission get(User user, Long id) {
		return (Submission) super.getSession().createCriteria(super.getEntityClass())
				.createAlias("group", "g")
				.add(Restrictions.eq("g.user", user))
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> list(SubmissionStatus submissionStatus, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
			.setFetchMode("group", FetchMode.SELECT)
			.add(Restrictions.eq("submissionStatus", submissionStatus))
			.addOrder(Order.asc("submissionDate"))
			.setFirstResult(offset)
			.setMaxResults(maxResults)
			.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> list(User user, Submission submission, Integer offset, Integer maxResults) {
		Example example = Example.create(submission).ignoreCase();
		
		Criteria criteria = super.getSession().createCriteria(super.getEntityClass())
				.add(example)
				.createAlias("group", "g")
				.add(Restrictions.eq("g.user", user))
				.addOrder(Order.asc("submissionDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults);
		
		if (submission.getGroup() != null) {
			criteria.add(Restrictions.eq("group", submission.getGroup()));
		}
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> list(User user, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
				.createAlias("group", "g")
				.add(Restrictions.eq("g.user", user))
				.addOrder(Order.asc("submissionDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> list(User user, Group group, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
				.createAlias("group", "g")
				.add(Restrictions.eq("group", group))
				.add(Restrictions.eq("g.user", user))
				.addOrder(Order.asc("submissionDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> list(Group group, SubmissionStatus submissionStatus) {
		return super.getSession().createCriteria(super.getEntityClass())
				.add(Restrictions.eq("group", group))
				.add(Restrictions.eq("submissionStatus", submissionStatus))
				.addOrder(Order.asc("submissionDate"))
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> list(Group group, SubmissionStatus submissionStatus, Integer offset, Integer maxResults) {
		return super.getSession().createCriteria(super.getEntityClass())
				.add(Restrictions.eq("group", group))
				.add(Restrictions.eq("submissionStatus", submissionStatus))
				.addOrder(Order.asc("submissionDate"))
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}

}
