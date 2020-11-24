package com.huxley.plagiarism.queue;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huxley.plagiarism.dao.SubmissionDao;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.SubmissionStatus;

@Component
public class TokenizerResponseQueueMessageListener implements MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenizerResponseQueueMessageListener.class);
	
	@Autowired
	private JsonMessageConverter converter;
	
	@Autowired
	private ComparisonQueueSender comparisonSender;
	
	@Autowired	
	private SubmissionDao submissionDao;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		Map<String, Object> map = (Map<String, Object>) this.converter.fromMessage(message);

		Long submissionId = Long.valueOf(map.get("id").toString());
		String tokens = (String) map.get("tokens");
		
		logger.debug("Receiving message from tokenizer.response queue. submission.id = " + submissionId);
		
		Submission submission = this.submissionDao.get(submissionId);
		
		if (submission != null) {
			submission.setProcessingDate(new Date());
			if ("PARSE_ERROR".equals(tokens)) {
				submission.setSubmissionStatus(SubmissionStatus.PARSE_ERROR);
			} else {
				submission.setTokens(tokens);
				submission.setSubmissionStatus(SubmissionStatus.OK);
				
				List<Submission> groupSubmissions = this.submissionDao.list(submission.getGroup(), SubmissionStatus.OK);
				groupSubmissions.remove(submission);
				
				for (Submission oldSubmission : groupSubmissions) {
					this.comparisonSender.send(submission.getId(), oldSubmission.getId());
				}
			}			
			logger.debug("Submission processed. submission.id = " + submissionId);
		} else {
			logger.error("Submission not found. submission.id = " + submissionId);			
		}
	}

}
