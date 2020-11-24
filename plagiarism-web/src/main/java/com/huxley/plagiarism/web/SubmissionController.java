package com.huxley.plagiarism.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huxley.plagiarism.dao.SubmissionDao;
import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Submission;
import com.huxley.plagiarism.domain.User;

@Controller
@RequestMapping(value = "submissions")
public class SubmissionController {
	
	@Autowired
	private SubmissionDao submissionDao;
	
	@Autowired
	private UserDao userDao;
	
	private List<Object[]> loadFullGroupPath(Group group) {
		List<Object[]> fullPath = new ArrayList<>();
		
		Object[] row = new Object[2];
		row[0] = group.getId();
		row[1] = group.getName();
		
		fullPath.add(row);
		
		if (group.getParentGroup() != null) {
			fullPath.addAll(loadFullGroupPath(group.getParentGroup()));
		}
		
		return fullPath;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String get(Authentication authentication, Model model, 
			@PathVariable("id") Long id) {
		
		User user = this.userDao.get(authentication.getName());		
		Submission submission = this.submissionDao.get(user, id);
		
		if (submission == null) {
			model.addAttribute("error", "Invalid submission.");
			return "error";
		}
		
		List<Object[]> groupFullPath = loadFullGroupPath(submission.getGroup());
		
		Collections.reverse(groupFullPath);
		
		model.addAttribute("groupFullPath", groupFullPath);
		model.addAttribute("submission", submission);
		
		return "comparisons";
	}
	
}
