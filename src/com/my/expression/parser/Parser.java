package com.my.expression.parser;

import com.my.expression.Context;
import com.my.expression.CustomOperator;
import com.my.expression.Expression;
import com.my.expression.lexer.Token;
import com.my.expression.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 语法分析器
 * 将输入标记流编译为可运行表达式
 */
public class Parser {

    private Context context; // 编译上下文

    public Parser(Context context) {
        this.context = context;
    }

    /**
     * 将输入标记流编译为可运行表达式
     */
    public Expression parse(ListIterator<Token> tokens) throws ParserException {
        return new Polynomial(tokens).getExpression();
    }

    /**
     * 多项表达式
     * 彼此之间由一个二元运算符分割的多个表达式
     */
    private class Polynomial {

        private List<Expression> expressions = new ArrayList<>(); // 各个表达式
        private List<String> operators = new ArrayList<>(); // 各个运算符

        private Polynomial(ListIterator<Token> tokens) throws ParserException {
            while (tokens.hasNext()) {
                Token token = tokens.next();
                switch (token.getType()) {
                    case WORD: {
                        String word = token.getValue();
                        if (context.isConstant(word)) {
                            Constant constant = new Constant(context.getConstant(word));
                            addExpression(constant);
                        } else if(context.isFunction(word)) {
                            addFunction(word, tokens);
                        } else {
                            throw new ParserException("未知的标识符: " + word);
                        }
                        break;
                    }
                    case OPERATOR: {
                        addOperator(token.getValue());
                        break;
                    }
                    case NUM: {
                        Constant constant = new Constant(token.getValue());
                        addExpression(constant);
                        break;
                    }
                    case BRACKET_LEFT: {
                        Expression expr = parse(tokens);
                        if (tokens.hasNext() && tokens.next().getType() == TokenType.BRACKET_RIGHT) {
                            addExpression(expr);
                        } else {
                            throw new ParserException("括号未关闭");
                        }
                        break;
                    }
                    case COMMA:
                    case BRACKET_RIGHT: {
                        tokens.previous();
                        return;
                    }
                }
            }
        }

        /**
         * 添加一个函数表达式
         */
        private void addFunction(String word, ListIterator<Token> tokens) throws ParserException {

            Function function = new Function(context.getFunction(word));
            addExpression(function);

            if (tokens.hasNext()) {
                Token token = tokens.next();
                if (token.getType() == TokenType.BRACKET_LEFT) {
                    while (tokens.hasNext()) {
                        function.addInput(parse(tokens));
                        if (tokens.hasNext()) {
                            token = tokens.next();
                            if (token.getType() == TokenType.COMMA) {
                                continue;
                            } else if (token.getType() == TokenType.BRACKET_RIGHT) {
                                return;
                            }
                        } else {
                            throw new ParserException("括号未关闭");
                        }
                    }
                } else {
                    throw new ParserException("非预期的标记: " + token.getSymbol());
                }
            } else {
                throw new ParserException("语句不完整");
            }
        }

        /**
         * 添加一个表达式
         */
        private void addExpression(Expression expression) throws ParserException {
            if (expressions.size() == operators.size()) {
                expressions.add(expression);
            } else {
                throw new ParserException("非预期的表达式");
            }
        }

        /**
         * 添加一个操作符
         */
        private void addOperator(String operator) throws ParserException {
            if (expressions.size() - 1 == operators.size()) {
                operators.add(operator);
            } else {
                throw new ParserException("非预期的操作符");
            }
        }

        /**
         * 将多项式转化为由多个二元运算符嵌套而成的二叉树型表达式
         */
        private Expression getExpression() throws ParserException {
            if (expressions.size() != 0 && expressions.size() - 1 == operators.size()) {
                for (CustomOperator operator : context.getOperators()) {
                    if (operator.order == CustomOperator.LEFT_TO_RIGHT) {
                        for (int i = operators.size() - 1; i >= 0; i--) {
                            String op = operators.get(i);
                            if (operator.name.equals(op)) {
                                mergeExpression(i, op);
                            }
                        }
                    } else {
                        for (int i = 0; i < operators.size(); i++) {
                            String op = operators.get(i);
                            if (operator.name.equals(op)) {
                                mergeExpression(i, op);
                                i--;
                            }
                        }
                    }
                }

                return expressions.get(0);
            } else {
                throw new ParserException("错误的多项式格式");
            }
        }

        /**
         * 合并相邻两项
         */
        private void mergeExpression(int index, String operator) {
            Function fun = new Function(context.getOperator(operator));
            Expression e1 = expressions.get(index);
            Expression e2 = expressions.get(index + 1);
            fun.addInput(e1, e2);
            expressions.remove(index);
            expressions.remove(index);
            operators.remove(index);
            expressions.add(index, fun);
        }
    }
}
