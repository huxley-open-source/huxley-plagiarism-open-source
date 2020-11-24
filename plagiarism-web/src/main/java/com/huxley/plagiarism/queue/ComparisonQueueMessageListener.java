package com.huxley.plagiarism.queue;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huxley.plagiarism.dao.ComparisonDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.Submission;

@Component
public class ComparisonQueueMessageListener implements MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ComparisonQueueMessageListener.class);
		
	@Autowired
	private JsonMessageConverter converter;
	
	@Autowired
	private ComparisonDao comparisonDao;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		Map<String, Object> map = (Map<String, Object>) this.converter.fromMessage(message);
		
		logger.debug("Receiving message from comparison queue. " + map);

		Long sourceSubmissionId = Long.valueOf(map.get("source_submission_id").toString());
		Long targetSubmissionId = Long.valueOf(map.get("target_submission_id").toString());
		
		Comparison comparison = new Comparison(new Submission(sourceSubmissionId), new Submission(targetSubmissionId));
		
		if (this.comparisonDao.list(comparison, 0, 1).size() == 0) {
			logger.debug("Creating new comparison. " + map);
			this.comparisonDao.save(comparison);			
			logger.debug("Comparison created. comparison.id = " + comparison.getId());
		} else {
			logger.error("Comparison already exists. " + map);			
		}
	}
	
}
