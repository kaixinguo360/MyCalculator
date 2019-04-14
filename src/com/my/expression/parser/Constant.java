package com.my.expression.parser;

import com.my.expression.Expression;

/**
 * 常量表达式
 * 代表一个常量
 */
public class Constant implements Expression {

    // 表达式的常量值
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    public Constant(String value) {
        this.value = Double.valueOf(value);
    }

    @Override
    public double getValue() {
        return value;
    }
}
