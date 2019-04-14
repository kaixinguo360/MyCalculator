package com.my.calculator.utils.lexer;

import java.util.List;

public class Token {

    private final static Token[] tokenPool = new Token[] {
            new Token(TokenType.ADD),
            new Token(TokenType.SUB),
            new Token(TokenType.MUL),
            new Token(TokenType.DIV),
            new Token(TokenType.POW),
            new Token(TokenType.BRACKET_LEFT),
            new Token(TokenType.BRACKET_RIGHT)
    };

    private final TokenType type;
    private final String value;

    private Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    private Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getSymbol() {
        if (type == TokenType.NUM || type == TokenType.WORD) {
            return value;
        } else {
            return type.getSymbol();
        }
    }

    @Override
    public String toString() {
        if (type == TokenType.NUM) {
            return "<" + type + ", " + value + ">";
        } else if (type == TokenType.WORD) {
            return "<" + type + ", \"" + value + "\">";
        } else {
            return "<" + type + ">";
        }
    }

    public static Token getToken(TokenType type) {
        return getToken(type, null);
    }

    public static Token getToken(TokenType type, String value) {
        for (Token token : tokenPool) {
            if (token.getType() == type) {
                return token;
            }
        }
        return new Token(type, value);
    }

    private static final StringBuffer buffer = new StringBuffer();
    public static String getString(List<Token> tokens) {
        buffer.setLength(0);
        tokens.forEach(token -> buffer.append(token.getSymbol()));
        return buffer.toString();
    }
}