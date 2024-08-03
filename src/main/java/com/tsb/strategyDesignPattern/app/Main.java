package com.tsb.strategyDesignPattern.app;

import com.tsb.strategyDesignPattern.ui.DrawingPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Main class that launches the application
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Setting the title of the primary stage
        primaryStage.setTitle("Strategy Pattern Drawing Application");

        // Creating an instance of DrawingPane which includes all UI elements
        DrawingPane drawingPane = new DrawingPane();
        BorderPane root = new BorderPane();

        // Placing the control panel at the top and the canvas in the center of the layout
        root.setTop(drawingPane.getControls());
        root.setCenter(drawingPane.getCanvas());

        // Setting the scene with a predefined size and adding it to the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
