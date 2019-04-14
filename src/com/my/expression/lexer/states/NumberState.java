package com.my.expression.lexer.states;

import com.my.expression.lexer.LexerException;
import com.my.expression.lexer.State;
import com.my.expression.lexer.Token;
import com.my.expression.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字识别状态
 */
public class NumberState implements State {

    private final StringBuffer buffer = new StringBuffer();

    private final List<Character> chars = new ArrayList<>();
    private boolean addedPoint = false;

    @Override
    public boolean isStartsWith(char ch) {
        return Character.isDigit(ch);
    }

    @Override
    public boolean next(char ch) throws LexerException {
        if (Character.isDigit(ch)) {
            chars.add(ch);
            return true;
        } if (ch == '.') {
            if (!addedPoint) {
                addedPoint = true;
                chars.add(ch);
                return true;
            } else {
                chars.add(ch);
                throw new LexerException("错误的数字格式: " + getString());
            }
        }else {
            return false;
        }
    }

    @Override
    public Token build() {

        String str = getString();
        chars.clear();
        addedPoint = false;

        return new Token(TokenType.NUM, str);
    }

    private String getString() {
        buffer.setLength(0);
        for (char ch : chars) {
            buffer.append(ch);
        }
        return buffer.toString();
    }
}
