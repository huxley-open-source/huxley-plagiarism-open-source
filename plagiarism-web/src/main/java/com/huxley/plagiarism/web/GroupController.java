package com.huxley.plagiarism.web;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.huxley.plagiarism.dao.GroupDao;
import com.huxley.plagiarism.dao.UserDao;
import com.huxley.plagiarism.domain.Group;
import com.huxley.plagiarism.domain.Language;
import com.huxley.plagiarism.domain.User;

@Controller
@RequestMapping(value = "groups")
public class GroupController {
	
	private static final Integer MAX_RESULTS = 30;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GroupDao groupDao;

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
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Authentication authentication, Model model, 
			@RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset) {
		
		User user = this.userDao.get(authentication.getName());
		List<Group> groups = this.groupDao.listWithNoParent(user, offset, MAX_RESULTS);
		
		Long totalOfGroups = this.groupDao.countWithNoParent(user);

		Long totalOfPages = new BigDecimal(totalOfGroups).divide(new BigDecimal(MAX_RESULTS), BigDecimal.ROUND_UP).longValue();
		
		model.addAttribute("groups", groups);
		model.addAttribute("totalOfPages", totalOfPages);
		
		return "groups";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String get(Authentication authentication, Model model,
			@PathVariable("id") Long id,
			@RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset) {
		
		User user = this.userDao.get(authentication.getName());
		Group group = this.groupDao.get(user, id);	
		
		if (group == null) {
			model.addAttribute("error", "Invalid group.");
			return "error";
		}
		
		List<Object[]> groupFullPath = loadFullGroupPath(group);
		
		Collections.reverse(groupFullPath);
		

		Group example = new Group();
		example.setParentGroup(group);
			
		List<Group> childrenGroups = this.groupDao.list(user, example, offset, MAX_RESULTS);		
		Long totalOfChildrenGroups = this.groupDao.count(user, example);

		Long totalOfPages = new BigDecimal(totalOfChildrenGroups).divide(new BigDecimal(MAX_RESULTS), BigDecimal.ROUND_UP).longValue();
		
		model.addAttribute("group", group);
		model.addAttribute("groupFullPath", groupFullPath);
		model.addAttribute("childrenGroups", childrenGroups);
		model.addAttribute("languages", Language.values());
		model.addAttribute("totalOfPages", totalOfPages);

		return "submissions";
	}

}
