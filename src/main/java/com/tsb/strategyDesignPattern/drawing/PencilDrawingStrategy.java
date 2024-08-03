package com.tsb.strategyDesignPattern.drawing;

import javafx.scene.canvas.GraphicsContext;

/**
 * Implements a basic pencil tool as a drawing strategy.
 * This strategy draws a continuous line following the mouse cursor.
 */
public class PencilDrawingStrategy implements DrawingStrategy {
    @Override
    public void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.strokeLine(startX, startY, endX, endY); // Draw a line from the start point to the end point
    }
}
