package com.my.calculator.utils.lexer.states;

import com.my.calculator.utils.lexer.State;
import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.lexer.TokenType;
import com.my.calculator.utils.ParserException;

import java.util.ArrayList;
import java.util.List;

public class NumberState implements State {

    private final StringBuffer buffer = new StringBuffer();

    private final List<Character> chars = new ArrayList<>();
    private boolean addedPoint = false;

    @Override
    public boolean isStartsWith(char ch) {
        return Character.isDigit(ch);
    }

    @Override
    public boolean next(char ch) throws ParserException {
        if (Character.isDigit(ch)) {
            chars.add(ch);
            return true;
        } if (ch == '.') {
            if (!addedPoint) {
                addedPoint = true;
                chars.add(ch);
                return true;
            } else {
                throw new ParserException("错误的数字格式");
            }
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

        chars.clear();
        addedPoint = false;

        return Token.getToken(TokenType.NUM, buffer.toString());
    }
}
