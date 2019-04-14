package com.my.calculator.utils.parser;

import java.util.HashMap;
import java.util.Map;

public class Constant implements Expression {

    private final static Map<String, Double> constants = new HashMap<>();

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

    public static void addConstant(String name, double value) {
        constants.put(name.toLowerCase(), value);
    }

    public static boolean isConstant(String name) {
        return constants.containsKey(name.toLowerCase());
    }

    public static double getConstant(String name) {
        return constants.get(name.toLowerCase());
    }
}
