package com.huxley.plagiarism.integrator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huxley.plagiarism.integrator.database.HuxleyRepository;
import com.huxley.plagiarism.service.PlagiarismService;

@Component
public class SubmissionSender {
	
	private static final Logger logger = LoggerFactory.getLogger(SubmissionSender.class);
	
	@Autowired
	private HuxleyRepository huxleyRepository;
	
	@Autowired
	private PlagiarismService plagiarismService;
	
	@Scheduled(fixedDelay = 120000)
	public void send() {
		for (Map<String, Object> row : this.huxleyRepository.listWaiting(100)) {
			Long institutionId = (Long) row.get("institution_id");
			String institutionName = (String) row.get("institution_name");
			Long classId = (Long) row.get("class_id");
			String className = (String) row.get("class_name");
			Long surveyId = (Long) row.get("survey_id");
			String surveyName = (String) row.get("survey_name");
			Long problemId = (Long) row.get("problem_id");
			String problemName = (String) row.get("problem_name");
			Long languageId = (Long) row.get("language_id");
			String languageName = (String) row.get("language_name");
			Long submissionId = (Long) row.get("submission_id");
			String filename = (String) row.get("filename");
						
			Map<String, String> groupInformation = new LinkedHashMap<>();
			groupInformation.put("/" + institutionId, institutionName);
			groupInformation.put("/" + institutionId + "/"+ classId, className);
			groupInformation.put("/" + institutionId + "/"+ classId + "/" + surveyId, surveyName);
			groupInformation.put("/" + institutionId + "/"+ classId + "/" + surveyId + "/" + problemId, problemName);
			groupInformation.put("/" + institutionId + "/"+ classId + "/" + surveyId + "/" + problemId + "/" + languageId, languageName);
						
			logger.info("Sending submission. submission.id = " + submissionId);
			try {				
				this.plagiarismService.send(groupInformation, submissionId, filename, languageName);
				this.huxleyRepository.setSentToPlagiarismDetection(surveyId, submissionId, true);
				logger.info("Submission sent. submission.id = " + submissionId);
			} catch (Exception e) {
				this.huxleyRepository.setSentToPlagiarismDetection(surveyId, submissionId, false);
				logger.error("Não foi possível enviar a submissão.", e);
			}
		}
	}

}
