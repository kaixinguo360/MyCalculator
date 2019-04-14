package com.my.expression;

/**
 * 自定义函数
 */
public class CustomFunction {

    public final String name; // 函数名称
    public final int params; // 函数参数长度
    public final Runnable runnable; // 实际的可运行函数

    public CustomFunction(String name, Runnable runnable) {
        this(name, 1, runnable);
    }

    public CustomFunction(String name, int paramLength, Runnable runnable) {
        this.name = name.toLowerCase();
        this.params = paramLength;
        this.runnable = runnable;
    }

    /**
     * 调用保存的可运行函数
     */
    public double run(double... inputs) throws CalculateException {
        // 检查实参与形参长度是否相符
        if (inputs.length == params) {
            return runnable.run(inputs);
        } else {
            throw new CalculateException(String.format("函数'%s'参数长度不匹配, 预期: %d, 实际: %d", name, params, inputs.length));
        }
    }

    /**
     * 可运行函数接口
     * 保存实际可运行的函数
     */
    public interface Runnable {
        double run(double[] inputs);
    }
}
