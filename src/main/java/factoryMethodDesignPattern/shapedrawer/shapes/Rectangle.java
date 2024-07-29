package factoryMethodDesignPattern.shapedrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

// Concrete implementation of a rectangle shape.
public class Rectangle implements Shape {
    private int x, y, width, height;
    private Color color;
    private int strokeThickness = 1; // Default stroke thickness

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void setStrokeThickness(int thickness){
        this.strokeThickness = Math.max(1, Math.min(thickness, 10)); // Clamp between 1 and 10
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create(); // Create a copy of the Graphics instance to maintain its original state elsewhere.
        g2d.setStroke(new BasicStroke(strokeThickness));
        g2d.setColor(color);
        g2d.drawRect(x, y, width, height);
        g2d.dispose(); // Dispose of this graphics context and release any system resources that it is using.
    }
    

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public Color getColor() {
        return color;
    }
}

