package com.my.calculator.utils;

import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

import static com.my.calculator.utils.lexer.TokenType.BRACKET_LEFT;
import static com.my.calculator.utils.lexer.TokenType.BRACKET_RIGHT;

public class PriorityHandler {

    public List<Token> handle(List<Token> tokens) throws ParserException {
        List<Token> target = new ArrayList<>();
        repeatL(target, 4);
        tokens.forEach(token -> {
            TokenType type = token.getType();
            if (type == BRACKET_LEFT) {
                repeatL(target, 4);
            } else if (type == BRACKET_RIGHT) {
                repeatR(target, 4);
            } else {
                repeatR(target, token.getType().getPriority());
                target.add(token);
                repeatL(target, token.getType().getPriority());
            }
        });
        repeatR(target, 4);
        return target;
    }

    private void repeatR(List<Token> target, int num) {
        for (int i = 0; i < num; i++) {
            target.add(Token.getToken(BRACKET_RIGHT));
        }
    }

    private void repeatL(List<Token> target, int num) {
        for (int i = 0; i < num; i++) {
            target.add(Token.getToken(BRACKET_LEFT));
        }
    }
}
