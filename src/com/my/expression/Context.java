package com.my.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编译上下文
 * 用于保存编译过程中自定义的常量, 函数与运算符
 * 供词法分析器与语法分析器查询
 */
public class Context {

    // 常量 //
    private final Map<String, Double> constants = new HashMap<>();
    public void addConstant(String name, double value) {
        constants.put(name.toLowerCase(), value);
    }
    public boolean isConstant(String name) {
        return constants.containsKey(name.toLowerCase());
    }
    public double getConstant(String name) {
        return constants.get(name.toLowerCase());
    }

    // 函数 //
    private final Map<String, CustomFunction> functions = new HashMap<>();
    public void addFunction(CustomFunction function) {
        functions.put(function.name, function);
    }
    public boolean isFunction(String name) {
        return functions.containsKey(name.toLowerCase());
    }
    public CustomFunction getFunction(String name) {
        return functions.get(name.toLowerCase());
    }

    // 运算符 //
    private final List<CustomOperator> operators = new ArrayList<>();
    /**
     * 新增运算符
     * 运算符优先级即添加的顺序
     */
    public void addOperator(CustomOperator function) {
        operators.add(function);
    }
    public boolean isOperator(String name) {
        return operators.stream().anyMatch(fun -> fun.name.equals(name.toLowerCase()));
    }
    public CustomOperator getOperator(String name) {
        return operators.stream().filter(fun -> fun.name.equals(name.toLowerCase())).findAny().get();
    }
    public List<CustomOperator> getOperators() {
        return operators;
    }
}
