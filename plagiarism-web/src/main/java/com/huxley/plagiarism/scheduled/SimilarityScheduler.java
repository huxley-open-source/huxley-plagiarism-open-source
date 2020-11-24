package com.huxley.plagiarism.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huxley.plagiarism.dao.ComparisonDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.ComparisonStatus;
import com.huxley.plagiarism.queue.SimilarityQueueSender;

@Component
public class SimilarityScheduler {
	
	private static Logger logger = LoggerFactory.getLogger(SimilarityScheduler.class);

	@Autowired	
	private SimilarityQueueSender sender;
	
	@Autowired
	private ComparisonDao comparisonDao;
	
	@Scheduled(fixedDelay = 1000)
	public void execute() {
		logger.debug("Starting to send comparisons to similarity queue. ");
		
		for (Comparison comparison : this.comparisonDao.list(ComparisonStatus.WAITING, 0, 1000)) {
			logger.debug("Updating comparison status. comparison.id = " + comparison.getId());

			comparison.setComparisonStatus(ComparisonStatus.PROCESSING);
			
			this.sender.send(comparison);
		}
		
		logger.debug("Finish sending comparisons to similarity queue. ");
	}
	
}
