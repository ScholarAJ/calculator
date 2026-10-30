package com.github.scholaraj.calculator;

import java.util.Scanner;

import com.github.scholaraj.calculator.core.Calculator;

import java.text.DecimalFormat;
import java.util.ArrayDeque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Class for CLI
public final class App extends Application {

    /*public static void loopInput(Scanner scnr, Calculator calc) {
        // String to check user input
        String userInput;

        // Instance of InputParser to validate user input
        InputParser parser = new InputParser();

        while (scnr.hasNextLine()) {

            userInput = scnr.nextLine();*/
            
            /* NOTE: It is important to use the equals() method for comparing 
            Strings, as the equality operator only compares the references of 
            String objects rather than their sequence of characters. */

            // If userInput is q or quit, exit program
            /*if (userInput.trim().equals("q") || userInput.trim().equals("quit")) {
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

                        // Normalizes whitespace (if needed)
                        userInput = userInput.replaceAll("\\s+", " ").trim();

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
    }*/

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

    // Instance of Calculator class
    Calculator myCalc = new Calculator();

    // Container for FXML content
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // Initializes scene with primary.fxml
        scene = new Scene(loadFXML("primary"), 640, 480);

        // Assigns stage parameter with FXML stored in scene
        stage.setScene(scene);

        // Sbows current window
        stage.show();

    }

    // Sets root node to specified FXML
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Returns loaded FXML file
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Main method to launch application
    public static void main(String[] args) {
        launch();
    }

}
