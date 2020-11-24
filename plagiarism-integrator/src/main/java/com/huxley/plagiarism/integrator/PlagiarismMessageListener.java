package com.huxley.plagiarism.integrator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlagiarismMessageListener implements MessageListener {
	
	private static Logger logger = LoggerFactory.getLogger(PlagiarismMessageListener.class);
	
	@Autowired
	private JsonMessageConverter converter;
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		logger.info("Receiving plagiarism suspect message...");
		Map<String, Object> map = (Map<String, Object>) this.converter.fromMessage(message);
		
		String[] sourceSplit = map.get("source_submission").toString().split("/");
		String[] targetSplit = map.get("target_submission").toString().split("/");
		
		Long sourceSubmissionId = Long.valueOf(sourceSplit[sourceSplit.length - 1]);
		Long targetSubmissionId = Long.valueOf(targetSplit[targetSplit.length - 1]);
		BigDecimal similarity = new BigDecimal(map.get("similarity").toString());
		
		if (similarity.doubleValue() >= 0.8d) {
			Map<String, Object> resultMap = new HashMap<>();
			
			resultMap.put("source_submission", sourceSubmissionId);
			resultMap.put("target_submission", targetSubmissionId);
			resultMap.put("similarity", similarity);
			
			this.amqpTemplate.convertAndSend("plagiarism_suspect_queue", resultMap, new MessagePostProcessor() {
	
			    @Override
			    public Message postProcessMessage(Message message) throws AmqpException {
			        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
			        return message;
			    }
	
			});
		}
		logger.info("Done.");
	}	
	
}