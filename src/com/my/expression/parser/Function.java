package com.my.expression.parser;

import com.my.expression.CalculateException;
import com.my.expression.CustomFunction;
import com.my.expression.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 函数表达式
 * 代表一个函数
 */
public class Function implements Expression {

    public List<Expression> inputs = new ArrayList<>(); // 函数的输入参数 (也都为表达式)
    private final CustomFunction function; // 要调用的自定义函数

    public Function(CustomFunction function) {
        this.function = function;
    }

    /**
     * 添加参数
     */
    public void addInput(Expression... expressions) {
        inputs.addAll(Arrays.asList(expressions));
    }

    @Override
    public double getValue() throws CalculateException {
        double[] values = new double[inputs.size()];
        int count = 0;
        for (Expression input : inputs) {
            values[count++] = input.getValue();
        }
        return this.function.run(values);
    }
}
