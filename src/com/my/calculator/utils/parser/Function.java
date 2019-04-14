package com.my.calculator.utils.parser;

import com.my.calculator.utils.ParserException;
import com.my.calculator.utils.lexer.Token;
import com.my.calculator.utils.lexer.TokenType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Function implements Expression {

    private final static Map<String, CustomFunction> customFunctions = new HashMap<>();

    private Expression value = null;
    private final CustomFunction function;

    public Function(CustomFunction function, Iterator<Token> tokens) throws ParserException {

        this.function = function;

        Token token = tokens.next();
        if (token.getType() == TokenType.BRACKET_LEFT) {
            this.value = new Polynomial(tokens);
        } else {
            throw new ParserException("非预期的标记: " + token.getSymbol());
        }
    }

    @Override
    public double getValue() {
        return this.function.calculate(value.getValue());
    }

    public static void addFunction(String name, CustomFunction function) {
        customFunctions.put(name.toLowerCase(), function);
    }

    public static boolean isFunction(String name) {
        return customFunctions.containsKey(name.toLowerCase());
    }

    public static CustomFunction getFunction(String name) {
        return customFunctions.get(name.toLowerCase());
    }

    public interface CustomFunction {
        double calculate(double input);
    }
}
