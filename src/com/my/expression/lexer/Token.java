package com.my.expression.lexer;

/**
 * 标记
 */
public class Token {

    private final TokenType type; // 标记类型
    private final String value; // 标记值

    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * 获取标记类型
     */
    public TokenType getType() {
        return type;
    }

    /**
     * 获取标记值
     */
    public String getValue() {
        return value;
    }

    /**
     * 获取标记符号
     */
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
        } else if (type == TokenType.WORD || type == TokenType.OPERATOR) {
            return "<" + type + ", \"" + value + "\">";
        } else {
            return "<" + type + ">";
        }
    }
}