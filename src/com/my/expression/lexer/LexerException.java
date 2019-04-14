package com.my.expression.lexer;

/**
 * 词法分析器异常
 * 在词法分析过程中产生的错误
 */
public class LexerException extends Exception {

    public LexerException(String message) {
        super(message);
    }
}
