// Generated from /Users/diogo/Developer/git/untitled/src/OCTAVE.g4 by ANTLR 4.6
package com.huxley.plagiarism.tokenizer.octave.visitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import com.huxley.plagiarism.tokenizer.octave.OCTAVEParser;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OCTAVEParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OCTAVEVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#fileDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileDecl(OCTAVEParser.FileDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#endStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndStat(OCTAVEParser.EndStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#endStatNL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndStatNL(OCTAVEParser.EndStatNLContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#partialFunctionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialFunctionDecl(OCTAVEParser.PartialFunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(OCTAVEParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(OCTAVEParser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#classDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(OCTAVEParser.ClassDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#propBlockDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropBlockDecl(OCTAVEParser.PropBlockDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#methodBlockDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBlockDecl(OCTAVEParser.MethodBlockDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#outArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutArgs(OCTAVEParser.OutArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#inArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInArgs(OCTAVEParser.InArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#prop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProp(OCTAVEParser.PropContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#dotRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotRef(OCTAVEParser.DotRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#statBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatBlock(OCTAVEParser.StatBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(OCTAVEParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#whileStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(OCTAVEParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#caseStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStat(OCTAVEParser.CaseStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(OCTAVEParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#arrayExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(OCTAVEParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#cellExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCellExpr(OCTAVEParser.CellExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(OCTAVEParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link OCTAVEParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(OCTAVEParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hcat}
	 * labeled alternative in {@link OCTAVEParser#exprArrayList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHcat(OCTAVEParser.HcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vcat}
	 * labeled alternative in {@link OCTAVEParser#exprArrayList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVcat(OCTAVEParser.VcatContext ctx);
}