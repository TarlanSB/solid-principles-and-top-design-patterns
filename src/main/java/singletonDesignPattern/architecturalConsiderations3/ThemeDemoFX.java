package singletonDesignPattern.architecturalConsiderations3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ThemeDemoFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);

        // Button to change the theme
        Button changeThemeButton = new Button("Change Theme");
        changeThemeButton.setOnAction(e -> {
            ThemeManager.INSTANCE.nextTheme();
            ThemeManager.INSTANCE.applyTheme(root.getScene());
        });

        // Label to show text that will change color with the theme
        Label infoLabel = new Label("See how the theme affects this text!");

        // ComboBox to demonstrate theme changes on different UI control
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
        comboBox.setValue("Choose an option");

        // Add all components to the root container
        root.getChildren().addAll(changeThemeButton, infoLabel, comboBox);

        // Initial scene setup
        Scene scene = new Scene(root, 300, 250);
        ThemeManager.INSTANCE.applyTheme(scene); // Apply the initial theme

        primaryStage.setTitle("JavaFX Theme Manager Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

