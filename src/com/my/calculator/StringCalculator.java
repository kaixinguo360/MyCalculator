package com.my.calculator;

import com.my.calculator.utils.ParserException;
import com.my.calculator.utils.PriorityHandler;
import com.my.calculator.utils.lexer.Lexer;
import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.parser.Constant;
import com.my.calculator.utils.parser.Function;
import com.my.calculator.utils.parser.Polynomial;

import java.util.List;

public class StringCalculator {

    private Lexer lexer = new Lexer();
    private PriorityHandler priorityHandler = new PriorityHandler();

    public StringCalculator() {
        addConstants();
        addFunctions();
    }

    private void addConstants() {
        Constant.addConstant("pi", Math.PI);
        Constant.addConstant("e", Math.E);
    }

    private void addFunctions() {
        Function.addFunction("sin", Math::sin);
        Function.addFunction("cos", Math::cos);
        Function.addFunction("tan", Math::tan);
        Function.addFunction("cot", input -> 1 / Math.tan(input));
        Function.addFunction("ln", Math::log);
        Function.addFunction("lg", Math::log10);
        Function.addFunction("abs", Math::abs);
        Function.addFunction("asin", Math::asin);
        Function.addFunction("acos", Math::acos);
        Function.addFunction("atan", Math::atan);
        Function.addFunction("exp", Math::exp);
        Function.addFunction("sqrt", Math::sqrt);
    }

    public String calculate(String input) {
        try {
            List<Token> tokens = lexer.parse(input);
            tokens = priorityHandler.handle(tokens);
            Polynomial expr = new Polynomial(tokens.iterator());
            return String.format("%f", expr.getValue());
        } catch (ParserException e) {
            return e.getMessage();
        }
    }
}
