package com.my.calculator.utils.parser;

import com.my.calculator.utils.ParserException;
import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.lexer.TokenType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polynomial implements Expression {

    private List<Expression> values = new ArrayList<>();
    private List<TokenType> operators = new ArrayList<>();

    public Polynomial(Iterator<Token> tokens) throws ParserException {

        OUT:
        while (tokens.hasNext()) {
            Token token = tokens.next();
            switch (token.getType()) {
                case WORD: {
                    String word = token.getValue();
                    if (Constant.isConstant(word)) {
                        Constant constant = new Constant(Constant.getConstant(word));
                        addValue(constant);
                    } else if(Function.isFunction(word)) {
                        Function function = new Function(Function.getFunction(word), tokens);
                        addValue(function);
                    } else {
                        throw new ParserException("未知的标识符: " + word);
                    }
                    break;
                }
                case ADD: case SUB: case MUL: case DIV: case POW: {
                    addOperator(token.getType());
                    break;
                }
                case NUM: {
                    Constant constant = new Constant(token.getValue());
                    addValue(constant);
                    break;
                }
                case BRACKET_LEFT: {
                    Polynomial expression = new Polynomial(tokens);
                    addValue(expression);
                    break;
                }
                case BRACKET_RIGHT: {
                    break OUT;
                }
            }
        }
        if (values.size() == 0) {
            throw new ParserException("错误的多项式格式");
        }
    }

    private void addValue(Expression value) throws ParserException {
        if (values.size() == operators.size()) {
            values.add(value);
        } else {
            throw new ParserException("错误的多项式格式");
        }
    }

    private void addOperator(TokenType operator) throws ParserException {
        if (values.size() - 1 == operators.size()) {
            operators.add(operator);
        } else {
            throw new ParserException("错误的多项式格式");
        }
    }

    @Override
    public double getValue() {
        double result = values.get(0).getValue();
        for (int i = 1; i < values.size(); i++) {
            Expression value = values.get(i);
            TokenType operator = operators.get(i - 1);
            switch (operator) {
                case ADD:
                    result = result + value.getValue();
                    break;
                case SUB:
                    result = result - value.getValue();
                    break;
                case MUL:
                    result = result * value.getValue();
                    break;
                case DIV:
                    result = result / value.getValue();
                    break;
                case POW:
                    result = Math.pow(result, value.getValue());
                    break;
            }
        }
        return result;
    }
}
