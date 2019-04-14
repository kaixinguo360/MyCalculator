package com.my.calculator.utils.lexer.states;

import com.my.calculator.utils.lexer.State;
import com.my.calculator.utils.lexer.Token;

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
