package com.github.scholaraj.calculator;

import java.util.Scanner;
import java.util.regex.*;
import java.text.DecimalFormat;

public final class App {
    public static void main(String[] args) {
        
        // Instance of Calculator class
        Calculator myCalc = new Calculator();

        // Instance of Scanner to collect user input
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter an expression:");

        // String to check user input
        String userInput;

        // Creates a new regular expression to validate input
        // NOTE: The pattern and calculator class currently work for only a single operator. 
        Pattern regex = Pattern.compile("\\s*[0-9]+\\.?[0-9]*\\s*[\\+,\\-,\\*,\\/]\\s*[0-9]+\\.?[0-9]*");

        // Declares Matcher object to check against regex
        Matcher validator;

        // Boolean primitive to store result of validation
        boolean valid;

        while (scnr.hasNextLine()) {

            userInput = scnr.nextLine();
            
            /* NOTE: It is important to use the equals() method for comparing 
            Strings, as the equality operator only compares the references of 
            String objects rather than their sequence of characters. */

            if (userInput.trim().equals("q") || userInput.trim().equals("quit")) {
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

                    // String array to store first and second numbers
                    String[] numArray;
                    
                    // String to store formatted solution
                    String solution;

                    // DecimalFormat object for formatting answer
                    DecimalFormat decForm;

                    // If the provided expression is addition, call related methods
                    if (userInput.indexOf("+") > -1) {
                        
                        // Split userInput string into two substrings, divided by the addition operator
                        numArray = userInput.split("\\+");

                        // Parse through both substrings, assigning the double values to firstNum and secondNum
                        double firstNum = Double.parseDouble(numArray[0].trim());
                        double secondNum = Double.parseDouble(numArray[1].trim());

                        // Specifies a max number of eleven decimal places, trimming insignificant zeros
                        decForm = new DecimalFormat("#.###########");

                        // Format the solution's decimal places
                        solution = decForm.format(firstNum) + " + " + decForm.format(secondNum) + " = " + decForm.format(myCalc.add(firstNum, secondNum));

                        System.out.println(solution);

                    }

                    // If the provided expression is subtraction, call related methods
                    else if (userInput.indexOf("-") > -1) {

                        // Split userInput string into two substrings, divided by the subtraction operator
                        numArray = userInput.split("\\-");

                        // Parse through both substrings, assigning the double values to firstNum and secondNum
                        double firstNum = Double.parseDouble(numArray[0].trim());
                        double secondNum = Double.parseDouble(numArray[1].trim());

                        // Specifies a max number of eleven decimal places, trimming insignificant zeros
                        decForm = new DecimalFormat("#.###########");

                        // Format the solution's decimal places
                        solution = decForm.format(firstNum) + " - " + decForm.format(secondNum) + " = " + decForm.format(myCalc.subtract(firstNum, secondNum));

                        System.out.println(solution);

                    }

                    // If the provided expression is multiplication, call related methods
                    else if (userInput.indexOf("*") > -1) {

                        // Split userInput string into two substrings, divided by the multiplication operator
                        numArray = userInput.split("\\*");

                        // Parse through both substrings, assigning the double values to firstNum and secondNum
                        double firstNum = Double.parseDouble(numArray[0].trim());
                        double secondNum = Double.parseDouble(numArray[1].trim());

                        // Specifies a max number of eleven decimal places, trimming insignificant zeros
                        decForm = new DecimalFormat("#.###########");

                        // Format the solution's decimal places
                        solution = decForm.format(firstNum) + " * " + decForm.format(secondNum) + " = " + decForm.format(myCalc.multiply(firstNum, secondNum));

                        System.out.println(solution);

                    }

                    // If the provided expression is division, call related methods
                    else if (userInput.indexOf("/") > -1) {

                        // Split userInput string into two substrings, divided by the division operator
                        numArray = userInput.split("\\/");

                        // Parse through both substrings, assigning the double values to firstNum and secondNum
                        double firstNum = Double.parseDouble(numArray[0].trim());
                        double secondNum = Double.parseDouble(numArray[1].trim());

                        // Specifies a max number of eleven decimal places, trimming insignificant zeros
                        decForm = new DecimalFormat("#.###########");

                        // Format the solution's decimal places
                        solution = decForm.format(firstNum) + " / " + decForm.format(secondNum) + " = " + decForm.format(myCalc.divide(firstNum, secondNum));

                        System.out.println(solution);
                    }

                    else {
                        System.out.println("Expression could not be processed.");
                    }

                }

            }

            System.out.println("Enter an expression:");
            
        } 

        scnr.close();

        System.out.println("Have a nice day!");

    }
}
