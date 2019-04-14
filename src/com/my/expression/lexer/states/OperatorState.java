package com.my.expression.lexer.states;

import com.my.expression.Context;
import com.my.expression.lexer.State;
import com.my.expression.lexer.Token;
import com.my.expression.lexer.TokenType;

/**
 * 运算符识别状态
 * 仅识别单字符的运算符
 */
public class OperatorState implements State {

    private String operator;
    private Context context;

    public OperatorState(Context context) {
        this.context = context;
    }

    @Override
    public boolean isStartsWith(char ch) {
        return context.isOperator(ch + "");
    }

    @Override
    public boolean next(char ch) {
        if (operator == null) {
            operator = ch + "";
            return true;
        }
        return false;
    }

    @Override
    public Token build() {
        String op = this.operator;
        this.operator = null;
        return new Token(TokenType.OPERATOR, op);
    }
}
