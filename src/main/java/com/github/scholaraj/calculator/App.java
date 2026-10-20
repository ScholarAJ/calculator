package com.github.scholaraj.calculator;

import java.util.Scanner;
import java.util.regex.*;
import java.text.DecimalFormat;
import java.util.ArrayDeque;

// Class for CLI
public final class App {

    // Method to handle userInput strings (Needs adjustment)
    public static double handleInput(String postfix, Calculator calc) {

        double solution;

        
        
        return solution;
    } 

    public static void main(String[] args) {
        
        // Instance of Calculator class
        Calculator myCalc = new Calculator();

        // Instance of Scanner to collect user input
        Scanner scnr = new Scanner(System.in);

        //System.out.println("Enter an expression:");

        // String to check user input
        String userInput = "(4.056 * 5.234 + 2.0) / 8 * 10";

        // Creates a new regular expression to validate input
        // NOTE: The pattern and calculator class currently work for only a single operator. 
        Pattern regex = Pattern.compile("\\s*[0-9]+\\.?[0-9]*\\s+[\\+,\\-,\\*,\\/]\\s+[0-9]+\\.?[0-9]*");

        // Declares Matcher object to check against regex
        Matcher validator;

        // Boolean primitive to store result of validation
        boolean valid;

        /*while (scnr.hasNextLine()) {

            userInput = scnr.nextLine();
            
            /* NOTE: It is important to use the equals() method for comparing 
            Strings, as the equality operator only compares the references of 
            String objects rather than their sequence of characters. */

            /*if (userInput.trim().equals("q") || userInput.trim().equals("quit")) {
                break;
            }

            else {

                // Creates new Matcher instance for current input
                validator = regex.matcher(userInput);

                // Checks if the current input is valid
                valid = validator.matches();

                if (!valid) {
                    System.out.println("Please enter a valid expression.");
                }

                else {

                    //System.out.println("Expression is valid.");

                    System.out.println(handleInput(InfixToPostfix.convert(userInput)));

                }

            }

            System.out.println("Enter an expression:");
            
        } 

        scnr.close();

        System.out.println("Have a nice day!");*/

        /*System.out.println("Infix expression: " + userInput);

        System.out.println("Postfix expression: " + InfixToPostfix.convert(userInput));*/

        System.out.println();

    }
}
