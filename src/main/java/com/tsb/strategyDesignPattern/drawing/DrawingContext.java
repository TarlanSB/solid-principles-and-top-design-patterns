package com.tsb.strategyDesignPattern.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Class to manage the drawing context, including color, thickness, and strategy
public class DrawingContext {
    private final GraphicsContext gc;
    private DrawingStrategy drawingStrategy;
    private Color color = Color.BLACK;  // Default color
    private double thickness = 1.0;     // Default thickness

    // Constructor takes a GraphicsContext
    public DrawingContext(GraphicsContext gc) {
        this.gc = gc;
        this.drawingStrategy = new PencilDrawingStrategy();  // Default strategy
    }

    // Set the drawing strategy
    public void setDrawingStrategy(DrawingStrategy drawingStrategy) {
        this.drawingStrategy = drawingStrategy;
    }

    // Set the color used for drawing
    public void setColor(Color color) {
        this.color = color;
        this.gc.setStroke(color);
        this.gc.setFill(color);
    }

    // Set the thickness of the drawing line
    public void setThickness(double thickness) {
        this.thickness = thickness;
        this.gc.setLineWidth(thickness);
    }

    // Draw from start to end points using the current strategy
    public void draw(double startX, double startY, double endX, double endY) {
        gc.setStroke(color);  // Ensure stroke color is updated
        gc.setFill(color);    // Ensure fill color is updated
        gc.setLineWidth(thickness);
        drawingStrategy.draw(gc, startX, startY, endX, endY);
    }

    // Clear the entire canvas
    public void clearCanvas() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
