package com.tsb.strategyDesignPattern.drawing;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 * Implements a spray paint effect as a drawing strategy.
 * This strategy simulates spraying paint by creating small dots around a central point.
 */
public class SprayPaintStrategy implements DrawingStrategy {
    private static final Random rand = new Random();

    @Override
    public void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        int radius = (int) gc.getLineWidth(); // Radius for the spray area determined by the line width
        int dots = 40; // Number of dots to draw each time the method is called

        // Generate and draw dots around the cursor location
        for (int i = 0; i < dots; i++) {
            double angle = Math.random() * 360; // Random angle for dot placement
            double distance = rand.nextDouble() * radius; // Random distance from the cursor within the radius
            double x = endX + distance * Math.cos(Math.toRadians(angle)); // Calculate x coordinate
            double y = endY + distance * Math.sin(Math.toRadians(angle)); // Calculate y coordinate
            gc.fillOval(x, y, 2, 2); // Draw the dot
        }
    }
}
