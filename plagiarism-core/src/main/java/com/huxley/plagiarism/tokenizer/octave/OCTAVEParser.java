// Generated from /Users/diogo/Developer/git/untitled/src/OCTAVE.g4 by ANTLR 4.6
package com.huxley.plagiarism.tokenizer.octave;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import com.huxley.plagiarism.tokenizer.octave.listener.OCTAVEListener;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OCTAVEParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNCTION=1, CLASSDEF=2, PROPERTIES=3, METHODS=4, END=5, IF=6, ELSEIF=7, 
		ELSE=8, WHILE=9, SWITCH=10, CASE=11, OTHERWISE=12, EQUALS=13, EQUALTO=14, 
		NOTEQUAL=15, GT=16, LT=17, GE=18, LE=19, PLUS=20, MINUS=21, DOT=22, VECAND=23, 
		VECOR=24, SCALAND=25, SCALOR=26, LPAREN=27, RPAREN=28, LBRACE=29, LBRACK=30, 
		MTIMES=31, TIMES=32, RDIVIDE=33, LDIVIDE=34, MRDIVIDE=35, MLDIVIDE=36, 
		POW=37, MPOW=38, NOT=39, COLON=40, TRANS=41, CTRANS=42, NL=43, COMMENT=44, 
		INT=45, FLOAT=46, SCI=47, ID=48, STRING=49, RBRACK=50, RBRACE=51, COMMA=52, 
		SEMI=53, WS=54;
	public static final int
		RULE_fileDecl = 0, RULE_endStat = 1, RULE_endStatNL = 2, RULE_partialFunctionDecl = 3, 
		RULE_functionDecl = 4, RULE_methodDecl = 5, RULE_classDecl = 6, RULE_propBlockDecl = 7, 
		RULE_methodBlockDecl = 8, RULE_outArgs = 9, RULE_inArgs = 10, RULE_prop = 11, 
		RULE_dotRef = 12, RULE_statBlock = 13, RULE_ifStat = 14, RULE_whileStat = 15, 
		RULE_caseStat = 16, RULE_stat = 17, RULE_arrayExpr = 18, RULE_cellExpr = 19, 
		RULE_expr = 20, RULE_exprList = 21, RULE_exprArrayList = 22;
	public static final String[] ruleNames = {
		"fileDecl", "endStat", "endStatNL", "partialFunctionDecl", "functionDecl", 
		"methodDecl", "classDecl", "propBlockDecl", "methodBlockDecl", "outArgs", 
		"inArgs", "prop", "dotRef", "statBlock", "ifStat", "whileStat", "caseStat", 
		"stat", "arrayExpr", "cellExpr", "expr", "exprList", "exprArrayList"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'function'", "'classdef'", "'properties'", "'methods'", "'end'", 
		"'if'", "'elseif'", "'else'", "'while'", "'switch'", "'case'", "'otherwise'", 
		"'='", "'=='", null, "'>'", "'<'", "'>='", "'<='", "'+'", "'-'", "'.'", 
		"'&'", "'|'", "'&&'", "'||'", "'('", "')'", "'{'", "'['", "'*'", "'.*'", 
		"'/'", "'\\'", "'./'", "'.\\'", "'.^'", "'^'", "'~'", "':'", "'.''", "'''", 
		null, null, null, null, null, null, null, "']'", "'}'", "','", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FUNCTION", "CLASSDEF", "PROPERTIES", "METHODS", "END", "IF", "ELSEIF", 
		"ELSE", "WHILE", "SWITCH", "CASE", "OTHERWISE", "EQUALS", "EQUALTO", "NOTEQUAL", 
		"GT", "LT", "GE", "LE", "PLUS", "MINUS", "DOT", "VECAND", "VECOR", "SCALAND", 
		"SCALOR", "LPAREN", "RPAREN", "LBRACE", "LBRACK", "MTIMES", "TIMES", "RDIVIDE", 
		"LDIVIDE", "MRDIVIDE", "MLDIVIDE", "POW", "MPOW", "NOT", "COLON", "TRANS", 
		"CTRANS", "NL", "COMMENT", "INT", "FLOAT", "SCI", "ID", "STRING", "RBRACK", 
		"RBRACE", "COMMA", "SEMI", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "OCTAVE.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public OCTAVEParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileDeclContext extends ParserRuleContext {
		public List<FunctionDeclContext> functionDecl() {
			return getRuleContexts(FunctionDeclContext.class);
		}
		public FunctionDeclContext functionDecl(int i) {
			return getRuleContext(FunctionDeclContext.class,i);
		}
		public ClassDeclContext classDecl() {
			return getRuleContext(ClassDeclContext.class,0);
		}
		public List<PartialFunctionDeclContext> partialFunctionDecl() {
			return getRuleContexts(PartialFunctionDeclContext.class);
		}
		public PartialFunctionDeclContext partialFunctionDecl(int i) {
			return getRuleContext(PartialFunctionDeclContext.class,i);
		}
		public List<StatBlockContext> statBlock() {
			return getRuleContexts(StatBlockContext.class);
		}
		public StatBlockContext statBlock(int i) {
			return getRuleContext(StatBlockContext.class,i);
		}
		public TerminalNode EOF() { return getToken(OCTAVEParser.EOF, 0); }
		public FileDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterFileDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitFileDecl(this);
		}
	}

	public final FileDeclContext fileDecl() throws RecognitionException {
		FileDeclContext _localctx = new FileDeclContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_fileDecl);
		int _la;
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(46);
					functionDecl();
					}
					break;
				case 2:
					{
					setState(47);
					classDecl();
					}
					break;
				}
				setState(62);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(53);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==FUNCTION) {
						{
						{
						setState(50);
						functionDecl();
						}
						}
						setState(55);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==FUNCTION) {
						{
						{
						setState(56);
						partialFunctionDecl();
						}
						}
						setState(61);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(64);
					partialFunctionDecl();
					}
					}
					setState(67); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==FUNCTION );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(69);
					statBlock();
					}
					}
					setState(72); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0) );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndStatContext extends ParserRuleContext {
		public List<TerminalNode> NL() { return getTokens(OCTAVEParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(OCTAVEParser.NL, i);
		}
		public TerminalNode COMMA() { return getToken(OCTAVEParser.COMMA, 0); }
		public TerminalNode SEMI() { return getToken(OCTAVEParser.SEMI, 0); }
		public EndStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterEndStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitEndStat(this);
		}
	}

	public final EndStatContext endStat() throws RecognitionException {
		EndStatContext _localctx = new EndStatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_endStat);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NL) | (1L << COMMA) | (1L << SEMI))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(78);
					match(NL);
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndStatNLContext extends ParserRuleContext {
		public List<TerminalNode> NL() { return getTokens(OCTAVEParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(OCTAVEParser.NL, i);
		}
		public EndStatNLContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endStatNL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterEndStatNL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitEndStatNL(this);
		}
	}

	public final EndStatNLContext endStatNL() throws RecognitionException {
		EndStatNLContext _localctx = new EndStatNLContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_endStatNL);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(84);
					match(NL);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(87); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialFunctionDeclContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(OCTAVEParser.FUNCTION, 0); }
		public TerminalNode ID() { return getToken(OCTAVEParser.ID, 0); }
		public EndStatContext endStat() {
			return getRuleContext(EndStatContext.class,0);
		}
		public OutArgsContext outArgs() {
			return getRuleContext(OutArgsContext.class,0);
		}
		public InArgsContext inArgs() {
			return getRuleContext(InArgsContext.class,0);
		}
		public List<StatBlockContext> statBlock() {
			return getRuleContexts(StatBlockContext.class);
		}
		public StatBlockContext statBlock(int i) {
			return getRuleContext(StatBlockContext.class,i);
		}
		public PartialFunctionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialFunctionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterPartialFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitPartialFunctionDecl(this);
		}
	}

	public final PartialFunctionDeclContext partialFunctionDecl() throws RecognitionException {
		PartialFunctionDeclContext _localctx = new PartialFunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_partialFunctionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(FUNCTION);
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(90);
				outArgs();
				}
				break;
			}
			setState(93);
			match(ID);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(94);
				inArgs();
				}
			}

			setState(97);
			endStat();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
				{
				{
				setState(98);
				statBlock();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclContext extends ParserRuleContext {
		public PartialFunctionDeclContext partialFunctionDecl() {
			return getRuleContext(PartialFunctionDeclContext.class,0);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public EndStatNLContext endStatNL() {
			return getRuleContext(EndStatNLContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(OCTAVEParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(OCTAVEParser.NL, i);
		}
		public FunctionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitFunctionDecl(this);
		}
	}

	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			partialFunctionDecl();
			setState(105);
			match(END);
			setState(106);
			endStatNL();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(107);
				match(NL);
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclContext extends ParserRuleContext {
		public PartialFunctionDeclContext partialFunctionDecl() {
			return getRuleContext(PartialFunctionDeclContext.class,0);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public EndStatContext endStat() {
			return getRuleContext(EndStatContext.class,0);
		}
		public MethodDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitMethodDecl(this);
		}
	}

	public final MethodDeclContext methodDecl() throws RecognitionException {
		MethodDeclContext _localctx = new MethodDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_methodDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			partialFunctionDecl();
			setState(114);
			match(END);
			setState(115);
			endStat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclContext extends ParserRuleContext {
		public TerminalNode CLASSDEF() { return getToken(OCTAVEParser.CLASSDEF, 0); }
		public TerminalNode ID() { return getToken(OCTAVEParser.ID, 0); }
		public List<EndStatContext> endStat() {
			return getRuleContexts(EndStatContext.class);
		}
		public EndStatContext endStat(int i) {
			return getRuleContext(EndStatContext.class,i);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public TerminalNode EOF() { return getToken(OCTAVEParser.EOF, 0); }
		public List<PropBlockDeclContext> propBlockDecl() {
			return getRuleContexts(PropBlockDeclContext.class);
		}
		public PropBlockDeclContext propBlockDecl(int i) {
			return getRuleContext(PropBlockDeclContext.class,i);
		}
		public List<MethodBlockDeclContext> methodBlockDecl() {
			return getRuleContexts(MethodBlockDeclContext.class);
		}
		public MethodBlockDeclContext methodBlockDecl(int i) {
			return getRuleContext(MethodBlockDeclContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(OCTAVEParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(OCTAVEParser.NL, i);
		}
		public ClassDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterClassDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitClassDecl(this);
		}
	}

	public final ClassDeclContext classDecl() throws RecognitionException {
		ClassDeclContext _localctx = new ClassDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(CLASSDEF);
			setState(118);
			match(ID);
			setState(119);
			endStat();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PROPERTIES || _la==METHODS) {
				{
				setState(122);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PROPERTIES:
					{
					setState(120);
					propBlockDecl();
					}
					break;
				case METHODS:
					{
					setState(121);
					methodBlockDecl();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
			match(END);
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				{
				setState(128);
				match(EOF);
				}
				break;
			case NL:
			case COMMA:
			case SEMI:
				{
				setState(129);
				endStat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(132);
				match(NL);
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropBlockDeclContext extends ParserRuleContext {
		public TerminalNode PROPERTIES() { return getToken(OCTAVEParser.PROPERTIES, 0); }
		public List<EndStatContext> endStat() {
			return getRuleContexts(EndStatContext.class);
		}
		public EndStatContext endStat(int i) {
			return getRuleContext(EndStatContext.class,i);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public List<PropContext> prop() {
			return getRuleContexts(PropContext.class);
		}
		public PropContext prop(int i) {
			return getRuleContext(PropContext.class,i);
		}
		public PropBlockDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propBlockDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterPropBlockDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitPropBlockDecl(this);
		}
	}

	public final PropBlockDeclContext propBlockDecl() throws RecognitionException {
		PropBlockDeclContext _localctx = new PropBlockDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_propBlockDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(PROPERTIES);
			setState(139);
			endStat();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(140);
				prop();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			match(END);
			setState(147);
			endStat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBlockDeclContext extends ParserRuleContext {
		public TerminalNode METHODS() { return getToken(OCTAVEParser.METHODS, 0); }
		public List<EndStatContext> endStat() {
			return getRuleContexts(EndStatContext.class);
		}
		public EndStatContext endStat(int i) {
			return getRuleContext(EndStatContext.class,i);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public List<MethodDeclContext> methodDecl() {
			return getRuleContexts(MethodDeclContext.class);
		}
		public MethodDeclContext methodDecl(int i) {
			return getRuleContext(MethodDeclContext.class,i);
		}
		public MethodBlockDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBlockDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterMethodBlockDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitMethodBlockDecl(this);
		}
	}

	public final MethodBlockDeclContext methodBlockDecl() throws RecognitionException {
		MethodBlockDeclContext _localctx = new MethodBlockDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_methodBlockDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(METHODS);
			setState(150);
			endStat();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUNCTION) {
				{
				{
				setState(151);
				methodDecl();
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
			match(END);
			setState(158);
			endStat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutArgsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(OCTAVEParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OCTAVEParser.ID, i);
		}
		public TerminalNode EQUALS() { return getToken(OCTAVEParser.EQUALS, 0); }
		public TerminalNode LBRACK() { return getToken(OCTAVEParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(OCTAVEParser.RBRACK, 0); }
		public List<TerminalNode> COMMA() { return getTokens(OCTAVEParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OCTAVEParser.COMMA, i);
		}
		public OutArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterOutArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitOutArgs(this);
		}
	}

	public final OutArgsContext outArgs() throws RecognitionException {
		OutArgsContext _localctx = new OutArgsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_outArgs);
		int _la;
		try {
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(ID);
				setState(161);
				match(EQUALS);
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				match(LBRACK);
				setState(163);
				match(ID);
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(164);
					match(COMMA);
					setState(165);
					match(ID);
					}
					}
					setState(170);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(171);
				match(RBRACK);
				setState(172);
				match(EQUALS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InArgsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(OCTAVEParser.LPAREN, 0); }
		public List<TerminalNode> ID() { return getTokens(OCTAVEParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OCTAVEParser.ID, i);
		}
		public TerminalNode RPAREN() { return getToken(OCTAVEParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(OCTAVEParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OCTAVEParser.COMMA, i);
		}
		public InArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterInArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitInArgs(this);
		}
	}

	public final InArgsContext inArgs() throws RecognitionException {
		InArgsContext _localctx = new InArgsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_inArgs);
		int _la;
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				match(LPAREN);
				setState(176);
				match(ID);
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(177);
					match(COMMA);
					setState(178);
					match(ID);
					}
					}
					setState(183);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(184);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				match(LPAREN);
				setState(186);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(OCTAVEParser.ID, 0); }
		public EndStatContext endStat() {
			return getRuleContext(EndStatContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(OCTAVEParser.EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitProp(this);
		}
	}

	public final PropContext prop() throws RecognitionException {
		PropContext _localctx = new PropContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_prop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(ID);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(190);
				match(EQUALS);
				setState(191);
				expr(0);
				}
			}

			setState(194);
			endStat();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DotRefContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(OCTAVEParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OCTAVEParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(OCTAVEParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(OCTAVEParser.DOT, i);
		}
		public DotRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterDotRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitDotRef(this);
		}
	}

	public final DotRefContext dotRef() throws RecognitionException {
		DotRefContext _localctx = new DotRefContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dotRef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(ID);
			setState(201);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(197);
					match(DOT);
					setState(198);
					match(ID);
					}
					} 
				}
				setState(203);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatBlockContext extends ParserRuleContext {
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public EndStatContext endStat() {
			return getRuleContext(EndStatContext.class,0);
		}
		public StatBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterStatBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitStatBlock(this);
		}
	}

	public final StatBlockContext statBlock() throws RecognitionException {
		StatBlockContext _localctx = new StatBlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_statBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(204);
			stat();
			setState(205);
			endStat();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(OCTAVEParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<EndStatContext> endStat() {
			return getRuleContexts(EndStatContext.class);
		}
		public EndStatContext endStat(int i) {
			return getRuleContext(EndStatContext.class,i);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public List<StatBlockContext> statBlock() {
			return getRuleContexts(StatBlockContext.class);
		}
		public StatBlockContext statBlock(int i) {
			return getRuleContext(StatBlockContext.class,i);
		}
		public List<TerminalNode> ELSEIF() { return getTokens(OCTAVEParser.ELSEIF); }
		public TerminalNode ELSEIF(int i) {
			return getToken(OCTAVEParser.ELSEIF, i);
		}
		public TerminalNode ELSE() { return getToken(OCTAVEParser.ELSE, 0); }
		public IfStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitIfStat(this);
		}
	}

	public final IfStatContext ifStat() throws RecognitionException {
		IfStatContext _localctx = new IfStatContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ifStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(IF);
			setState(208);
			expr(0);
			setState(209);
			endStat();
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
				{
				{
				setState(210);
				statBlock();
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSEIF) {
				{
				{
				setState(216);
				match(ELSEIF);
				setState(217);
				expr(0);
				setState(218);
				endStat();
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					{
					setState(219);
					statBlock();
					}
					}
					setState(224);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(230);
				match(ELSE);
				setState(232);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(231);
					endStat();
					}
					break;
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					{
					setState(234);
					statBlock();
					}
					}
					setState(239);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(242);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(OCTAVEParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public EndStatContext endStat() {
			return getRuleContext(EndStatContext.class,0);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public List<StatBlockContext> statBlock() {
			return getRuleContexts(StatBlockContext.class);
		}
		public StatBlockContext statBlock(int i) {
			return getRuleContext(StatBlockContext.class,i);
		}
		public WhileStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitWhileStat(this);
		}
	}

	public final WhileStatContext whileStat() throws RecognitionException {
		WhileStatContext _localctx = new WhileStatContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_whileStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(WHILE);
			setState(245);
			expr(0);
			setState(246);
			endStat();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
				{
				{
				setState(247);
				statBlock();
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(253);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseStatContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(OCTAVEParser.SWITCH, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<EndStatContext> endStat() {
			return getRuleContexts(EndStatContext.class);
		}
		public EndStatContext endStat(int i) {
			return getRuleContext(EndStatContext.class,i);
		}
		public TerminalNode END() { return getToken(OCTAVEParser.END, 0); }
		public List<TerminalNode> CASE() { return getTokens(OCTAVEParser.CASE); }
		public TerminalNode CASE(int i) {
			return getToken(OCTAVEParser.CASE, i);
		}
		public TerminalNode OTHERWISE() { return getToken(OCTAVEParser.OTHERWISE, 0); }
		public List<StatBlockContext> statBlock() {
			return getRuleContexts(StatBlockContext.class);
		}
		public StatBlockContext statBlock(int i) {
			return getRuleContext(StatBlockContext.class,i);
		}
		public CaseStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterCaseStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitCaseStat(this);
		}
	}

	public final CaseStatContext caseStat() throws RecognitionException {
		CaseStatContext _localctx = new CaseStatContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_caseStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(SWITCH);
			setState(256);
			expr(0);
			setState(257);
			endStat();
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(258);
				match(CASE);
				setState(259);
				expr(0);
				setState(260);
				endStat();
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					{
					setState(261);
					statBlock();
					}
					}
					setState(266);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OTHERWISE) {
				{
				setState(272);
				match(OTHERWISE);
				setState(273);
				endStat();
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << PLUS) | (1L << MINUS) | (1L << LPAREN) | (1L << LBRACE) | (1L << LBRACK) | (1L << NOT) | (1L << NL) | (1L << INT) | (1L << FLOAT) | (1L << SCI) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					{
					setState(274);
					statBlock();
					}
					}
					setState(279);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(282);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public DotRefContext dotRef() {
			return getRuleContext(DotRefContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(OCTAVEParser.EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IfStatContext ifStat() {
			return getRuleContext(IfStatContext.class,0);
		}
		public WhileStatContext whileStat() {
			return getRuleContext(WhileStatContext.class,0);
		}
		public CaseStatContext caseStat() {
			return getRuleContext(CaseStatContext.class,0);
		}
		public TerminalNode NL() { return getToken(OCTAVEParser.NL, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stat);
		try {
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284);
				dotRef();
				setState(285);
				match(EQUALS);
				setState(286);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(288);
				ifStat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(289);
				whileStat();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(290);
				caseStat();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(291);
				expr(0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(292);
				match(NL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayExprContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(OCTAVEParser.LBRACK, 0); }
		public ExprArrayListContext exprArrayList() {
			return getRuleContext(ExprArrayListContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(OCTAVEParser.RBRACK, 0); }
		public ArrayExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitArrayExpr(this);
		}
	}

	public final ArrayExprContext arrayExpr() throws RecognitionException {
		ArrayExprContext _localctx = new ArrayExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrayExpr);
		try {
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				match(LBRACK);
				setState(296);
				exprArrayList();
				setState(297);
				match(RBRACK);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(299);
				match(LBRACK);
				setState(300);
				match(RBRACK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CellExprContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(OCTAVEParser.LBRACE, 0); }
		public ExprArrayListContext exprArrayList() {
			return getRuleContext(ExprArrayListContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(OCTAVEParser.RBRACE, 0); }
		public CellExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cellExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterCellExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitCellExpr(this);
		}
	}

	public final CellExprContext cellExpr() throws RecognitionException {
		CellExprContext _localctx = new CellExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_cellExpr);
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				match(LBRACE);
				setState(304);
				exprArrayList();
				setState(305);
				match(RBRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				match(LBRACE);
				setState(308);
				match(RBRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(OCTAVEParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(OCTAVEParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(OCTAVEParser.NOT, 0); }
		public DotRefContext dotRef() {
			return getRuleContext(DotRefContext.class,0);
		}
		public TerminalNode INT() { return getToken(OCTAVEParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(OCTAVEParser.FLOAT, 0); }
		public TerminalNode SCI() { return getToken(OCTAVEParser.SCI, 0); }
		public TerminalNode STRING() { return getToken(OCTAVEParser.STRING, 0); }
		public ArrayExprContext arrayExpr() {
			return getRuleContext(ArrayExprContext.class,0);
		}
		public CellExprContext cellExpr() {
			return getRuleContext(CellExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(OCTAVEParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(OCTAVEParser.RPAREN, 0); }
		public TerminalNode MPOW() { return getToken(OCTAVEParser.MPOW, 0); }
		public TerminalNode POW() { return getToken(OCTAVEParser.POW, 0); }
		public TerminalNode MTIMES() { return getToken(OCTAVEParser.MTIMES, 0); }
		public TerminalNode TIMES() { return getToken(OCTAVEParser.TIMES, 0); }
		public TerminalNode MLDIVIDE() { return getToken(OCTAVEParser.MLDIVIDE, 0); }
		public TerminalNode LDIVIDE() { return getToken(OCTAVEParser.LDIVIDE, 0); }
		public TerminalNode MRDIVIDE() { return getToken(OCTAVEParser.MRDIVIDE, 0); }
		public TerminalNode RDIVIDE() { return getToken(OCTAVEParser.RDIVIDE, 0); }
		public TerminalNode COLON() { return getToken(OCTAVEParser.COLON, 0); }
		public TerminalNode EQUALTO() { return getToken(OCTAVEParser.EQUALTO, 0); }
		public TerminalNode NOTEQUAL() { return getToken(OCTAVEParser.NOTEQUAL, 0); }
		public TerminalNode GT() { return getToken(OCTAVEParser.GT, 0); }
		public TerminalNode LT() { return getToken(OCTAVEParser.LT, 0); }
		public TerminalNode GE() { return getToken(OCTAVEParser.GE, 0); }
		public TerminalNode LE() { return getToken(OCTAVEParser.LE, 0); }
		public TerminalNode VECAND() { return getToken(OCTAVEParser.VECAND, 0); }
		public TerminalNode VECOR() { return getToken(OCTAVEParser.VECOR, 0); }
		public TerminalNode SCALAND() { return getToken(OCTAVEParser.SCALAND, 0); }
		public TerminalNode SCALOR() { return getToken(OCTAVEParser.SCALOR, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode TRANS() { return getToken(OCTAVEParser.TRANS, 0); }
		public TerminalNode CTRANS() { return getToken(OCTAVEParser.CTRANS, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case NOT:
				{
				setState(312);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(313);
				expr(17);
				}
				break;
			case ID:
				{
				setState(314);
				dotRef();
				}
				break;
			case INT:
				{
				setState(315);
				match(INT);
				}
				break;
			case FLOAT:
				{
				setState(316);
				match(FLOAT);
				}
				break;
			case SCI:
				{
				setState(317);
				match(SCI);
				}
				break;
			case STRING:
				{
				setState(318);
				match(STRING);
				}
				break;
			case LBRACK:
				{
				setState(319);
				arrayExpr();
				}
				break;
			case LBRACE:
				{
				setState(320);
				cellExpr();
				}
				break;
			case LPAREN:
				{
				setState(321);
				match(LPAREN);
				setState(322);
				expr(0);
				setState(323);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(363);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(361);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(327);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(328);
						_la = _input.LA(1);
						if ( !(_la==POW || _la==MPOW) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(329);
						expr(19);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(330);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(331);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MTIMES) | (1L << TIMES) | (1L << RDIVIDE) | (1L << LDIVIDE) | (1L << MRDIVIDE) | (1L << MLDIVIDE))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(332);
						expr(17);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(333);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(334);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(335);
						expr(16);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(336);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(337);
						match(COLON);
						setState(338);
						expr(15);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(339);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(340);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALTO) | (1L << NOTEQUAL) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE) | (1L << NOT))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(341);
						expr(14);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(342);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(343);
						match(VECAND);
						setState(344);
						expr(13);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(345);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(346);
						match(VECOR);
						setState(347);
						expr(12);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(348);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(349);
						match(SCALAND);
						setState(350);
						expr(11);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(351);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(352);
						match(SCALOR);
						setState(353);
						expr(10);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(354);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(355);
						match(LPAREN);
						setState(356);
						exprList();
						setState(357);
						match(RPAREN);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(359);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(360);
						_la = _input.LA(1);
						if ( !(_la==TRANS || _la==CTRANS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(365);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitExprList(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			expr(0);
			setState(371);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(367);
				match(COMMA);
				setState(368);
				expr(0);
				}
				}
				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprArrayListContext extends ParserRuleContext {
		public ExprArrayListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprArrayList; }
	 
		public ExprArrayListContext() { }
		public void copyFrom(ExprArrayListContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VcatContext extends ExprArrayListContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ExprArrayListContext> exprArrayList() {
			return getRuleContexts(ExprArrayListContext.class);
		}
		public ExprArrayListContext exprArrayList(int i) {
			return getRuleContext(ExprArrayListContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(OCTAVEParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(OCTAVEParser.SEMI, i);
		}
		public List<TerminalNode> NL() { return getTokens(OCTAVEParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(OCTAVEParser.NL, i);
		}
		public VcatContext(ExprArrayListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterVcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitVcat(this);
		}
	}
	public static class HcatContext extends ExprArrayListContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ExprArrayListContext> exprArrayList() {
			return getRuleContexts(ExprArrayListContext.class);
		}
		public ExprArrayListContext exprArrayList(int i) {
			return getRuleContext(ExprArrayListContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(OCTAVEParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OCTAVEParser.COMMA, i);
		}
		public HcatContext(ExprArrayListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).enterHcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OCTAVEListener ) ((OCTAVEListener)listener).exitHcat(this);
		}
	}

	public final ExprArrayListContext exprArrayList() throws RecognitionException {
		ExprArrayListContext _localctx = new ExprArrayListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_exprArrayList);
		int _la;
		try {
			int _alt;
			setState(392);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				_localctx = new HcatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				expr(0);
				setState(381);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(376);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(375);
							match(COMMA);
							}
						}

						setState(378);
						exprArrayList();
						}
						} 
					}
					setState(383);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
				}
				}
				break;
			case 2:
				_localctx = new VcatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				expr(0);
				setState(389);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(385);
						_la = _input.LA(1);
						if ( !(_la==NL || _la==SEMI) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(386);
						exprArrayList();
						}
						} 
					}
					setState(391);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 18);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 9);
		case 9:
			return precpred(_ctx, 20);
		case 10:
			return precpred(_ctx, 19);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\38\u018d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\5"+
		"\2\63\n\2\3\2\7\2\66\n\2\f\2\16\29\13\2\3\2\7\2<\n\2\f\2\16\2?\13\2\5"+
		"\2A\n\2\3\2\6\2D\n\2\r\2\16\2E\3\2\6\2I\n\2\r\2\16\2J\3\2\5\2N\n\2\3\3"+
		"\3\3\7\3R\n\3\f\3\16\3U\13\3\3\4\6\4X\n\4\r\4\16\4Y\3\5\3\5\5\5^\n\5\3"+
		"\5\3\5\5\5b\n\5\3\5\3\5\7\5f\n\5\f\5\16\5i\13\5\3\6\3\6\3\6\3\6\7\6o\n"+
		"\6\f\6\16\6r\13\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b}\n\b\f\b\16"+
		"\b\u0080\13\b\3\b\3\b\3\b\5\b\u0085\n\b\3\b\7\b\u0088\n\b\f\b\16\b\u008b"+
		"\13\b\3\t\3\t\3\t\7\t\u0090\n\t\f\t\16\t\u0093\13\t\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\7\n\u009b\n\n\f\n\16\n\u009e\13\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\7\13\u00a9\n\13\f\13\16\13\u00ac\13\13\3\13\3\13\5\13\u00b0"+
		"\n\13\3\f\3\f\3\f\3\f\7\f\u00b6\n\f\f\f\16\f\u00b9\13\f\3\f\3\f\3\f\5"+
		"\f\u00be\n\f\3\r\3\r\3\r\5\r\u00c3\n\r\3\r\3\r\3\16\3\16\3\16\7\16\u00ca"+
		"\n\16\f\16\16\16\u00cd\13\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00d6"+
		"\n\20\f\20\16\20\u00d9\13\20\3\20\3\20\3\20\3\20\7\20\u00df\n\20\f\20"+
		"\16\20\u00e2\13\20\7\20\u00e4\n\20\f\20\16\20\u00e7\13\20\3\20\3\20\5"+
		"\20\u00eb\n\20\3\20\7\20\u00ee\n\20\f\20\16\20\u00f1\13\20\5\20\u00f3"+
		"\n\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u00fb\n\21\f\21\16\21\u00fe\13"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0109\n\22\f\22"+
		"\16\22\u010c\13\22\7\22\u010e\n\22\f\22\16\22\u0111\13\22\3\22\3\22\3"+
		"\22\7\22\u0116\n\22\f\22\16\22\u0119\13\22\5\22\u011b\n\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0128\n\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u0130\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u0138\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\5\26\u0148\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u016c\n\26"+
		"\f\26\16\26\u016f\13\26\3\27\3\27\3\27\7\27\u0174\n\27\f\27\16\27\u0177"+
		"\13\27\3\30\3\30\5\30\u017b\n\30\3\30\7\30\u017e\n\30\f\30\16\30\u0181"+
		"\13\30\3\30\3\30\3\30\7\30\u0186\n\30\f\30\16\30\u0189\13\30\5\30\u018b"+
		"\n\30\3\30\2\3*\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\n"+
		"\4\2--\66\67\4\2\26\27))\3\2\'(\3\2!&\3\2\26\27\4\2\20\25))\3\2+,\4\2"+
		"--\67\67\u01bb\2M\3\2\2\2\4O\3\2\2\2\6W\3\2\2\2\b[\3\2\2\2\nj\3\2\2\2"+
		"\fs\3\2\2\2\16w\3\2\2\2\20\u008c\3\2\2\2\22\u0097\3\2\2\2\24\u00af\3\2"+
		"\2\2\26\u00bd\3\2\2\2\30\u00bf\3\2\2\2\32\u00c6\3\2\2\2\34\u00ce\3\2\2"+
		"\2\36\u00d1\3\2\2\2 \u00f6\3\2\2\2\"\u0101\3\2\2\2$\u0127\3\2\2\2&\u012f"+
		"\3\2\2\2(\u0137\3\2\2\2*\u0147\3\2\2\2,\u0170\3\2\2\2.\u018a\3\2\2\2\60"+
		"\63\5\n\6\2\61\63\5\16\b\2\62\60\3\2\2\2\62\61\3\2\2\2\62\63\3\2\2\2\63"+
		"@\3\2\2\2\64\66\5\n\6\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3"+
		"\2\2\28A\3\2\2\29\67\3\2\2\2:<\5\b\5\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2="+
		">\3\2\2\2>A\3\2\2\2?=\3\2\2\2@\67\3\2\2\2@=\3\2\2\2AN\3\2\2\2BD\5\b\5"+
		"\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FN\3\2\2\2GI\5\34\17\2HG\3\2"+
		"\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LN\7\2\2\3M\62\3\2\2\2MC\3"+
		"\2\2\2MH\3\2\2\2ML\3\2\2\2N\3\3\2\2\2OS\t\2\2\2PR\7-\2\2QP\3\2\2\2RU\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2T\5\3\2\2\2US\3\2\2\2VX\7-\2\2WV\3\2\2\2XY\3"+
		"\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\7\3\2\2\2[]\7\3\2\2\\^\5\24\13\2]\\\3\2\2"+
		"\2]^\3\2\2\2^_\3\2\2\2_a\7\62\2\2`b\5\26\f\2a`\3\2\2\2ab\3\2\2\2bc\3\2"+
		"\2\2cg\5\4\3\2df\5\34\17\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\t"+
		"\3\2\2\2ig\3\2\2\2jk\5\b\5\2kl\7\7\2\2lp\5\6\4\2mo\7-\2\2nm\3\2\2\2or"+
		"\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\13\3\2\2\2rp\3\2\2\2st\5\b\5\2tu\7\7\2\2"+
		"uv\5\4\3\2v\r\3\2\2\2wx\7\4\2\2xy\7\62\2\2y~\5\4\3\2z}\5\20\t\2{}\5\22"+
		"\n\2|z\3\2\2\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081"+
		"\3\2\2\2\u0080~\3\2\2\2\u0081\u0084\7\7\2\2\u0082\u0085\7\2\2\3\u0083"+
		"\u0085\5\4\3\2\u0084\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\u0089\3\2"+
		"\2\2\u0086\u0088\7-\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\17\3\2\2\2\u008b\u0089\3\2\2"+
		"\2\u008c\u008d\7\5\2\2\u008d\u0091\5\4\3\2\u008e\u0090\5\30\r\2\u008f"+
		"\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7\7\2\2\u0095"+
		"\u0096\5\4\3\2\u0096\21\3\2\2\2\u0097\u0098\7\6\2\2\u0098\u009c\5\4\3"+
		"\2\u0099\u009b\5\f\7\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f"+
		"\u00a0\7\7\2\2\u00a0\u00a1\5\4\3\2\u00a1\23\3\2\2\2\u00a2\u00a3\7\62\2"+
		"\2\u00a3\u00b0\7\17\2\2\u00a4\u00a5\7 \2\2\u00a5\u00aa\7\62\2\2\u00a6"+
		"\u00a7\7\66\2\2\u00a7\u00a9\7\62\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ac\3"+
		"\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00ae\7\64\2\2\u00ae\u00b0\7\17\2\2\u00af\u00a2\3"+
		"\2\2\2\u00af\u00a4\3\2\2\2\u00b0\25\3\2\2\2\u00b1\u00b2\7\35\2\2\u00b2"+
		"\u00b7\7\62\2\2\u00b3\u00b4\7\66\2\2\u00b4\u00b6\7\62\2\2\u00b5\u00b3"+
		"\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00be\7\36\2\2\u00bb\u00bc\7"+
		"\35\2\2\u00bc\u00be\7\36\2\2\u00bd\u00b1\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"+
		"\27\3\2\2\2\u00bf\u00c2\7\62\2\2\u00c0\u00c1\7\17\2\2\u00c1\u00c3\5*\26"+
		"\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5"+
		"\5\4\3\2\u00c5\31\3\2\2\2\u00c6\u00cb\7\62\2\2\u00c7\u00c8\7\30\2\2\u00c8"+
		"\u00ca\7\62\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3"+
		"\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\33\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce"+
		"\u00cf\5$\23\2\u00cf\u00d0\5\4\3\2\u00d0\35\3\2\2\2\u00d1\u00d2\7\b\2"+
		"\2\u00d2\u00d3\5*\26\2\u00d3\u00d7\5\4\3\2\u00d4\u00d6\5\34\17\2\u00d5"+
		"\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00e5\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\7\t\2\2\u00db"+
		"\u00dc\5*\26\2\u00dc\u00e0\5\4\3\2\u00dd\u00df\5\34\17\2\u00de\u00dd\3"+
		"\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00da\3\2\2\2\u00e4\u00e7\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00f2\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e8\u00ea\7\n\2\2\u00e9\u00eb\5\4\3\2\u00ea\u00e9\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ef\3\2\2\2\u00ec\u00ee\5\34\17\2\u00ed"+
		"\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00e8\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\7\7\2\2\u00f5\37\3\2\2"+
		"\2\u00f6\u00f7\7\13\2\2\u00f7\u00f8\5*\26\2\u00f8\u00fc\5\4\3\2\u00f9"+
		"\u00fb\5\34\17\2\u00fa\u00f9\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3"+
		"\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff"+
		"\u0100\7\7\2\2\u0100!\3\2\2\2\u0101\u0102\7\f\2\2\u0102\u0103\5*\26\2"+
		"\u0103\u010f\5\4\3\2\u0104\u0105\7\r\2\2\u0105\u0106\5*\26\2\u0106\u010a"+
		"\5\4\3\2\u0107\u0109\5\34\17\2\u0108\u0107\3\2\2\2\u0109\u010c\3\2\2\2"+
		"\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a"+
		"\3\2\2\2\u010d\u0104\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u011a\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7\16"+
		"\2\2\u0113\u0117\5\4\3\2\u0114\u0116\5\34\17\2\u0115\u0114\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011b\3\2"+
		"\2\2\u0119\u0117\3\2\2\2\u011a\u0112\3\2\2\2\u011a\u011b\3\2\2\2\u011b"+
		"\u011c\3\2\2\2\u011c\u011d\7\7\2\2\u011d#\3\2\2\2\u011e\u011f\5\32\16"+
		"\2\u011f\u0120\7\17\2\2\u0120\u0121\5*\26\2\u0121\u0128\3\2\2\2\u0122"+
		"\u0128\5\36\20\2\u0123\u0128\5 \21\2\u0124\u0128\5\"\22\2\u0125\u0128"+
		"\5*\26\2\u0126\u0128\7-\2\2\u0127\u011e\3\2\2\2\u0127\u0122\3\2\2\2\u0127"+
		"\u0123\3\2\2\2\u0127\u0124\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2"+
		"\2\2\u0128%\3\2\2\2\u0129\u012a\7 \2\2\u012a\u012b\5.\30\2\u012b\u012c"+
		"\7\64\2\2\u012c\u0130\3\2\2\2\u012d\u012e\7 \2\2\u012e\u0130\7\64\2\2"+
		"\u012f\u0129\3\2\2\2\u012f\u012d\3\2\2\2\u0130\'\3\2\2\2\u0131\u0132\7"+
		"\37\2\2\u0132\u0133\5.\30\2\u0133\u0134\7\65\2\2\u0134\u0138\3\2\2\2\u0135"+
		"\u0136\7\37\2\2\u0136\u0138\7\65\2\2\u0137\u0131\3\2\2\2\u0137\u0135\3"+
		"\2\2\2\u0138)\3\2\2\2\u0139\u013a\b\26\1\2\u013a\u013b\t\3\2\2\u013b\u0148"+
		"\5*\26\23\u013c\u0148\5\32\16\2\u013d\u0148\7/\2\2\u013e\u0148\7\60\2"+
		"\2\u013f\u0148\7\61\2\2\u0140\u0148\7\63\2\2\u0141\u0148\5&\24\2\u0142"+
		"\u0148\5(\25\2\u0143\u0144\7\35\2\2\u0144\u0145\5*\26\2\u0145\u0146\7"+
		"\36\2\2\u0146\u0148\3\2\2\2\u0147\u0139\3\2\2\2\u0147\u013c\3\2\2\2\u0147"+
		"\u013d\3\2\2\2\u0147\u013e\3\2\2\2\u0147\u013f\3\2\2\2\u0147\u0140\3\2"+
		"\2\2\u0147\u0141\3\2\2\2\u0147\u0142\3\2\2\2\u0147\u0143\3\2\2\2\u0148"+
		"\u016d\3\2\2\2\u0149\u014a\f\24\2\2\u014a\u014b\t\4\2\2\u014b\u016c\5"+
		"*\26\25\u014c\u014d\f\22\2\2\u014d\u014e\t\5\2\2\u014e\u016c\5*\26\23"+
		"\u014f\u0150\f\21\2\2\u0150\u0151\t\6\2\2\u0151\u016c\5*\26\22\u0152\u0153"+
		"\f\20\2\2\u0153\u0154\7*\2\2\u0154\u016c\5*\26\21\u0155\u0156\f\17\2\2"+
		"\u0156\u0157\t\7\2\2\u0157\u016c\5*\26\20\u0158\u0159\f\16\2\2\u0159\u015a"+
		"\7\31\2\2\u015a\u016c\5*\26\17\u015b\u015c\f\r\2\2\u015c\u015d\7\32\2"+
		"\2\u015d\u016c\5*\26\16\u015e\u015f\f\f\2\2\u015f\u0160\7\33\2\2\u0160"+
		"\u016c\5*\26\r\u0161\u0162\f\13\2\2\u0162\u0163\7\34\2\2\u0163\u016c\5"+
		"*\26\f\u0164\u0165\f\26\2\2\u0165\u0166\7\35\2\2\u0166\u0167\5,\27\2\u0167"+
		"\u0168\7\36\2\2\u0168\u016c\3\2\2\2\u0169\u016a\f\25\2\2\u016a\u016c\t"+
		"\b\2\2\u016b\u0149\3\2\2\2\u016b\u014c\3\2\2\2\u016b\u014f\3\2\2\2\u016b"+
		"\u0152\3\2\2\2\u016b\u0155\3\2\2\2\u016b\u0158\3\2\2\2\u016b\u015b\3\2"+
		"\2\2\u016b\u015e\3\2\2\2\u016b\u0161\3\2\2\2\u016b\u0164\3\2\2\2\u016b"+
		"\u0169\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016e+\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0175\5*\26\2\u0171\u0172"+
		"\7\66\2\2\u0172\u0174\5*\26\2\u0173\u0171\3\2\2\2\u0174\u0177\3\2\2\2"+
		"\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176-\3\2\2\2\u0177\u0175\3"+
		"\2\2\2\u0178\u017f\5*\26\2\u0179\u017b\7\66\2\2\u017a\u0179\3\2\2\2\u017a"+
		"\u017b\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017e\5.\30\2\u017d\u017a\3\2"+
		"\2\2\u017e\u0181\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180"+
		"\u018b\3\2\2\2\u0181\u017f\3\2\2\2\u0182\u0187\5*\26\2\u0183\u0184\t\t"+
		"\2\2\u0184\u0186\5.\30\2\u0185\u0183\3\2\2\2\u0186\u0189\3\2\2\2\u0187"+
		"\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2"+
		"\2\2\u018a\u0178\3\2\2\2\u018a\u0182\3\2\2\2\u018b/\3\2\2\2\61\62\67="+
		"@EJMSY]agp|~\u0084\u0089\u0091\u009c\u00aa\u00af\u00b7\u00bd\u00c2\u00cb"+
		"\u00d7\u00e0\u00e5\u00ea\u00ef\u00f2\u00fc\u010a\u010f\u0117\u011a\u0127"+
		"\u012f\u0137\u0147\u016b\u016d\u0175\u017a\u017f\u0187\u018a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}