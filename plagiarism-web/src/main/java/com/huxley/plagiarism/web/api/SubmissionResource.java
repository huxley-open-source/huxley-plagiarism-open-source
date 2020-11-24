package com.huxley.plagiarism.web.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huxley.plagiarism.dao.GroupDao;
import com.huxley.plagiarism.dao.SubmissionDao;
import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Language;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.User;
import com.huxley.plagiarism.web.api.dto.ComparisonDTO;
import com.huxley.plagiarism.web.api.dto.SubmissionDTO;

@Controller
@RequestMapping(value = "api/submissions")
public class SubmissionResource implements Serializable {
	
	private static final long serialVersionUID = -2282563576074981115L;
	
	@Value("${data.dir}")
	private String filePath;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private SubmissionDao submissionDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> submit(Authentication authentication, Model model, 
			@RequestParam("group_id") Long groupId, 
			@RequestParam("private_id") String privateId, 
			@RequestParam("language") String language, 
			@RequestParam("file") MultipartFile file) throws IOException {
		
		User user = this.userDao.get(authentication.getName());
		Group group = this.groupDao.get(user, groupId);
		
		Language selectedLanguage = Language.findByName(language);		
		
		if (selectedLanguage == null) {
			return new ResponseEntity<String>("Language not found.", HttpStatus.BAD_REQUEST);
		}
		
		if (group == null) {
			return new ResponseEntity<String>("Group not found.", HttpStatus.BAD_REQUEST);
		}
		
		if (privateId != null && !"".equals(privateId)) {			
			Submission testSubmission = new Submission();
			testSubmission.setPrivateId(privateId);
			
			if (!this.submissionDao.list(user, testSubmission, 0, 1).isEmpty()) {
				return new ResponseEntity<String>("Submission already exists.", HttpStatus.FOUND);
			}
		} else {
			privateId = null;
		}
		
		String filename = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		Submission submission = new Submission(group, privateId, filename, inputStream, selectedLanguage);
		
		submission.saveFile(this.filePath);
		
		this.submissionDao.save(submission);		
		
		return new ResponseEntity<SubmissionDTO>(new SubmissionDTO(submission.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(Authentication authentication, 
			@PathVariable(value = "id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		
		Submission submission = this.submissionDao.get(user, id);
				
		if (submission == null) {
			return new ResponseEntity<String>("Submission not found.", HttpStatus.NOT_FOUND);
		}
		
		submission.readFile(this.filePath);
		
		return new ResponseEntity<SubmissionDTO>(Submission.convertToDTO(submission), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> get(Authentication authentication,
			@RequestParam(value = "private_id", required = false) String privateId,
			@RequestParam(value = "group_id", required = false) Long groupId,
			@RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset) {
		
		User user = this.userDao.get(authentication.getName());
		
		List<Submission> submissions = null;
		
		Submission submission = new Submission(null, privateId);
		
		if (groupId != null) {
			submission.setGroup(new Group(groupId));				
		}
		
		submissions = this.submissionDao.list(user, submission, offset, 100);
		
		return new ResponseEntity<List<SubmissionDTO>>(Submission.convertToDTO(submissions), HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}/comparisons", method = RequestMethod.GET)
	public ResponseEntity<?> getResults(Authentication authentication, 
			@PathVariable(value = "id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		
		Submission submission = this.submissionDao.get(user, id);
		
		if (submission == null) {
			return new ResponseEntity<String>("Submission not found.", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<ComparisonDTO>>(Comparison.convertToDTO(new ArrayList<>(submission.getComparisons())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(Authentication authentication, 
			@PathVariable("id") Long id) {
		
		User user = this.userDao.get(authentication.getName());
		Submission submission = this.submissionDao.get(user, id);
		
		if (submission == null) {
			return new ResponseEntity<String>("Submission not found.", HttpStatus.NOT_FOUND);
		}
		this.submissionDao.delete(submission);
		
		return new ResponseEntity<String>("Success.", HttpStatus.OK);
	}
	
}
