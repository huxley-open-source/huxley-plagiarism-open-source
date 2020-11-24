package com.huxley.plagiarism.tokenizer.c.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.huxley.plagiarism.tokenizer.Token;
import com.huxley.plagiarism.tokenizer.c.CParser.AdditiveExpressionContext;
import com.huxley.plagiarism.tokenizer.c.CParser.AssignmentExpressionContext;
import com.huxley.plagiarism.tokenizer.c.CParser.AssignmentOperatorContext;
import com.huxley.plagiarism.tokenizer.c.CParser.FunctionDefinitionContext;
import com.huxley.plagiarism.tokenizer.c.CParser.InitDeclaratorContext;
import com.huxley.plagiarism.tokenizer.c.CParser.InitializerContext;
import com.huxley.plagiarism.tokenizer.c.CParser.InitializerListContext;
import com.huxley.plagiarism.tokenizer.c.CParser.IterationStatementContext;
import com.huxley.plagiarism.tokenizer.c.CParser.JumpStatementContext;
import com.huxley.plagiarism.tokenizer.c.CParser.LabeledStatementContext;
import com.huxley.plagiarism.tokenizer.c.CParser.MultiplicativeExpressionContext;
import com.huxley.plagiarism.tokenizer.c.CParser.PostfixExpressionContext;
import com.huxley.plagiarism.tokenizer.c.CParser.PrimaryExpressionContext;
import com.huxley.plagiarism.tokenizer.c.CParser.SelectionStatementContext;

public class CCustomVisitor extends CBaseVisitor<Void> {
	
	private List<Token> tokens = new ArrayList<>();

	public List<Token> getTokens() {
		return tokens;
	}

	@Override
	public Void visitAssignmentExpression(AssignmentExpressionContext ctx) {		
		if (ctx.getChildCount() == 3) {
			this.visit(ctx.getChild(0));
			this.visit(ctx.getChild(1));
			this.visit(ctx.getChild(2));
		} else if (ctx.getChildCount() == 1) {
			this.visit(ctx.getChild(0));
		}
		
		return null;
	}

	@Override
	public Void visitInitializer(InitializerContext ctx) {
		if (ctx.getChildCount() == 1) {
			super.visit(ctx.assignmentExpression());
		} else if (ctx.getChildCount() == 3) {
			super.visit(ctx.initializerList());
		}
		return null;
	}
	
	@Override
	public Void visitInitializerList(InitializerListContext ctx) {
		if (ctx.getChildCount() <= 2) {
			tokens.add(Token.CONSTANT);
		}
		return null;
	}

	@Override
	public Void visitAssignmentOperator(AssignmentOperatorContext ctx) {		
		if (ctx.Assign() != null) {
			tokens.add(Token.ASSIGN);		
		} else if (ctx.StarAssign() != null) {
			tokens.add(Token.STAR_ASSIGN);		
		} else if (ctx.DivAssign() != null) {
			tokens.add(Token.DIV_ASSIGN);		
		} else if (ctx.ModAssign() != null) {
			tokens.add(Token.MOD_ASSIGN);		
		} else if (ctx.PlusAssign() != null) {
			tokens.add(Token.PLUS_ASSIGN);		
		} else if (ctx.MinusAssign() != null) {
			tokens.add(Token.MINUS_ASSIGN);		
		} else if (ctx.LeftShiftAssign() != null) {
			tokens.add(Token.LEFT_SHIFT_ASSIGN);
		} else if (ctx.RightShiftAssign() != null) {
			tokens.add(Token.RIGHT_SHIFT_ASSIGN);		
		} else if (ctx.AndAssign() != null) {
			tokens.add(Token.AND_ASSIGN);
		} else if (ctx.XorAssign() != null) {
			tokens.add(Token.XOR_ASSIGN);	
		} else if (ctx.OrAssign() != null) {
			tokens.add(Token.OR_ASSIGN);		
		}

		return null;
	}

	@Override
	public Void visitAdditiveExpression(AdditiveExpressionContext ctx) {
		if (ctx.getChildCount() == 3) {
			this.visit(ctx.getChild(0));
			if (ctx.Plus() != null) {
				tokens.add(Token.PLUS);
			} else if (ctx.Minus() != null) {
				tokens.add(Token.MINUS);			
			}
			
			this.visit(ctx.getChild(2));
			
			return null;
		}
			
		return this.visitChildren(ctx);
	}

