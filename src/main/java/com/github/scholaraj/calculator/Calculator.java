package com.github.scholaraj.calculator;

//import java.lang.Math;
import java.util.ArrayList;

// Class for calculator functions
public class Calculator {

    // String ArrayList to store past expressions
    // Note: Must edit to include answers
    private ArrayList<String> expHist;

    public Calculator() {
        expHist = new ArrayList<String>();
    }

    // Method to add an expression to expHist ArrayList
    public void addExp(String exp) {
        expHist.add(exp);
    }

    // Method to display expression history
    public void showHist() {
        for (int i = 0; i < expHist.size(); i++) {
            System.out.println(expHist.get(i));
        } 
    }

    public double add(double firstNum, double secondNum) {
        //addExp(firstNum + " + " + secondNum);
        return firstNum + secondNum;
    }

    public double subtract(double firstNum, double secondNum) {
        //addExp(firstNum + " - " + secondNum);
        return firstNum - secondNum;
    }

    public double multiply(double firstNum, double secondNum) {
        //addExp(firstNum + " * " + secondNum);
        return firstNum * secondNum;
    }

    public double divide(double firstNum, double secondNum) {
        //addExp(firstNum + " / " + secondNum);
        return firstNum / secondNum;
    }

    // Returns the modulo value of division (the remainder)
    // NOTE: Still needs implementation
    public double remainder(double firstNum, double secondNum) {
        return firstNum % secondNum;
    }

    
    // Returns value of firstNum raised to power of secondNum
    // NOTE: Still needs implementation
    public double exponent(double firstNum, double secondNum) {
        return Math.pow(firstNum, secondNum); 
    }

    // Square roots, logs, and trig functions to be added later...

    /*public double squareRoot(double num) {
        return Math.sqrt(num);
    }*/
}
