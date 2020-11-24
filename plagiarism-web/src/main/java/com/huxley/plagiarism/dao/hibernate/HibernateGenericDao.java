package com.huxley.plagiarism.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.huxley.plagiarism.dao.GenericDao;

public abstract class HibernateGenericDao<T, Pk extends Serializable> implements GenericDao<T, Pk> {

	@Autowired
	private SessionFactory sessionFactory;
		
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(Pk pk) {
		return (T) this.getSession().get(this.getEntityClass(), pk);
	}
	
	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}
	
	@Override
	public void update(T entity) {
		this.getSession().update(entity);
	}
	
	@Override
	public void delete(T entity) {
		this.getSession().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return this.getSession().createCriteria(this.getEntityClass())
			.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(Integer offset, Integer maxResults) {
		return this.getSession().createCriteria(this.getEntityClass())
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(String orderByColumn, boolean asc, Integer offset, Integer maxResults) {
		Criteria criteria = this.getSession().createCriteria(this.getEntityClass())
				.setFirstResult(offset)
				.setMaxResults(maxResults);
		
		if (asc) {
			criteria.addOrder(Order.asc(orderByColumn));			
		} else {
			criteria.addOrder(Order.desc(orderByColumn));			
		}
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(T entity, Integer offset, Integer maxResults) {
		Example example = Example.create(entity).enableLike(MatchMode.ANYWHERE).ignoreCase();
		
		return this.getSession().createCriteria(this.getEntityClass())
				.add(example)
				.setFirstResult(offset)
				.setMaxResults(maxResults)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(T entity, String orderByColumn, boolean asc, Integer offset, Integer maxResults) {
		Example example = Example.create(entity).enableLike(MatchMode.ANYWHERE).ignoreCase();
		
		Criteria criteria = this.getSession().createCriteria(this.getEntityClass())
				.add(example)
				.setFirstResult(offset)
				.setMaxResults(maxResults);
		
		if (asc) {
			criteria.addOrder(Order.asc(orderByColumn));			
		} else {
			criteria.addOrder(Order.desc(orderByColumn));			
		}
		
		return criteria.list();
	}

	@Override
	public Integer count() {
		return (Integer) getSession().createCriteria(getEntityClass())
			.setProjection(Projections.rowCount())
			.uniqueResult();
	}

	@Override
	public Integer count(T entity) {
		Example example = Example.create(entity).enableLike(MatchMode.ANYWHERE).ignoreCase();

		return (Integer) getSession().createCriteria(getEntityClass())
			.setProjection(Projections.rowCount())
			.add(example)
			.uniqueResult();
	}
	
}
