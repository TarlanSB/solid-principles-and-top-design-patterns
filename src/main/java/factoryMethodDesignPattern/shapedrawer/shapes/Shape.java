package factoryMethodDesignPattern.shapedrawer.shapes;

import java.awt.Graphics;
import java.awt.Color;

// Shape interface defining basic shape functionalities.
public interface Shape {
    void draw(Graphics g);
    String getName();
    Color getColor();
    void setStrokeThickness(int thickness);
}
