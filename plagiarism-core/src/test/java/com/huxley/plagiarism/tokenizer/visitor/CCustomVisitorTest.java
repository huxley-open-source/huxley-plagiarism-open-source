package com.huxley.plagiarism.tokenizer.visitor;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import com.huxley.plagiarism.tokenizer.Token;
import com.huxley.plagiarism.tokenizer.c.CLexer;
import com.huxley.plagiarism.tokenizer.c.CParser;
import com.huxley.plagiarism.tokenizer.c.visitor.CCustomVisitor;

public class CCustomVisitorTest {
	
	private static final String PATH = "c/";

	private static final String IF = "if.c";
	
	private static final String IF_ELSE = "if_else.c";
	
	private static final String FUNCTION_ASSIGN = "function_assign.c";
	
	private static final String FOR = "for.c";
	
	private static final String SWITCH = "switch.c";
	
	private static final String WHILE = "while.c";
	
	private List<Token> tokens = new ArrayList<>();
	
	private ANTLRInputStream loadStream(String fileName) {
		try {
			return new ANTLRInputStream(getClass().getClassLoader().getResourceAsStream(PATH + fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Token> loadTokens(String file) {
		CLexer lexer = new CLexer(this.loadStream(file));
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CParser parser = new CParser(tokens);
		
		CCustomVisitor visitor = new CCustomVisitor();
		visitor.visit(parser.compilationUnit());
		
		return visitor.getTokens();
	}
	
	@Before
	public void setUp() {
		this.tokens.clear();
	}
	
	@Test
	public void ifCommand() {
		this.tokens.add(Token.ENTER_FUNCTION_DEFINITION);
		this.tokens.add(Token.ENTER_SELECTION_STATEMENT);
		this.tokens.add(Token.BEGIN_CONDITION);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.END_CONDITION);
		this.tokens.add(Token.EXIT_SELECTION_STATEMENT);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.EXIT_FUNCTION_DEFINITION);
		
		List<Token> result = this.loadTokens(IF);

		assertEquals(this.tokens, result);
	}
	
	@Test
	public void ifElseCommand() {
		CLexer lexer = new CLexer(this.loadStream(IF_ELSE));
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CParser parser = new CParser(tokens);
		
		CCustomVisitor visitor = new CCustomVisitor();
		visitor.visit(parser.compilationUnit());

		this.tokens.add(Token.ENTER_FUNCTION_DEFINITION);
		this.tokens.add(Token.ENTER_SELECTION_STATEMENT);
		this.tokens.add(Token.BEGIN_CONDITION);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.END_CONDITION);
		this.tokens.add(Token.BEGIN_CONDITION);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.END_CONDITION);
		this.tokens.add(Token.EXIT_SELECTION_STATEMENT);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.EXIT_FUNCTION_DEFINITION);
		
		List<Token> result = this.loadTokens(IF_ELSE);

		assertEquals(this.tokens, result);
	}
	
	@Test
	public void functionAssign() {
		CLexer lexer = new CLexer(this.loadStream(FUNCTION_ASSIGN));
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CParser parser = new CParser(tokens);
		
		CCustomVisitor visitor = new CCustomVisitor();
		visitor.visit(parser.compilationUnit());

		this.tokens.add(Token.ENTER_FUNCTION_DEFINITION);
		this.tokens.add(Token.IDENTIFIER);
		this.tokens.add(Token.ASSIGN);
		this.tokens.add(Token.FUNCTION_CALL);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.EXIT_FUNCTION_DEFINITION);
		
		List<Token> result = this.loadTokens(FUNCTION_ASSIGN);

		assertEquals(this.tokens, result);
	}
	
	@Test
	public void forCommand() {
		CLexer lexer = new CLexer(this.loadStream(FOR));
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CParser parser = new CParser(tokens);
		
		CCustomVisitor visitor = new CCustomVisitor();
		visitor.visit(parser.compilationUnit());

		this.tokens.add(Token.ENTER_FUNCTION_DEFINITION);
		this.tokens.add(Token.ENTER_ITERATION_STATEMENT);
		this.tokens.add(Token.IDENTIFIER);
		this.tokens.add(Token.PLUS_ASSIGN);
		this.tokens.add(Token.CONSTANT);
		this.tokens.add(Token.EXIT_ITERATION_STATEMENT);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.EXIT_FUNCTION_DEFINITION);
		
		List<Token> result = this.loadTokens(FOR);
		
		assertEquals(this.tokens, result);
	}
	
	@Test
	public void whileCommand() {
		CLexer lexer = new CLexer(this.loadStream(WHILE));
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		CParser parser = new CParser(tokens);
		
		CCustomVisitor visitor = new CCustomVisitor();
		visitor.visit(parser.compilationUnit());
		
		this.tokens.add(Token.ENTER_FUNCTION_DEFINITION);
		this.tokens.add(Token.IDENTIFIER);
		this.tokens.add(Token.ASSIGN);
		this.tokens.add(Token.CONSTANT);
		this.tokens.add(Token.ENTER_ITERATION_STATEMENT);
		this.tokens.add(Token.IDENTIFIER);
		this.tokens.add(Token.PLUS_ASSIGN);
		this.tokens.add(Token.CONSTANT);
		this.tokens.add(Token.EXIT_ITERATION_STATEMENT);
		this.tokens.add(Token.JUMP_STATEMENT);
		this.tokens.add(Token.EXIT_FUNCTION_DEFINITION);
		
		List<Token> result = this.loadTokens(WHILE);
		
		assertEquals(this.tokens, result);
	}
	
//	int main (int argc, char const *argv[])
//	{
//		int a, b = 1;
//		switch (b) {
//			case 1:
//		        	a = 1;
//			break;
//			default:
//		        	a = b;
//	    }
//		return 0;
//	}
//	@Test
//	public void switchCommand() {		
//		this.tokens.add(Token.ENTER_FUNCTION_DEFINITION);
//		this.tokens.add(Token.IDENTIFIER);
//		this.tokens.add(Token.ASSIGN);
//		this.tokens.add(Token.CONSTANT);
//		this.tokens.add(Token.ENTER_SELECTION_STATEMENT);
//		this.tokens.add(Token.BEGIN_CONDITION);
//		this.tokens.add(Token.IDENTIFIER);
//		this.tokens.add(Token.ASSIGN);
//		this.tokens.add(Token.CONSTANT);
//		this.tokens.add(Token.JUMP_STATEMENT);
//		this.tokens.add(Token.END_CONDITION);
//		this.tokens.add(Token.BEGIN_CONDITION);
//		this.tokens.add(Token.IDENTIFIER);
//		this.tokens.add(Token.ASSIGN);
//		this.tokens.add(Token.IDENTIFIER);
//		this.tokens.add(Token.END_CONDITION);
//		this.tokens.add(Token.EXIT_SELECTION_STATEMENT);
//		this.tokens.add(Token.JUMP_STATEMENT);
//		this.tokens.add(Token.EXIT_FUNCTION_DEFINITION);
//		
//		List<Token> result = this.loadTokens(SWITCH);
//
//		assertEquals(this.tokens, result);
//	}

}
