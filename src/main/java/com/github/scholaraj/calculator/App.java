package com.github.scholaraj.calculator;

import java.util.Scanner;

import com.github.scholaraj.calculator.core.*;

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

    // Container for FXML content
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // Initializes scene with primary.fxml
        scene = new Scene(loadFXML("index"), 250, 375);

        // Retrieves stylesheet for scene
        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());

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
