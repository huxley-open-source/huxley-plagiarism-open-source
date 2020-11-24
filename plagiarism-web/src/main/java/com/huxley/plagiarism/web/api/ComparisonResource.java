package com.huxley.plagiarism.web.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huxley.plagiarism.dao.ComparisonDao;
import com.huxley.plagiarism.dao.GroupDao;
import com.huxley.plagiarism.dao.SubmissionDao;
import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.User;
import com.huxley.plagiarism.web.api.dto.ComparisonDTO;

@Controller
@RequestMapping(value = "api/comparisons")
public class ComparisonResource implements Serializable {

	private static final long serialVersionUID = 8393943837023162468L;

	@Autowired
	private ComparisonDao comparisonDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private SubmissionDao submissionDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public  ResponseEntity<?> get(Authentication authentication, 
			@PathVariable(value = "id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		
		Comparison comparison = this.comparisonDao.get(user, id);
				
		if (comparison == null) {
			return new ResponseEntity<String>("Comparison not found", HttpStatus.NOT_FOUND);	
		}
		
		return new ResponseEntity<ComparisonDTO>(Comparison.convertToDTO(comparison), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getByExample(Authentication authentication, 
			@RequestParam(value = "source_submission_id", required = false) Long sourceSubmissionId, 
			@RequestParam(value = "target_submission_id", required = false) Long targetSubmissionId,
			@RequestParam(value = "private_source_submission_id", required = false) String privateSourceSubmissionId,
			@RequestParam(value = "private_target_submission_id", required = false) String privateTargetSubmissionId,
			@RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset) {
		
		User user = this.userDao.get(authentication.getName());
		
		Submission sourceSubmission = new Submission(sourceSubmissionId, privateSourceSubmissionId);
		Submission targetSubmission = new Submission(targetSubmissionId, privateTargetSubmissionId);
		
		Comparison comparison = new Comparison();
		comparison.setSourceSubmission(sourceSubmission);
		comparison.setTargetSubmission(targetSubmission);
		
		List<Comparison> comparisons = this.comparisonDao.list(user, comparison, offset, 100);
		
		return new ResponseEntity<List<ComparisonDTO>>(Comparison.convertToDTO(comparisons), HttpStatus.OK);
	}
	
	@RequestMapping(value = "plagiarism", method = RequestMethod.GET)
	public ResponseEntity<?> getResults(Authentication authentication, 
			@RequestParam(value = "group_id") Long groupId, 
			@RequestParam(value = "threshold") BigDecimal threshold,
			@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
		
		User user = this.userDao.get(authentication.getName());
		
		Group group = this.groupDao.get(user, groupId);
		
		List<Comparison> comparisons = this.comparisonDao.list(user, group, threshold, offset, 10);
		
		return new ResponseEntity<List<ComparisonDTO>>(Comparison.convertToDTO(comparisons), HttpStatus.OK);
	}

}
