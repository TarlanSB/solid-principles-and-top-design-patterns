package factoryMethodDesignPattern.shapedrawer.factory;

import factoryMethodDesignPattern.shapedrawer.shapes.Circle;
import factoryMethodDesignPattern.shapedrawer.shapes.Rectangle;
import factoryMethodDesignPattern.shapedrawer.shapes.Shape;
import factoryMethodDesignPattern.shapedrawer.shapes.ShapeType;

import java.awt.Color;
import java.util.Random;

/**
 * The ShapeFactory class follows the Factory Method design pattern.
 * It's responsible for creating instances of different shapes (e.g., Rectangle, Circle) without
 * specifying the exact class of the object that will be created. This abstraction allows for
 * flexibility in adding new shape types without changing the code that uses the factory.
 */
public class ShapeFactory {
    // A static Random instance for generating random values.
    private static final Random random = new Random();

    /**
     * Creates a shape based on the specified type, dimensions, and stroke thickness.
     * 
     * @param shapeType The type of the shape to create, defined by the ShapeType enum.
     * @param width The maximum width available for placing the shape. Used to calculate the shape's x position.
     * @param height The maximum height available for placing the shape. Used to calculate the shape's y position.
     * @param strokeThickness The thickness of the shape's outline.
     * @return A Shape instance of the specified type with random position and color.
     */
    public static Shape createShape(ShapeType shapeType, int width, int height, int strokeThickness) {
        // Generate a random starting point within the given dimensions.
        int x = random.nextInt(width);
        int y = random.nextInt(height);

        // Generate a random color.
        Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());

        Shape shape; // Declare a reference to the Shape interface.

        // Use a switch statement to instantiate the appropriate shape based on the shapeType argument.
        switch (shapeType) {
            case RECTANGLE:
                // Create a Rectangle with random position, size, and color.
                shape = new Rectangle(x, y, 50 + random.nextInt(100), 50 + random.nextInt(100), color);
                break;
            case CIRCLE:
                // Create a Circle with random position, radius, and color.
                shape = new Circle(x, y, 25 + random.nextInt(75), color);
                break;
            default:
                // If an unknown shape type is provided, throw an exception.
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }

        // Set the stroke thickness for the created shape.
        shape.setStrokeThickness(strokeThickness);

        // Return the created shape.
        return shape;
    }
}
