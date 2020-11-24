package com.huxley.plagiarism.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huxley.plagiarism.dao.SubmissionDao;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.SubmissionStatus;
import com.huxley.plagiarism.queue.TokenizerQueueSender;
import com.huxley.plagiarism.web.api.dto.SubmissionDTO;

@Component
public class TokenizerScheduler {
	
	private static Logger logger = LoggerFactory.getLogger(TokenizerScheduler.class);
		
	@Value("${data.dir}")
	private String filePath;
	
	@Autowired
	private TokenizerQueueSender sender;
	
	@Autowired
	private SubmissionDao submissionDao;
	
	@Scheduled(fixedDelay = 1000)
	public void execute() {
		logger.debug("Starting to send submissions to tokenize queue.");
		
		for (Submission submission : this.submissionDao.list(SubmissionStatus.WAITING, 0, 100)) {			
			logger.debug("Updating submission status. submission.id = " + submission.getId());
			
			submission.setSubmissionStatus(SubmissionStatus.PROCESSING);
			
			submission.readFile(this.filePath);
			
			SubmissionDTO submissionDTO = Submission.convertToDTO(submission);		
			
			this.sender.send(submissionDTO);
		}
		
		logger.debug("Finish sending submissions to tokenizing queue.");
	}
	
}
