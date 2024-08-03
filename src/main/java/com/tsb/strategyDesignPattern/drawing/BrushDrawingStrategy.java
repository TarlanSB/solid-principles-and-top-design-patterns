package com.tsb.strategyDesignPattern.drawing;

import javafx.scene.canvas.GraphicsContext;

/**
 * Implements a brush tool as a drawing strategy.
 * This strategy draws a solid circle that follows the mouse cursor, simulating a brush stroke.
 * The size of the brush (diameter of the circle) is determined by the current line width setting.
 */
public class BrushDrawingStrategy implements DrawingStrategy {
    @Override
    public void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        double radius = gc.getLineWidth() / 2;  // Calculate the radius from the line width
        gc.setFill(gc.getStroke());  // Use the current stroke color as the fill color
        gc.fillOval(endX - radius, endY - radius, 2 * radius, 2 * radius);  // Draw the circle centered at the end coordinates
    }
}
