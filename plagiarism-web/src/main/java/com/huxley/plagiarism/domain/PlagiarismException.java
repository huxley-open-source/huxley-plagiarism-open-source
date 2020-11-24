package com.huxley.plagiarism.domain;

public class PlagiarismException extends RuntimeException {

	private static final long serialVersionUID = 7836141906124983951L;

	public PlagiarismException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlagiarismException(String message) {
		super(message);
	}

	public PlagiarismException(Throwable cause) {
		super(cause);
	}
	
}
