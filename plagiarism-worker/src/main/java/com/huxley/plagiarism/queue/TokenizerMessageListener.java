package com.huxley.plagiarism.queue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.huxley.plagiarism.tokenizer.Token;
import com.huxley.plagiarism.tokenizer.TokenBuilder;

public class TokenizerMessageListener implements MessageListener {
	
	private static Logger logger = LoggerFactory.getLogger(TokenizerMessageListener.class);

	private JsonMessageConverter converter;
	
	private AmqpTemplate amqpTemplate;
	
	private TokenBuilder tokenBuilder;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		Map<String, Object> map = (Map<String, Object>) this.converter.fromMessage(message);
		
		Long id = Long.valueOf(map.get("id").toString());
		String language = (String) map.get("language");
		String fileContent = (String) map.get("file_content");
		
		logger.info("Tokenizing submissions. submission.id = " + id);
		
		List<String> tokens = new ArrayList<>();
		try {
			for (Token token : this.tokenBuilder.generateTokens(fileContent, language)) {
				tokens.add(token.getValue());
			}
		} catch (Exception e) {
			tokens.clear();
			tokens.add("PARSE_ERROR");
		}
		
		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("id", id);
		mapResult.put("processing_date", new Date());
		mapResult.put("tokens", tokens.toString().replaceAll("\\[|\\]", ""));		

		this.amqpTemplate.convertAndSend("tokenizer.response", mapResult, new MessagePostProcessor() {

		    @Override
		    public Message postProcessMessage(Message message) throws AmqpException {
		        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		        return message;
		    }

		});
	}

	public void setConverter(JsonMessageConverter converter) {
		this.converter = converter;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public void setTokenBuilder(TokenBuilder tokenBuilder) {
		this.tokenBuilder = tokenBuilder;
	}

}