	@Override
	public Void visitMultiplicativeExpression(MultiplicativeExpressionContext ctx) {		
		if (ctx.getChildCount() == 3) {			
			this.visit(ctx.getChild(0));
			
			if (ctx.Star() != null) {
				tokens.add(Token.STAR);
			} else if (ctx.Div() != null) {
				tokens.add(Token.DIV);
			} else if (ctx.Mod() != null) {
				tokens.add(Token.MOD);		
			}
			
			this.visit(ctx.getChild(2));
			
			return null;
		}
		
		return this.visitChildren(ctx);
	}
	
	@Override
	public Void visitPrimaryExpression(PrimaryExpressionContext ctx) {	
		if (ctx.getChildCount() == 1) {
			if (ctx.Constant() != null) {
				tokens.add(Token.CONSTANT);
			} else if (ctx.Identifier() != null) {
				tokens.add(Token.IDENTIFIER);
			}			
		} else if (ctx.getChildCount() == 3) {
			tokens.add(Token.LEFT_PAREN);
			this.visit(ctx.getChild(1));
			tokens.add(Token.RIGHT_PAREN);
		}

		return null;
	}

	@Override
	public Void visitFunctionDefinition(FunctionDefinitionContext ctx) {
		tokens.add(Token.ENTER_FUNCTION_DEFINITION);
		this.visit(ctx.compoundStatement());
		tokens.add(Token.EXIT_FUNCTION_DEFINITION);
		
		return null;
	}

	@Override
	public Void visitSelectionStatement(SelectionStatementContext ctx) {
		tokens.add(Token.ENTER_SELECTION_STATEMENT);
		if (ctx.If() != null) {
			tokens.add(Token.BEGIN_CONDITION);
			this.visit(ctx.getChild(4));
			tokens.add(Token.END_CONDITION);
			if (ctx.Else() != null) {
				tokens.add(Token.BEGIN_CONDITION);
				this.visit(ctx.getChild(6));
				tokens.add(Token.END_CONDITION);
			}
		} else if (ctx.Switch() != null) {
			visit(ctx.getChild(4));
		}
		tokens.add(Token.EXIT_SELECTION_STATEMENT);
		
		return null;
	}

	@Override
	public Void visitLabeledStatement(LabeledStatementContext ctx) {
		if (ctx.Case() != null || ctx.Default() != null) {
			tokens.add(Token.BEGIN_CONDITION);
			super.visit(ctx.statement());
			tokens.add(Token.END_CONDITION);
		}
		return null;
	}

	@Override
	public Void visitInitDeclarator(InitDeclaratorContext ctx) {
		if (ctx.getChildCount() == 3) {
			tokens.add(Token.IDENTIFIER);
			tokens.add(Token.ASSIGN);
			this.visit(ctx.getChild(2));
		}
		
		return null;
	}
	
	@Override
	public Void visitPostfixExpression(PostfixExpressionContext ctx) {
		if (ctx.getChildCount() == 1) {
			this.visit(ctx.getChild(0));
		} else if (ctx.postfixExpression() != null && ctx.LeftParen() != null && ctx.RightParen() != null) {
			if (!Pattern.matches("(printf|scanf).*", ctx.getText())) {
				tokens.add(Token.FUNCTION_CALL);
			};
		} else if (ctx.postfixExpression() != null && ctx.LeftBracket() != null && ctx.RightBracket() != null) {
			this.visit(ctx.postfixExpression());
		} else if (ctx.postfixExpression() != null && ctx.PlusPlus() != null) {
			this.visit(ctx.postfixExpression());
			tokens.add(Token.ASSIGN);
			tokens.add(Token.IDENTIFIER);
			tokens.add(Token.PLUS);
			tokens.add(Token.CONSTANT);
		} else if (ctx.postfixExpression() != null && ctx.MinusMinus() != null) {
			this.visit(ctx.postfixExpression());
			tokens.add(Token.ASSIGN);
			tokens.add(Token.IDENTIFIER);
			tokens.add(Token.MINUS);
			tokens.add(Token.CONSTANT);
		}
		
		return null;
	}

	@Override
	public Void visitJumpStatement(JumpStatementContext ctx) {
		tokens.add(Token.JUMP_STATEMENT);
		return null;
	}

	@Override
	public Void visitIterationStatement(IterationStatementContext ctx) {
		tokens.add(Token.ENTER_ITERATION_STATEMENT);
		this.visit(ctx.statement());
		tokens.add(Token.EXIT_ITERATION_STATEMENT);

		return null;
	}
}
