package com.huxley.plagiarism.tokenizer;

import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class MyErrorStrategy extends DefaultErrorStrategy implements ANTLRErrorStrategy {
	
    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw new RuntimeException(e);
    }

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        throw new RuntimeException(new InputMismatchException(recognizer));
    }
    
    @Override
    public void sync(Parser recognizer) {
    }

    @Override
    protected Token getMissingSymbol(Parser recognizer) {
        throw new RuntimeException(new InputMismatchException(recognizer));
    }
    
}
