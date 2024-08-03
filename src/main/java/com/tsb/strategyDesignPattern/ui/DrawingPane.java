package com.tsb.strategyDesignPattern.ui;

import com.tsb.strategyDesignPattern.drawing.BrushDrawingStrategy;
import com.tsb.strategyDesignPattern.drawing.DrawingContext;
import com.tsb.strategyDesignPattern.drawing.PencilDrawingStrategy;
import com.tsb.strategyDesignPattern.drawing.SprayPaintStrategy;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

// Class that manages the drawing canvas and its controls
public class DrawingPane {
    private final Canvas canvas;
    private final DrawingContext drawingContext;
    private HBox controls;

    // Constructor initializes the UI components
    public DrawingPane() {
        canvas = new Canvas(600, 400);
        drawingContext = new DrawingContext(canvas.getGraphicsContext2D());

        // Setup the controls and interaction handlers
        setupControls();
        setupCanvasInteractions();
    }

    // Setup for UI controls like buttons and combo boxes
    private void setupControls() {
        // Color picker for choosing the draw color
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> drawingContext.setColor(colorPicker.getValue()));

        // Combo box for selecting the thickness of the drawing tool
        ComboBox<Double> thicknessComboBox = new ComboBox<>();
        thicknessComboBox.getItems().addAll(1.0, 3.0, 5.0, 10.0, 15.0);
        thicknessComboBox.setValue(1.0);
        thicknessComboBox.setOnAction(e -> drawingContext.setThickness(thicknessComboBox.getValue()));

        // Combo box for selecting the drawing tool (Pencil, Brush, Spray Paint)
        ComboBox<String> toolComboBox = new ComboBox<>();
        toolComboBox.getItems().addAll("Pencil", "Brush", "Spray Paint");
        toolComboBox.setValue("Pencil");
        toolComboBox.setOnAction(e -> updateDrawingStrategy(toolComboBox.getValue()));

        // Button to clear the drawing canvas
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> drawingContext.clearCanvas());

        // Horizontal box to hold the controls
        controls = new HBox(10, colorPicker, thicknessComboBox, toolComboBox, clearButton);
        controls.setPadding(new Insets(10));
        controls.setAlignment(javafx.geometry.Pos.CENTER);
    }

    // Method to update the drawing strategy based on tool selection
    private void updateDrawingStrategy(String tool) {
        switch (tool) {
            case "Brush" -> drawingContext.setDrawingStrategy(new BrushDrawingStrategy());
            case "Spray Paint" -> drawingContext.setDrawingStrategy(new SprayPaintStrategy());
            default -> drawingContext.setDrawingStrategy(new PencilDrawingStrategy());
        }
    }

    // Setup for mouse interactions on the canvas
    private void setupCanvasInteractions() {
        // Store the last position to start the next line segment from the last end point
        final double[] lastPosition = new double[2];

        // Initialize the drawing from where the mouse is pressed
        canvas.setOnMousePressed(e -> {
            lastPosition[0] = e.getX();
            lastPosition[1] = e.getY();
            drawingContext.draw(lastPosition[0], lastPosition[1], e.getX(), e.getY());
        });

        // Continue drawing as the mouse is dragged
        canvas.setOnMouseDragged(e -> {
            drawingContext.draw(lastPosition[0], lastPosition[1], e.getX(), e.getY());
            lastPosition[0] = e.getX();
            lastPosition[1] = e.getY();
        });

        // Finish drawing when the mouse is released
        canvas.setOnMouseReleased(e -> drawingContext.draw(lastPosition[0], lastPosition[1], e.getX(), e.getY()));
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public HBox getControls() {
        return controls;
    }
}

