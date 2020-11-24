// Generated from /Users/diogo/Developer/git/untitled/src/OCTAVE.g4 by ANTLR 4.6
package com.huxley.plagiarism.tokenizer.octave.listener;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import com.huxley.plagiarism.tokenizer.octave.OCTAVEParser;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OCTAVEParser}.
 */
public interface OCTAVEListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#fileDecl}.
	 * @param ctx the parse tree
	 */
	void enterFileDecl(OCTAVEParser.FileDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#fileDecl}.
	 * @param ctx the parse tree
	 */
	void exitFileDecl(OCTAVEParser.FileDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#endStat}.
	 * @param ctx the parse tree
	 */
	void enterEndStat(OCTAVEParser.EndStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#endStat}.
	 * @param ctx the parse tree
	 */
	void exitEndStat(OCTAVEParser.EndStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#endStatNL}.
	 * @param ctx the parse tree
	 */
	void enterEndStatNL(OCTAVEParser.EndStatNLContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#endStatNL}.
	 * @param ctx the parse tree
	 */
	void exitEndStatNL(OCTAVEParser.EndStatNLContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#partialFunctionDecl}.
	 * @param ctx the parse tree
	 */
	void enterPartialFunctionDecl(OCTAVEParser.PartialFunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#partialFunctionDecl}.
	 * @param ctx the parse tree
	 */
	void exitPartialFunctionDecl(OCTAVEParser.PartialFunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(OCTAVEParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(OCTAVEParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(OCTAVEParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(OCTAVEParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(OCTAVEParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(OCTAVEParser.ClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#propBlockDecl}.
	 * @param ctx the parse tree
	 */
	void enterPropBlockDecl(OCTAVEParser.PropBlockDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#propBlockDecl}.
	 * @param ctx the parse tree
	 */
	void exitPropBlockDecl(OCTAVEParser.PropBlockDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#methodBlockDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodBlockDecl(OCTAVEParser.MethodBlockDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#methodBlockDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodBlockDecl(OCTAVEParser.MethodBlockDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#outArgs}.
	 * @param ctx the parse tree
	 */
	void enterOutArgs(OCTAVEParser.OutArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#outArgs}.
	 * @param ctx the parse tree
	 */
	void exitOutArgs(OCTAVEParser.OutArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#inArgs}.
	 * @param ctx the parse tree
	 */
	void enterInArgs(OCTAVEParser.InArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#inArgs}.
	 * @param ctx the parse tree
	 */
	void exitInArgs(OCTAVEParser.InArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#prop}.
	 * @param ctx the parse tree
	 */
	void enterProp(OCTAVEParser.PropContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#prop}.
	 * @param ctx the parse tree
	 */
	void exitProp(OCTAVEParser.PropContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#dotRef}.
	 * @param ctx the parse tree
	 */
	void enterDotRef(OCTAVEParser.DotRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#dotRef}.
	 * @param ctx the parse tree
	 */
	void exitDotRef(OCTAVEParser.DotRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#statBlock}.
	 * @param ctx the parse tree
	 */
	void enterStatBlock(OCTAVEParser.StatBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#statBlock}.
	 * @param ctx the parse tree
	 */
	void exitStatBlock(OCTAVEParser.StatBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(OCTAVEParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(OCTAVEParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(OCTAVEParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(OCTAVEParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#caseStat}.
	 * @param ctx the parse tree
	 */
	void enterCaseStat(OCTAVEParser.CaseStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#caseStat}.
	 * @param ctx the parse tree
	 */
	void exitCaseStat(OCTAVEParser.CaseStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(OCTAVEParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(OCTAVEParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#arrayExpr}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(OCTAVEParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#arrayExpr}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(OCTAVEParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#cellExpr}.
	 * @param ctx the parse tree
	 */
	void enterCellExpr(OCTAVEParser.CellExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#cellExpr}.
	 * @param ctx the parse tree
	 */
	void exitCellExpr(OCTAVEParser.CellExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(OCTAVEParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(OCTAVEParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OCTAVEParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(OCTAVEParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link OCTAVEParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(OCTAVEParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hcat}
	 * labeled alternative in {@link OCTAVEParser#exprArrayList}.
	 * @param ctx the parse tree
	 */
	void enterHcat(OCTAVEParser.HcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hcat}
	 * labeled alternative in {@link OCTAVEParser#exprArrayList}.
	 * @param ctx the parse tree
	 */
	void exitHcat(OCTAVEParser.HcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vcat}
	 * labeled alternative in {@link OCTAVEParser#exprArrayList}.
	 * @param ctx the parse tree
	 */
	void enterVcat(OCTAVEParser.VcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vcat}
	 * labeled alternative in {@link OCTAVEParser#exprArrayList}.
	 * @param ctx the parse tree
	 */
	void exitVcat(OCTAVEParser.VcatContext ctx);
}