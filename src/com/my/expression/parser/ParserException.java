package com.my.expression.parser;

/**
 * 语法分析器异常
 * 在语法分析过程中产生的错误
 */
public class ParserException extends Exception {

    public ParserException(String message) {
        super(message);
    }
}
