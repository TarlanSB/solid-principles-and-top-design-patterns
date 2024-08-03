package com.tsb.strategyDesignPattern.drawing;

import javafx.scene.canvas.GraphicsContext;

/**
 * The DrawingStrategy interface defines a common approach for drawing strategies.
 * Each drawing strategy must implement the draw method that defines how to draw on the canvas.
 */
public interface DrawingStrategy {
    /**
     * Draws on the canvas using the specified GraphicsContext.
     * @param gc The GraphicsContext associated with the canvas to draw on.
     * @param startX The starting x-coordinate of the drawing.
     * @param startY The starting y-coordinate of the drawing.
     * @param endX The ending x-coordinate of the drawing.
     * @param endY The ending y-coordinate of the drawing.
     */
    void draw(GraphicsContext gc, double startX, double startY, double endX, double endY);
}
