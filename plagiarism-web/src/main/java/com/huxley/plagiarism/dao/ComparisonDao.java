package com.huxley.plagiarism.dao;

import java.math.BigDecimal;
import java.util.List;

import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.ComparisonStatus;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.User;

public interface ComparisonDao extends GenericDao<Comparison, Long> {
		
	Comparison get(User user, Long id);
			
	List<Comparison> list(User user, Comparison comparison, Integer offset, Integer maxResults);
	
	List<Comparison> list(Comparison comparison, Integer offset, Integer maxResults);
	
	List<Comparison> list(ComparisonStatus status, Integer offset, Integer maxResults);
	
	List<Comparison> list(User user, Group group, BigDecimal threshold, Integer offset, Integer maxResults);

}
