package com.huxley.plagiarism.queue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huxley.plagiarism.dao.ComparisonDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.ComparisonStatus;

@Component
public class SimilarityResponseQueueMessageListener implements MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(SimilarityResponseQueueMessageListener.class);
		
	@Autowired
	private JsonMessageConverter converter;
	
	@Autowired
	private ComparisonDao comparisonDao;
		
	@Autowired
	private AmqpTemplate amqpTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		Map<String, Object> map = (Map<String, Object>) this.converter.fromMessage(message);
		
		logger.debug("Receiving message from similarity.response queue. " + map);
		
		Long comparisonId = new Long(map.get("id").toString());
		BigDecimal value = new BigDecimal((String) map.get("value"));
		
		Comparison comparison = this.comparisonDao.get(comparisonId);
		
		if (comparison != null) {
			comparison.setSimilarity(value);
			comparison.setProcessingDate(new Date());
			comparison.setComparisonStatus(ComparisonStatus.OK);			
			logger.debug("Comparison received from similarity.response queue. comparison.id = " + comparison.getId());
			
			Map<String, Object> huxleyMap = new HashMap<>();
			huxleyMap.put("source_submission", comparison.getSourceSubmission().getPrivateId());
			huxleyMap.put("target_submission", comparison.getTargetSubmission().getPrivateId());
			huxleyMap.put("similarity", comparison.getSimilarity());
			
			this.amqpTemplate.convertAndSend("plagiarism_exchange", comparison.getSourceSubmission().getGroup().getUser().getUsername(), huxleyMap);
			
			logger.debug("Sending plagiarism suspect to plagiarism.warning.queue. comparison.id = " + comparison.getId());
		} else {
			logger.debug("Comparison not found. comparison.id = " + comparisonId);			
		}
		
	}

}
