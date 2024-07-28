package singletonDesignPattern.architecturalConsiderations3;

import javafx.scene.Scene;

/**
 * Singleton enum to manage theme changes across the entire application.
 * This enum ensures that only one instance of the ThemeManager exists.
 */
public enum ThemeManager {
    INSTANCE; // The single instance of this enum (Singleton pattern).

    private String currentTheme = "light"; // Default theme set to 'light'.

    // Paths to the CSS files for each theme, loaded as resources.
    // These paths are converted to a string format that JavaFX can use directly.
    private final String lightThemePath = getClass().getResource("/theme_light.css").toExternalForm();
    private final String darkThemePath = getClass().getResource("/theme_dark.css").toExternalForm();
    private final String blueThemePath = getClass().getResource("/theme_blue.css").toExternalForm();

    /**
     * Applies the current theme to the given JavaFX scene.
     * This method clears previous styles and adds the new theme style.
     * @param scene The JavaFX scene to which the theme will be applied.
     */
    public void applyTheme(Scene scene) {
        switch (currentTheme) {
            case "light" -> {
                scene.getStylesheets().clear(); // Clear existing stylesheets.
                scene.getStylesheets().add(lightThemePath); // Add light theme stylesheet.
            }
            case "dark" -> {
                scene.getStylesheets().clear(); // Clear existing stylesheets.
                scene.getStylesheets().add(darkThemePath); // Add dark theme stylesheet.
            }
            case "blue" -> {
                scene.getStylesheets().clear(); // Clear existing stylesheets.
                scene.getStylesheets().add(blueThemePath); // Add blue theme stylesheet.
            }
        }
    }

    /**
     * Cycles through the themes (light, dark, blue) upon invocation.
     * This method changes the current theme to the next one in the sequence.
     */
    public void nextTheme() {
        switch (currentTheme) {
            case "light" -> currentTheme = "dark"; // Change theme from light to dark.
            case "dark" -> currentTheme = "blue"; // Change theme from dark to blue.
            case "blue" -> currentTheme = "light"; // Change theme from blue to light.
        }
    }
}


