package com.github.scholaraj.calculator.core;

import java.util.ArrayList;

// Class for calculator functions
public class Calculator {

    // String ArrayList to store past expressions
    private ArrayList<Expression> expHist;

    public Calculator() {
        expHist = new ArrayList<Expression>();
    }

    // Method to add an expression to expHist ArrayList
    public void addExp(Expression exp) {
        expHist.add(exp);
    }

    // Method to get expression history
    public ArrayList<Expression> getHist() throws CloneNotSupportedException{

        // Creates a new ArrayList
        ArrayList<Expression> userList = new ArrayList<Expression>();

        // If the instance data is empty, return userList as is
        if (expHist.isEmpty()) {
            return userList;
        }

        // Otherwise, iterate through expHist
        else {

            // For every Expression in expHist, add a copy of it to userList
            for (Expression exp : expHist) {

                userList.add(exp.clone());

            }

            // After copying Expression data, return completed ArrayList
            return userList;

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
