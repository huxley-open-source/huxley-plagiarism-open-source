package com.huxley.plagiarism.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "authentication", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
}
