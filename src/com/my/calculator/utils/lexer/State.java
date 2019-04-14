package com.my.calculator.utils.lexer;

import com.my.calculator.utils.ParserException;

public interface State {
    boolean isStartsWith(char ch);
    boolean next(char ch) throws ParserException;
    Token build() throws ParserException;
}
