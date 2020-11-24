package com.huxley.plagiarism.tokenizer.other;

import java.util.ArrayList;
import java.util.List;

import com.huxley.plagiarism.tokenizer.Token;

public class GenericTokenGenerator {
	
	private List<Token> tokens = new ArrayList<>();
	
	public void process(String content) {
		this.tokens.clear();

		for (String word : content.replaceAll("\\p{Punct}", " ").split("\\s+")) {
			this.tokens.add(new Token(word, word));
		}
	}

	public List<Token> getTokens() {
		return tokens;
	}

}
