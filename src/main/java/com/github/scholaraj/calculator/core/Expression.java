package com.github.scholaraj.calculator.core;

// Class for storing calculator entries
public class Expression {

    private String expression;
    private double solution;

    public Expression(String userExp, double userSolution) {
        expression = userExp;
        solution = userSolution;
    }

    public double getSolution() {
        return solution;
    }

    public String getExpression() {
        return expression;
    }

}
