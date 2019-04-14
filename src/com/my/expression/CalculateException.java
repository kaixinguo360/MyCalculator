package com.my.expression;

/**
 * 计算异常
 * 在计算表达式值时产生的错误
 */
public class CalculateException extends Exception {

    public CalculateException(String message) {
        super(message);
    }
}
