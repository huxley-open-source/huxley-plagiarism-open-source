grammar MATLAB;

fileDecl
    : (functionDecl | classDecl)? (functionDecl* | partialFunctionDecl*)
    | partialFunctionDecl+
    | statBlock+ // Script
    | EOF
    ;

endStat
    : (NL|COMMA|SEMI) NL*
    ;

endStatNL
    : NL+
    ;

// Function declaration without the closing end
partialFunctionDecl
    : FUNCTION outArgs? ID inArgs? endStat statBlock*
    ;

// Normal function declaration including closing end
functionDecl
    : partialFunctionDecl END endStatNL NL*
    ;

// Functions inside method blocks can be comma or semi separated
methodDecl
    : partialFunctionDecl END endStat
    ;

classDecl
    : CLASSDEF ID endStat
      (propBlockDecl|methodBlockDecl)*
      END (EOF|endStat) NL*
    ;

propBlockDecl
    : PROPERTIES endStat prop* END endStat
    ;

methodBlockDecl
    : METHODS endStat methodDecl* END endStat
    ;

outArgs
    : ID EQUALS
    | LBRACK ID (COMMA ID)* RBRACK EQUALS
    ;

inArgs
    : LPAREN ID (COMMA ID)* RPAREN
    | LPAREN RPAREN
    ;

prop
    : ID (EQUALS expr)? endStat
    ;

dotRef
    : ID (DOT ID)*
    ;

statBlock
    : (stat endStat)
    ;

ifStat
    : IF expr endStat statBlock*
      (ELSEIF expr endStat statBlock*)*
      (ELSE endStat? statBlock*)?
      END
    ;

whileStat
    : WHILE expr endStat statBlock* END
    ;

caseStat
    : SWITCH expr endStat
      (CASE expr endStat statBlock*)*
      (OTHERWISE endStat statBlock*)?
      END
    ;

stat
    : dotRef EQUALS expr
    | ifStat
    | whileStat
    | caseStat
    | expr
    | NL
    ;

arrayExpr
    : LBRACK exprArrayList RBRACK
    | LBRACK RBRACK
    ;

cellExpr
    : LBRACE exprArrayList RBRACE
    | LBRACE RBRACE
    ;

expr
    : expr LPAREN exprList RPAREN
    | expr (TRANS|CTRANS)
    | expr (MPOW|POW) expr
    | (PLUS|MINUS|NOT) expr
    | expr (MTIMES|TIMES|MLDIVIDE|LDIVIDE|MRDIVIDE|RDIVIDE) expr
    | expr (PLUS|MINUS) expr
    | expr COLON expr
    | expr (NOT|EQUALTO|NOTEQUAL|GT|LT|GE|LE) expr
    | expr VECAND expr
    | expr VECOR expr
    | expr SCALAND expr
    | expr SCALOR expr
    | dotRef
    | INT | FLOAT | SCI
    | STRING
    | arrayExpr
    | cellExpr
    | LPAREN expr RPAREN
    ;

exprList
    : expr (',' expr)*
    ;

exprArrayList
    : expr (COMMA? exprArrayList)*    #hcat
    | expr ((SEMI|NL) exprArrayList)* #vcat
    ;

// Keywords
FUNCTION : 'function' ;

CLASSDEF : 'classdef' ;

PROPERTIES : 'properties' ;

METHODS : 'methods' ;

END : 'end' ;

IF  : 'if' ;

ELSEIF : 'elseif' ;

ELSE : 'else' ;

WHILE : 'while' ;

SWITCH : 'switch' ;

CASE : 'case' ;

OTHERWISE : 'otherwise' ;

// Symbols
EQUALS : '=' ;

EQUALTO : '==' ;

NOTEQUAL : '!=' | '~=' ;

GT : '>' ;

LT : '<' ;

GE : '>=' ;

LE : '<=' ;

PLUS : '+' ;

MINUS : '-' ;

DOT : '.' ;

VECAND : '&' ;

VECOR : '|' ;

SCALAND : '&&' ;

SCALOR : '||' ;

LPAREN : '(' ;

RPAREN : ')' ;

LBRACE : '{' ;

// RBRACE : '}' ;

LBRACK : '[' ;

// RBRACK : ']' ;

MTIMES : '*' ;

TIMES : '.*' ;

RDIVIDE : '/' ;

LDIVIDE : '\\' ;

MRDIVIDE : './' ;

MLDIVIDE : '.\\' ;

POW : '.^' ;

MPOW : '^' ;

NOT : '~' ;

COLON : ':' ;

TRANS : '.\'' ;

CTRANS : '\'' ;

// General rules
NL  : '\r'?'\n' ;

fragment
LINECONTINUE
    : '...' ;

COMMENT
    : ('%' | LINECONTINUE) .*? NL -> skip ;

fragment
LETTER  : [a-zA-Z] ;
fragment
DIGIT   : [0-9] ;
fragment
ESC : '\'\'' ;

INT : DIGIT+;

FLOAT : DIGIT+ '.' DIGIT*
      | '.' DIGIT+
      ;

SCI : (INT|FLOAT) 'e' INT ;

ID  : LETTER (LETTER|DIGIT|'_')* ;
STRING : '\'' (ESC|.)*? '\'' ;

RBRACK : ']' ;
RBRACE : '}' ;

// HCAT : (COMMA); // | SPACE ) ;

// VCAT : (SEMI | NL ) ;

// ARRAYELSEP : (HCAT| VCAT ) ; // SPACE+ ;

COMMA : ',' ;

SEMI  : ';' ;

WS  : SPACE+ -> skip ;

fragment
SPACE : [ \t] ;