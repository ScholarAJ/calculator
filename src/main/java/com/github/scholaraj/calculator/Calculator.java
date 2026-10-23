package com.github.scholaraj.calculator;

import java.text.DecimalFormat;
//import java.lang.Math;
import java.util.ArrayList;

// Class for calculator functions
public class Calculator {

    // String ArrayList to store past expressions
    // Note: Must edit to include answers
    private ArrayList<Expression> expHist;

    public Calculator() {
        expHist = new ArrayList<Expression>();
    }

    // Method to add an expression to expHist ArrayList
    public void addExp(Expression exp) {
        expHist.add(exp);
    }

    // Method to display expression history
    public void showHist() {

        if (expHist.isEmpty()) {
            System.out.print("\n" + "History is empty!" + "\n");
        }

        else {

            System.out.println();

            // For each Expression object stored in expHist, print stored expression and its solution
            for (int i = 0; i < expHist.size(); i++) {
                Expression exp = expHist.get(i);
                System.out.println(exp.getExpression() + " = " + new DecimalFormat("#.###########").format(exp.getSolution()));
            } 

        }

        
    }

    public double add(double firstNum, double secondNum) {
        return firstNum + secondNum;
    }

    public double subtract(double firstNum, double secondNum) {
        return firstNum - secondNum;
    }

    public double multiply(double firstNum, double secondNum) {
        return firstNum * secondNum;
    }

    public double divide(double firstNum, double secondNum) {
        return firstNum / secondNum;
    }

    // Returns the modulo value of division (the remainder)
    public double remainder(double firstNum, double secondNum) {
        return firstNum % secondNum;
    }

    
    // Returns value of firstNum raised to power of secondNum
    public double exponent(double firstNum, double secondNum) {
        return Math.pow(firstNum, secondNum); 
    }

    // Square roots, logs, and trig functions to be added in the future...

}
