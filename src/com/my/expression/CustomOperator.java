package com.my.expression;

/**
 * 自定义运算符
 * 仅支持二元运算符 (参数长度必须为二)
 * 继承于自定义函数
 */
public class CustomOperator extends CustomFunction {

    // 同优先级运算符运算顺序
    public final static int LEFT_TO_RIGHT = 0; // 从左至右 (默认)
    public final static int RIGHT_TO_LEFT = 1; // 从右至左

    // 同优先级运算符运算顺序
    public final int order;

    public CustomOperator(String name, Runnable runnable) {
        this(name, LEFT_TO_RIGHT, runnable);
    }

    public CustomOperator(String name, int order, Runnable runnable) {
        super(name, 2, runnable);
        this.order = order;
    }
}
