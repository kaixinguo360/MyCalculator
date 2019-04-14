package com.my.expression.lexer.states;

import com.my.expression.Context;
import com.my.expression.lexer.State;
import com.my.expression.lexer.Token;
import com.my.expression.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词识别状态
 */
public class LetterState implements State {

    private final List<Character> chars = new ArrayList<>();
    private Context context;

    public LetterState(Context context) {
        this.context = context;
    }

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

    private final StringBuffer buffer = new StringBuffer();
    @Override
    public Token build() {

        buffer.setLength(0);
        for (char ch : chars) {
            buffer.append(ch);
        }
        String str = buffer.toString();
        chars.clear();

        return new Token(context.isOperator(str) ? TokenType.OPERATOR : TokenType.WORD, str);
    }
}
