package com.my.calculator.utils.lexer.states;

import com.my.calculator.utils.lexer.State;
import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class LetterState implements State {

    private final StringBuffer buffer = new StringBuffer();

    private final List<Character> chars = new ArrayList<>();

    @Override
    public boolean isStartsWith(char ch) {
        return Character.isLetter(ch);
    }

    @Override
    public boolean next(char ch) {
        if (Character.isLetter(ch) || Character.isDigit(ch)) {
            chars.add(ch);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Token build() {

        buffer.setLength(0);
        for (char ch : chars) {
            buffer.append(ch);
        }
        String str = buffer.toString();

        chars.clear();

        return Token.getToken(TokenType.WORD, str);
    }
}
