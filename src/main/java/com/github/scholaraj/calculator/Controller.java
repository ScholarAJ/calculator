package com.github.scholaraj.calculator;

import java.util.ArrayDeque;

import com.github.scholaraj.calculator.core.Calculator;

import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

public class Controller {

    // Instance of Calculator class
    Calculator myCalc = new Calculator();

    @FXML TextField outputScreen;
    @FXML GridPane buttonContainer;
    
    // Method to handle postfix strings
    public double handleInput(String postfix, Calculator calc) {

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
        solution =  valStack.pop();
        
        return solution;
    }

    // Method to reset outputString
    private void clearScreen() {

        outputScreen.setText("0");

    }

    // Method to handle whitespace for operators
    private void handleWhitespace(String val) {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();

        // Switch statement for provided String
        switch (val) {

            // If val is an operator, append whitespace to text
            case "+", "-", "x", "/", "%":
                text += " ";
                outputScreen.setText(text);
                break;

            // Otherwise, break statement and change nothing
            default:
                break;

        }

    }

    private void printToScreen(String val) {

        // Retrieves text from outputScreen
        String text = outputScreen.getText();
        
        // If outputScreen is empty, add new value as text
        if (text.equals("0")) {

            outputScreen.setText(val); 

        }

        // Otherwise, append val to text
        else {

            //char currChar = text.charAt(text.length() - 2);

            // Switch statement for provided String
            switch (val) {

                case "1", "2", "3", "4", "5", 
                "6", "7", "8", "9", "0":
                    text = text + val;
                    break;

                case "+", "-", "x", "/", "%":
                    text = text + val + " ";
                    break;
                    
                case ".":
                    text = text + val; 
                    break;    

            }

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
                //System.out.println(value);
                printToScreen(value);
                break;
            
            case "+", "-", "x", "/", "%":
                //System.out.println(value);
                handleWhitespace(value);
                printToScreen(value);
                break;

            case ".":
                printToScreen(value);
                break;
                
            case "+/-": 
                System.out.println("-");
                break;

            // If button's value is C, clear screen    
            case "C": 
                clearScreen();
                break;

            default:
                break;
            
        }

        //System.out.println(((Button)target).getText());

    }

}
