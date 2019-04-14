package com.my.expression.lexer.states;

import com.my.expression.lexer.LexerException;
import com.my.expression.lexer.State;
import com.my.expression.lexer.Token;
import com.my.expression.lexer.TokenType;

/**
 * 关键字符识别状态
 * 仅识别三个字符: (  )  ,
 */
public class KeySymbolState implements State {

    private final static TokenType[] SYMBOLS = {
            TokenType.BRACKET_LEFT,
            TokenType.BRACKET_RIGHT,
            TokenType.COMMA
    };

    private String symbol = null;

    @Override
    public boolean isStartsWith(char ch) {
        for (TokenType type : SYMBOLS) {
            if (type.getSymbol().equals(ch + "")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean next(char ch) {
        if (symbol == null) {
            symbol = ch + "";
            return true;
        }
        return false;
    }

    @Override
    public Token build() throws LexerException {
        String symbol = this.symbol;
        this.symbol = null;
        for (TokenType type : SYMBOLS) {
            if (type.getSymbol().equals(symbol)) {
                return new Token(type);
            }
        }
        throw new LexerException("未知的符号: " + symbol);
    }
}
