package com.my.calculator.utils.lexer.states;

import com.my.calculator.utils.lexer.State;
import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.lexer.TokenType;
import com.my.calculator.utils.ParserException;

public class OperatorState implements State {

    private static final TokenType[] OPERATORS = new TokenType[]{
            TokenType.ADD,
            TokenType.SUB,
            TokenType.MUL,
            TokenType.DIV,
            TokenType.POW,
            TokenType.BRACKET_LEFT,
            TokenType.BRACKET_RIGHT
    };

    private Character character;

    @Override
    public boolean isStartsWith(char ch) {
        for (TokenType operator : OPERATORS) {
            if (operator.getSymbol().equals(ch + "")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean next(char ch) {
        if (character == null) {
            character = ch;
            return true;
        }
        return false;
    }

    @Override
    public Token build() throws ParserException {
        char ch = character;
        character = null;

        for (TokenType operator : OPERATORS) {
            if (operator.getSymbol().equals(ch + "")) {
                return Token.getToken(operator);
            }
        }
        throw new ParserException("未知操作符: " + ch);
    }
}
