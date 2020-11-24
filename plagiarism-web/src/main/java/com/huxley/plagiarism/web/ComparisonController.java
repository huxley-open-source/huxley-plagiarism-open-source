package com.huxley.plagiarism.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huxley.plagiarism.dao.ComparisonDao;
import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.Comparison;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.User;

@Controller
@RequestMapping(value = "comparisons")
public class ComparisonController implements Serializable {

	private static final long serialVersionUID = -6568501113270986180L;

	@Value("${data.dir}")
	private String filePath;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ComparisonDao comparisonDao;
	
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
		Comparison comparison = this.comparisonDao.get(user, id);
				
		if (comparison == null) {
			model.addAttribute("error", "Comparison not found.");
			return "error";
		}
		
		List<Object[]> groupFullPath = loadFullGroupPath(comparison.getSourceSubmission().getGroup());
		
		Collections.reverse(groupFullPath);
		
		comparison.getSourceSubmission().readFile(this.filePath);
		comparison.getTargetSubmission().readFile(this.filePath);
		
		model.addAttribute("groupFullPath", groupFullPath);
		model.addAttribute("comparison", comparison);
		
		return "diff";
	}
	
	
	@RequestMapping(value = "{id}/plagiarism", method = RequestMethod.GET)
	public String markAsPlagiarism(Authentication authentication, Model model,
			@PathVariable("id") Long id,
			@RequestParam("value") String value) {
		
		User user = this.userDao.get(authentication.getName());
		Comparison comparison = this.comparisonDao.get(user, id);
		
		if ("yes".equals(value)) {
			comparison.setPlagiarism(true);
		} else {
			comparison.setPlagiarism(false);
		}
		
		return "redirect:/comparisons/" + id;
	}

}
