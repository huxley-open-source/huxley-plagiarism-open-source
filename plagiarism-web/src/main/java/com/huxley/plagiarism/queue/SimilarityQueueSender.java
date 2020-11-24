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

import com.huxley.plagiarism.domain.Comparison;

@Component
public class SimilarityQueueSender {
	
	private static final Logger logger = LoggerFactory.getLogger(SimilarityQueueSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(Comparison comparison) {
		logger.debug("Sending comparison to similarity queue. comparison.id = " + comparison.getId());

		Map<String, Object> objects = new HashMap<>();			
		objects.put("id", comparison.getId());
		objects.put("language", comparison.getSourceSubmission().getLanguage());
		objects.put("source_tokens", comparison.getSourceSubmission().getTokens());
		objects.put("target_tokens", comparison.getTargetSubmission().getTokens());
		
		this.amqpTemplate.convertAndSend("plagiarism.exchange", "similarity", objects, new MessagePostProcessor() {

		    @Override
		    public Message postProcessMessage(Message message) throws AmqpException {
		        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		        return message;
		    }

		});
		
		logger.debug("Comparison sent to similarity queue. comparison.id = " + comparison.getId());
	}
	
}
