package com.my.calculator;

import com.my.expression.Compiler;
import com.my.expression.*;
import com.my.expression.lexer.LexerException;
import com.my.expression.lexer.Token;
import com.my.expression.lexer.TokenType;
import com.my.expression.parser.ParserException;

import static com.my.expression.CustomOperator.RIGHT_TO_LEFT;

/**
 * 文本界面的计算器
 */
public class StringCalculator {

    private Compiler compiler = new Compiler(); // 表达式编译器
    private Context context = compiler.getContext(); // 编译上下文

    public StringCalculator() {
        addConstants();
        addFunctions();
        addOperators();
        compiler.setTokenFilter(tokens -> { // 添加标记过滤器, 解决正负号问题
            for (int i = 0; i < tokens.size(); i++) {
                if ("-".equals(tokens.get(i).getValue()) || "+".equals(tokens.get(i).getValue())) {
                    if (i == 0 || tokens.get(i - 1).getType() == TokenType.OPERATOR
                            || tokens.get(i - 1).getType() == TokenType.BRACKET_LEFT) {
                        tokens.add(i, new Token(TokenType.NUM, "0"));
                        i++;
                    }
                }
            }
            return tokens;
        });
    }

    // 添加常量
    private void addConstants() {
        context.addConstant("pi", Math.PI);
        context.addConstant("e", Math.E);
    }
    // 添加函数
    private void addFunctions() {
        context.addFunction(new CustomFunction("sin", ins -> Math.sin(ins[0])));
        context.addFunction(new CustomFunction("cos", ins -> Math.cos(ins[0])));
        context.addFunction(new CustomFunction("tan", ins -> Math.tan(ins[0])));
        context.addFunction(new CustomFunction("cot", ins -> 1 / Math.tan(ins[0])));
        context.addFunction(new CustomFunction("ln", ins -> Math.log(ins[0])));
        context.addFunction(new CustomFunction("lg", ins -> Math.log10(ins[0])));
        context.addFunction(new CustomFunction("abs", ins -> Math.abs(ins[0])));
        context.addFunction(new CustomFunction("asin", ins -> Math.asin(ins[0])));
        context.addFunction(new CustomFunction("acos", ins -> Math.acos(ins[0])));
        context.addFunction(new CustomFunction("atan", ins -> Math.atan(ins[0])));
        context.addFunction(new CustomFunction("exp", ins -> Math.exp(ins[0])));
        context.addFunction(new CustomFunction("sqrt", ins -> Math.sqrt(ins[0])));
        context.addFunction(new CustomFunction("max", 2, ins -> Math.max(ins[0], ins[1])));
        context.addFunction(new CustomFunction("min", 2, ins -> Math.min(ins[0], ins[1])));
    }
    // 添加运算符
    private void addOperators() {
        context.addOperator(new CustomOperator("^", RIGHT_TO_LEFT, ins -> Math.pow(ins[0], ins[1])));
        context.addOperator(new CustomOperator("%", ins -> ins[0] % ins[1]));
        context.addOperator(new CustomOperator("mod", ins -> ins[0] % ins[1]));
        context.addOperator(new CustomOperator("/", ins -> ins[0] / ins[1]));
        context.addOperator(new CustomOperator("*", ins -> ins[0] * ins[1]));
        context.addOperator(new CustomOperator("-", ins -> ins[0] - ins[1]));
        context.addOperator(new CustomOperator("+", ins -> ins[0] + ins[1]));
    }

    /**
     * 计算输入的表达式
     */
    public String calculate(String input) {
        try {
            Expression expr = compiler.compile(input);
            return String.format("%f", expr.getValue());
        } catch (LexerException | ParserException | CalculateException e) {
            return e.getMessage();
        }
    }
}
