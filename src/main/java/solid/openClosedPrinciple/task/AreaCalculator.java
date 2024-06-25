package solid.openClosedPrinciple.task;

/**
 * 1. Identify the parts of the code that change when a new type of object is introduced,
 * 2. Create an abstraction which would be an interface or an abstract class for these kind of objects,
 * and define the behavior that varies in this abstraction,
 * 3. Then implement this abstraction in each of the object classes, providing their own implementation of the behavior,
 * 4. and use the abstraction instead of the concrete classes where the behavior is needed.
 */
public class AreaCalculator {

    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle rectangle) {
            return rectangle.width * rectangle.height;
        } else if (shape instanceof Circle circle) {
            return Math.PI * circle.radius * circle.radius;
        }
        return 0;
    }
}

class Rectangle {
    double width;
    double height;
}

class Circle {
    double radius;
}