package com.huxley.plagiarism.tokenizer.octave.visitor;

import java.util.ArrayList;
import java.util.List;

import com.huxley.plagiarism.tokenizer.Language;
import com.huxley.plagiarism.tokenizer.Token;

public class OCTAVECustomVisitor extends OCTAVEBaseVisitor<Void> {
	
	public List<Token> getTokens() {
		return new ArrayList<Token>();
	}
	
	public static void main(String[] args) {
		System.out.println(Language.OCTAVE.generateTokens("a = input('');"));
	}

}
