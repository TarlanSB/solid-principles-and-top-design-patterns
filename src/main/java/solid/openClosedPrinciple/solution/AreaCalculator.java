package solid.openClosedPrinciple.solution;

import java.util.List;

public class AreaCalculator {
    public double calcShapeArea(Shape shape) {
        return shape.area();
    }

    public double calcTotalArea(List<Shape> shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.area();
        }
        return totalArea;
    }
}
