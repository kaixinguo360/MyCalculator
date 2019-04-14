package com.my.calculator.utils.lexer;

public enum TokenType {

    // Operators
    ADD("+", 3),
    SUB("-", 3),
    MUL("*", 2),
    DIV("/", 2),
    POW("^", 1),

    // Brackets
    BRACKET_LEFT("("),
    BRACKET_RIGHT(")"),

    // Variables
    NUM("num"),
    WORD("word");


    private final String symbol;
    private final int priority;

    TokenType(String symbol) {
        this(symbol, 0);
    }

    TokenType(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }
}
