package com.huxley.plagiarism.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, Pk extends Serializable> {
	
	T get(Pk pk);
	
	void save(T entity);
	
	void delete(T entity);
	
	void update(T entity);
	
	List<T> list(Integer offset, Integer maxResults);
	
	List<T> list(String orderByColumn, boolean asc, Integer offset, Integer maxResults);
	
	List<T> list(T example, Integer offset, Integer maxResults);
	
	List<T> list(T example, String orderByColumn, boolean asc, Integer offset, Integer maxResults);
	
	Integer count();
	
	Integer count(T example);
	
}
