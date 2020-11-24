package com.huxley.plagiarism.integrator;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Integrator {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/application-context.xml");
		context.registerShutdownHook();
	}

}
