package com.huxley.plagiarism.queue;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComparisonQueueSender {
	
	private static final Logger logger = LoggerFactory.getLogger(ComparisonQueueSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Long sourceSubmissionId, Long targetSubmissionId) {
		logger.debug("Sending comparison creation to queue.");
		Map<String, Object> map = new HashMap<>();
		
		map.put("source_submission_id", sourceSubmissionId);
		map.put("target_submission_id", targetSubmissionId);

		this.amqpTemplate.convertAndSend("plagiarism.exchange", "comparison", map, new MessagePostProcessor() {

		    @Override
		    public Message postProcessMessage(Message message) throws AmqpException {
		        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		        return message;
		    }

		});
		
		logger.debug("Comparison sent to creation queue.");
	}
	
}
