package com.my.expression.lexer;

/**
 * 标记类型
 */
public enum TokenType {

    // 关键符号 (仅三个) //
    BRACKET_LEFT("("), // 左括号
    BRACKET_RIGHT(")"), // 右括号
    COMMA(","), // 逗号 (分割函数输入参数)

    // 自定义类型 //
    OPERATOR("operator"), // 运算符 (自定义运算符)
    NUM("num"), // 数字 (常量)
    WORD("word"); // 单词 (自定义函数名)


    private final String symbol; // 类型符号

    TokenType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
