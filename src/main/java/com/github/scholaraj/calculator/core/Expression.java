package com.github.scholaraj.calculator.core;

import java.text.DecimalFormat;

// Class to implement Expression data type
public class Expression implements Cloneable {

    private String expression;
    private double solution;

    // Constructor
    public Expression(String userExp, double userSolution) {
        expression = userExp;
        solution = userSolution;
    }

    /* Overrided method that returns copy of current expression object */
    @Override 
    public Expression clone() throws CloneNotSupportedException {
        return new Expression(this.getExpression(), this.getSolution());
    }

    /* Overrided method that returns formatted String */
    @Override 
    public String toString() {
        return this.getExpression() + " = " + new DecimalFormat("#.###########").format(this.getSolution());
    }

    // Getter method
    public double getSolution() {
        return solution;
    }

    // Setter method
    public String getExpression() {
        return expression;
    }

}
