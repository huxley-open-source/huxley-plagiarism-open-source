package com.huxley.plagiarism.tokenizer;

public class Token {
	
	public static Token ASSIGN = new Token("ASSIGN", "00");	 
	public static Token STAR_ASSIGN = new Token("*=", "01");
	public static Token DIV_ASSIGN = new Token("/=", "02");
	public static Token MOD_ASSIGN = new Token("%=", "03");
	public static Token PLUS_ASSIGN = new Token("+=", "04");
	public static Token MINUS_ASSIGN = new Token("-=", "05");
	public static Token LEFT_SHIFT_ASSIGN = new Token("<<=", "06");
	public static Token RIGHT_SHIFT_ASSIGN = new Token(">>=", "07");
	public static Token AND_ASSIGN = new Token("&=", "08");
	public static Token XOR_ASSIGN = new Token("^=", "09");
	public static Token OR_ASSIGN = new Token("|=", "10");
	public static Token PLUS = new Token("+", "11");
	public static Token PLUS_PLUS = new Token("++", "12");
	public static Token MINUS = new Token("-", "13");
	public static Token MINUS_MINUS = new Token("--", "14");
	public static Token STAR = new Token("*", "15");
	public static Token DIV = new Token("/", "16");
	public static Token MOD = new Token("%", "17");
	public static Token CONSTANT = new Token("CONSTANT", "18");
	public static Token IDENTIFIER = new Token("IDENTIFIER", "19");
	public static Token ENTER_FUNCTION_DEFINITION = new Token("ENTER_FUNCTION_DEFINITION", "20");
	public static Token EXIT_FUNCTION_DEFINITION = new Token("EXIT_FUNCTION_DEFINITION", "21");
	public static Token ENTER_SELECTION_STATEMENT = new Token("ENTER_SELECTION_STATEMENT", "22");
	public static Token BEGIN_CONDITION = new Token("BEGIN_CONDITION", "23");
	public static Token END_CONDITION = new Token("END_CONDITION", "24");
	public static Token EXIT_SELECTION_STATEMENT = new Token("EXIT_SELECTION_STATEMENT", "25");
	public static Token FUNCTION_CALL = new Token("FUNCTION_CALL", "26");
	public static Token JUMP_STATEMENT = new Token("JUMP_STATEMENT", "27");
	public static Token ENTER_ITERATION_STATEMENT = new Token("ENTER_ITERATION_STATEMENT", "28");
	public static Token EXIT_ITERATION_STATEMENT = new Token("EXIT_ITERATION_STATEMENT", "29");
	public static Token LEFT_PAREN = new Token("(", "30");
	public static Token RIGHT_PAREN = new Token(")", "31");
	public static Token LEFT_BRACKET = new Token("[", "32");
	public static Token RIGHT_BRACKET = new Token("]", "33");
	public static Token LEFT_BRACE = new Token("{", "34");
	public static Token RIGHT_BRACE = new Token("}", "35");
	
	private String key;
	
	private String value;

	public Token(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Token [key=" + key + "]";
	}

}
