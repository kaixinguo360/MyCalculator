package com.my.expression.lexer.states;

import com.my.expression.lexer.State;
import com.my.expression.lexer.Token;

/**
 * 空白字符识别状态
 */
public class SpaceState implements State {

    @Override
    public boolean isStartsWith(char ch) {
        return Character.isWhitespace(ch);
    }

    @Override
    public boolean next(char ch) {
        return Character.isWhitespace(ch);
    }

    @Override
    public Token build() {
        return null;
    }
}
