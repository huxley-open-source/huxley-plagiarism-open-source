package com.huxley.plagiarism.tokenizer;

import java.util.List;

public class TokenBuilder {
	
	public List<Token> generateTokens(String content, String selectedLanguage) {
		Language language = Language.get(selectedLanguage);
		if (language == null) {
			return Language.OTHER.generateTokens(content);
		}
		return language.generateTokens(content);
	}
	
}
