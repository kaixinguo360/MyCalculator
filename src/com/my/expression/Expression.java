package com.my.expression;

/**
 * 可运行表达式
 */
public interface Expression {
    /**
     * 计算表达式的值
     */
    double getValue() throws CalculateException;
}
