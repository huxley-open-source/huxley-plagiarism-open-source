package com.huxley.plagiarism.dao;

import java.util.List;

import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.SubmissionStatus;
import com.huxley.plagiarism.domain.User;

public interface SubmissionDao extends GenericDao<Submission, Long> {
				
	Submission get(User user, Long id);
	
	List<Submission> list(User user, Integer offset, Integer maxResults);
	
	List<Submission> list(User user, Group group, Integer offset, Integer maxResults);
	
	List<Submission> list(User user, Submission submission, Integer offset, Integer maxResults);
	
	List<Submission> list(Group group, SubmissionStatus submissionStatus);
	
	List<Submission> list(Group group, SubmissionStatus submissionStatus, Integer offset, Integer maxResults);
	
	List<Submission> list(SubmissionStatus status, Integer offset, Integer maxResults);

}
