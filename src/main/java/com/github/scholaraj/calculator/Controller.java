package com.github.scholaraj.calculator;

import java.util.ArrayDeque;
import java.text.DecimalFormat;

import com.github.scholaraj.calculator.core.*;

import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

public class Controller {

    // Instance of Calculator class
    private Calculator myCalc = new Calculator();

    // Instance of InputParser class
    private InputParser myPars = new InputParser();

    @FXML TextField outputScreen;
    @FXML GridPane buttonContainer;
    
    // Method to handle postfix strings
    private double handlePostfix(String postfix, Calculator calc) {

        // Primitive for storing solution
        double solution;

        // Stack for storing intermediate results
        ArrayDeque<Double> valStack = new ArrayDeque<Double>();

        // String array to store values
        String[] stringVals = postfix.split(" ");

        // Primitives for storing operands
        double firstNum, secondNum;

        // For each loop to iterate through stringVals
        for (String val: stringVals) {

            val = val.trim();

            // If val is a number, add it to the stack
            if (val.matches("\\(?\\s*\\-*[0-9]+\\.?[0-9]*\\s*\\)?") || val.matches("\\(?\\s*\\-*[0-9]*\\.[0-9]+\\s*\\)?")) {
                valStack.push(Double.parseDouble(val));
            }

            // Otherwise, if val is an operator, evaluate current expression and push solution to stack
            else {

                switch (val) {
                    case "^":
                        secondNum = valStack.pop();
                        firstNum = valStack.pop();
                        valStack.push(calc.exponent(firstNum, secondNum));
                        break;

                    case "*":
                        secondNum = valStack.pop();
                        firstNum = valStack.pop();
                        valStack.push(calc.multiply(firstNum, secondNum));
                        break;

                    case "/":
                        secondNum = valStack.pop();
                        firstNum = valStack.pop();
                        valStack.push(calc.divide(firstNum, secondNum));
                        break;

                    case "%":
                        secondNum = valStack.pop();
                        firstNum = valStack.pop();
                        valStack.push(calc.remainder(firstNum, secondNum));
                        break;


                    case "+":
                        secondNum = valStack.pop();
                        firstNum = valStack.pop();
                        valStack.push(calc.add(firstNum, secondNum));
                        break;
                    
                    case "-":
                        secondNum = valStack.pop();
                        firstNum = valStack.pop();
                        valStack.push(calc.subtract(firstNum, secondNum));
                        break;

                    default:
                        break;
                }

            }

        }

        // After all expressions are evaluated, pop final solution from stack
        solution = valStack.pop();
        
        return solution;
    }

    private void evalExp() {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();

        // Parse expression from text
        myPars.readNew(text);

        // If expression is valid, evaluate
        if (myPars.isValid()) {

            // Convert given expression to postfix
            String postfix = InfixToPostfix.convert(text);

            // Evaluate full postfix expression
            Double solution = handlePostfix(postfix, myCalc);

            // Add formatted solution to text
            text = new DecimalFormat("#.###########").format(solution);

        }

        // Otherwise, clear text
        else {
            text = "0";
        }

        // Update expression on outputScreen
        outputScreen.setText(text);
    }

    // Method to delete current number or operator
    private void delChar() {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();

        // If text has more than two characters, remove last character
        if (text.length() > 2) {
            text = text.substring(0, (text.length() - 1));
        }

        // Otherwise, reset outputScreen to 0
        else {
            text = "0";
        }

        // Update expression on outputScreen
        outputScreen.setText(text);

    }

    // Method to reset outputString
    private void clearScreen() {

        outputScreen.setText("0");

    }

    private void addSign() {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();

        // Retrieves starting index of number after whitespace
        int numStart = text.lastIndexOf(" ") + 1;

        // Assigns currentNumber with substring of text
        String currentNum = text.substring(numStart);

        // If currentNumber already has sign, toggle off
        if (currentNum.startsWith("-")) {
            currentNum = currentNum.substring(1);
        }

        // Otherwise, attach sign to currentNumber
        else {
            currentNum = "-" + currentNum;
        }

        // Update expression on outputScreen    
        outputScreen.setText(text.substring(0, numStart) + currentNum);

    }

