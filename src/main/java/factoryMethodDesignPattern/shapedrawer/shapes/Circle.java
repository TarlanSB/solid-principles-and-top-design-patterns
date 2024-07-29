package factoryMethodDesignPattern.shapedrawer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

// Concrete implementation of a circle shape.
public class Circle implements Shape {
    private int x, y, radius;
    private Color color;
    private int strokeThickness = 1; // Default thickness
    

    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void setStrokeThickness(int thickness) {
        this.strokeThickness = Math.max(1, Math.min(thickness, 10)); // Clamp between 1 and 10
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create(); // Create a copy of the Graphics instance.
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeThickness)); // Set the stroke thickness.

        // The drawOval method requires the top-left corner of the bounding box,
        // which is (x - radius, y - radius) for a circle, and the width and height
        // of the bounding box, which are both 2 * radius for a circle.
        g2d.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

        g2d.dispose(); // Dispose of the graphics context to release system resources.
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public Color getColor() {
        return color;
    }
}

