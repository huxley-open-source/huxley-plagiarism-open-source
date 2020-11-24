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

import com.huxley.plagiarism.web.api.dto.SubmissionDTO;

@Component
public class TokenizerQueueSender {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenizerQueueSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(SubmissionDTO submissionDTO) {
		logger.debug("Sending submission to tokenizer queue. submission.id=" + submissionDTO.getId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", submissionDTO.getId());
		map.put("file_content", submissionDTO.getFileContent());
		map.put("language", submissionDTO.getLanguage());
		
		this.amqpTemplate.convertAndSend("plagiarism.exchange", "tokenizer", map, new MessagePostProcessor() {

		    @Override
		    public Message postProcessMessage(Message message) throws AmqpException {
		        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		        return message;
		    }

		});
		
		logger.debug("Submission sent to tokenizer queue. submission.id=" + submissionDTO.getId());
	}
	
}
