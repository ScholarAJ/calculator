// Package declaration
package com.github.scholaraj.calculator;

// Imports JavaFX classes
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Imports IOException objects included with JDK
import java.io.IOException;

// Class for CLI
public final class App extends Application {

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