    // Method to handle whitespace for operators
    private void handleWhitespace(String val) {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();

        // Switch statement for provided String
        switch (val) {

            // If val is an operator, append whitespace to text
            case "+", "-", "*", "/", "%":
                text += " ";
                outputScreen.setText(text);
                break;

            // Otherwise, break statement and change nothing
            default:
                break;

        }

    }

    // Method to validate use of operators
    private boolean validOp(String val) {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();

        // Retrieves starting index of operator or number after whitespace
        int startIndex = text.lastIndexOf(" ") + 1;

        // If text string is longer than 2 characters, check operator validity
        if (text.length() >= 2) {

            /* If current ends with whitespace, assign with previous operator, 
            otherwise assign with number at startIndex */
            String current = text.endsWith(" ") 
            ? text.substring((text.length() - 2), (text.length() - 1)) 
            : text.substring(startIndex);

            // If current is operator, refuse second operator in val
            if (current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/") || current.equals("%")) {

                // If val is second arithmetic operator, return false
                if (val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/") || val.equals("%")) {
                    return false;
                }

                // If val is decimal following operator, return false
                else if (val.equals(".")) {
                    return false;
                }

                // Otherwise, return true
                else {
                    return true;
                }

            }

            // If current contains decimal, refuse second decimal in number
            else if (current.indexOf(".") > -1) {

                // If val is second decimal, return false
                if (val.equals(".")) {
                    return false;
                }

                // If val is digit, return true
                else if (val.equals("0") || val.equals("1") || val.equals("2") || val.equals("3") || val.equals("4") || val.equals("5") || val.equals("6") || val.equals("7") || val.equals("8") || val.equals("9")) {
                    return true;
                }

                // If val is operator, return true
                else if (val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/") || val.equals("%")) {
                    return true;
                }

                // Otherwise, return false
                else {
                    return false;
                }

            }

            // Otherwise, return true (if current is simply number)
            else {
                return true;
            }

        }

        // Otherwise, return true if only one number is onscreen
        return true;
        
    }

    // Method to handle arithmetic display
    private void printToScreen(String val) {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();
        
        // If outputScreen is empty, add new value as text
        if (text.equals("0")) {

            // Update expression on outputScreen
            outputScreen.setText(val); 

        }

        // Otherwise, append val to text
        else {

            // Switch statement for provided String
            switch (val) {

                case "1", "2", "3", "4", "5", 
                "6", "7", "8", "9", "0":
                    text = text + val;
                    break;

                case "+", "-", "*", "/", "%":
                    text = text + val + " ";
                    break;
                    
                case ".":
                    text = text + val; 
                    break;    

            }

            // Update expression on outputScreen
            outputScreen.setText(text); 

        }

    }

    // Method to handle button press
    public void handleMouseClick(ActionEvent e) {
        
        // Retrieves target element of event
        EventTarget target = e.getTarget();

        // Converts element and retrieves text String
        String value = ((Button) target).getText();

        // Switch statement for value
        switch (value) {

            case "1", "2", "3", "4", "5", 
            "6", "7", "8", "9", "0":
                printToScreen(value);
                break;
            
            case "+", "-", "*", "/", "%":

                if (validOp(value)) {
                    handleWhitespace(value);
                    printToScreen(value);
                }
                
                break;

            case ".":
                if (validOp(value)) printToScreen(value);
                break;
                
            case "+/-": 
                addSign();
                break;

            // If button's value is DEL, erase current character
            case "DEL": 
                delChar();
                break;

            // If button's value is C, clear screen    
            case "C": 
                clearScreen();
                break;

            // If button's value is =, evaluate current expression on outputScreen    
            case "=": 
                evalExp();
                break;

            default:
                break;
            
        }

        //System.out.println(((Button)target).getText());

    }

}
