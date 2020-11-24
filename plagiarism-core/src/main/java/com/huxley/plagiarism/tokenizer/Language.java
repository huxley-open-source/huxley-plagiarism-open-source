package com.huxley.plagiarism.tokenizer;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.huxley.plagiarism.tokenizer.c.CLexer;
import com.huxley.plagiarism.tokenizer.c.CParser;
import com.huxley.plagiarism.tokenizer.c.visitor.CCustomVisitor;
import com.huxley.plagiarism.tokenizer.octave.OCTAVELexer;
import com.huxley.plagiarism.tokenizer.octave.OCTAVEParser;
import com.huxley.plagiarism.tokenizer.octave.visitor.OCTAVECustomVisitor;
import com.huxley.plagiarism.tokenizer.other.GenericTokenGenerator;

public enum Language {
	
	C("C") {
		
		@Override
		public List<Token> generateTokens(String content) {
			StringReader reader = new StringReader(content);
			CharStream stream = new UnbufferedCharStream(reader);
			
			CLexer lexer = new CLexer(stream);
			lexer.setTokenFactory(new CommonTokenFactory(true));
			
			UnbufferedTokenStream<org.antlr.v4.runtime.Token> tokenStream = new UnbufferedTokenStream<>(lexer); 
			CParser parser = new CParser(tokenStream);
			parser.setErrorHandler(new MyErrorStrategy());
			ParseTree tree = parser.compilationUnit();

			CCustomVisitor visitor = new CCustomVisitor();
			visitor.visit(tree);
			
			return visitor.getTokens();
		}
		
	},
	CPP("CPP"),
	JAVA("JAVA"),
	OCTAVE("OCTAVE") {
		
		@Override
		public List<Token> generateTokens(String content) {
			StringReader reader = new StringReader(content);
			CharStream stream = new UnbufferedCharStream(reader);
			
			OCTAVELexer lexer = new OCTAVELexer(stream);
			lexer.setTokenFactory(new CommonTokenFactory(true));
			
			UnbufferedTokenStream<org.antlr.v4.runtime.Token> tokenStream = new UnbufferedTokenStream<>(lexer); 
			OCTAVEParser parser = new OCTAVEParser(tokenStream);
			parser.setErrorHandler(new MyErrorStrategy());
			ParseTree tree = parser.fileDecl();

			OCTAVECustomVisitor visitor = new OCTAVECustomVisitor();
			visitor.visit(tree);

			return visitor.getTokens();
		}
		
	},
	PASCAL("PASCAL"),
	PYTHON("PYTHON"),
	PYTHON_3_2("PYTHON_3_2"),
	OTHER("OTHER") {
		
		@Override
		public List<Token> generateTokens(String content) {
			GenericTokenGenerator genericTokenGenerator = new GenericTokenGenerator();
			genericTokenGenerator.process(content);
			return genericTokenGenerator.getTokens();
		}
		
	};
	
	public List<Token> generateTokens(String content) {
		return Arrays.asList(new Token(content, content));
	}
	
	private String name;
	
	private Language(String name) {
		this.name = name;
	}

	public static Language get(String selectedLanguage) {
		for (Language language : Language.values()) {
			if (language.name.equalsIgnoreCase(selectedLanguage)) {
				return language;							
			}
		}
		return null;
	}
	
}
