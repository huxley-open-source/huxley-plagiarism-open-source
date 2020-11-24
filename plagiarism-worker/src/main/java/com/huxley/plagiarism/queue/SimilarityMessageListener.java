package com.huxley.plagiarism.queue;

import java.math.BigDecimal;
import java.util.Arrays;
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

import com.huxley.plagiarism.similarity.SimilarityDetectionStrategy;
import com.huxley.plagiarism.similarity.strategy.gst.GreedyStringTilingStrategy;
import com.huxley.plagiarism.similarity.strategy.jplag.JPlagSimilarityStrategy;
import com.huxley.plagiarism.similarity.strategy.moss.MossSimilarityStrategy;

public class SimilarityMessageListener implements MessageListener {
	
	private static Logger logger = LoggerFactory.getLogger(SimilarityMessageListener.class);

	private JsonMessageConverter converter;
	
	private AmqpTemplate amqpTemplate;
	
	private SimilarityDetectionStrategy similarityDetectionStrategy;
	
	private Integer granularity;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		Map<String, Object> map = (Map<String, Object>) this.converter.fromMessage(message);
		
		Long id = Long.valueOf(map.get("id").toString());
		String language = (String) map.get("language");
		String sourceTokens = (String) map.get("source_tokens");
		String targetTokens = (String) map.get("target_tokens");

		logger.info("Calculating similarity. comparison.id=" + id + "]");
		
		List<String> sourceTokenList = Arrays.asList(sourceTokens);
		List<String> targetTokenList = Arrays.asList(targetTokens);

		if (language.matches("(?i)(cpp|java)")) {
			this.similarityDetectionStrategy = new JPlagSimilarityStrategy();
			
			((JPlagSimilarityStrategy) this.similarityDetectionStrategy).setLanguage(language);
			
		} else if (language.matches("(?i)(c)")) {
			this.similarityDetectionStrategy = new GreedyStringTilingStrategy();
			
			sourceTokenList = Arrays.asList(sourceTokens.split(", "));
			targetTokenList = Arrays.asList(targetTokens.split(", "));
		} else {
			this.similarityDetectionStrategy = new GreedyStringTilingStrategy();
			
			sourceTokenList = Arrays.asList(sourceTokens.split(" "));
			targetTokenList = Arrays.asList(targetTokens.split(" "));
		}
		
		BigDecimal value = this.similarityDetectionStrategy.score(sourceTokenList, targetTokenList, this.granularity);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("id", id);
		resultMap.put("value", value.toString());
		resultMap.put("processing_date", new Date());
		
		this.amqpTemplate.convertAndSend("similarity.response", resultMap, new MessagePostProcessor() {

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

	public void setSimilarityDetectionStrategy(SimilarityDetectionStrategy similarityDetectionStrategy) {
		this.similarityDetectionStrategy = similarityDetectionStrategy;
	}

	public void setGranularity(Integer granularity) {
		this.granularity = granularity;
	}
	
}
