package com.huxley.plagiarism.worker;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Worker {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/rabbit-mq.xml");
		context.registerShutdownHook();
	}
	
}
