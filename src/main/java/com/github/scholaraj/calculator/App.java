package com.github.scholaraj.calculator;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayDeque;

// Class for CLI
public final class App {

    public static void loopInput(Scanner scnr, Calculator calc) {
        // String to check user input
        String userInput;

        // Instance of InputParser to validate user input
        InputParser parser = new InputParser();

        while (scnr.hasNextLine()) {

            userInput = scnr.nextLine();
            
            /* NOTE: It is important to use the equals() method for comparing 
            Strings, as the equality operator only compares the references of 
            String objects rather than their sequence of characters. */

            // If userInput is q or quit, exit program
            if (userInput.trim().equals("q") || userInput.trim().equals("quit")) {
                break;
            }

            // If userInput is h or history, show calculator expression history
            else if (userInput.trim().equals("h") || userInput.trim().equals("history")) {
                calc.showHist();
            }

            else {

                // Uses parser to read new input string
                parser.readNew(userInput);

                // Checks if the current input is valid
                boolean valid = parser.isValid();

                if (!valid) {
                    System.out.print("\n" + "Please enter a valid expression." + "\n");
                }

                else {

                    try {

                        // Find postfix of userInput String
                        String postfix = InfixToPostfix.convert(userInput);

                        // Primitive to store solution of expression
                        double solution = handleInput(postfix, calc);

                        // Print formatted decimal to terminal
                        System.out.println(new DecimalFormat("#.###########").format(solution));

                        // Adds new expression to calculator history
                        calc.addExp(new Expression(userInput, solution));

                    }

                    // Catches Exception if one occurs
                    catch(Exception error) {
                        System.out.println(error.getMessage());
                    }
                    

                }

            }

            System.out.print("\n" + "Enter an expression:" + "\n");
            
        } 
    }

    // Method to handle postfix strings
    public static double handleInput(String postfix, Calculator calc) {

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

    public static void main(String[] args) {
        
        // Instance of Calculator class
        Calculator myCalc = new Calculator();

        // Instance of Scanner to collect user input
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter an expression (whitespace is required):");

        // Invokes method to loop input
        loopInput(scnr, myCalc);

        // Closes Scanner
        scnr.close();

        System.out.print("\n" + "Have a nice day!" + "\n");

    }
}
