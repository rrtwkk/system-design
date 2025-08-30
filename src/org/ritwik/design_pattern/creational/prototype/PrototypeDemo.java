package org.ritwik.design_pattern.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class PrototypeDemo {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        // Create original shapes
        Circle circle = new Circle();
        circle.setColor("Red");
        circle.setX(10);
        circle.setY(20);
        circle.setRadius(5);
        shapes.add(circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setColor("Blue");
        rectangle.setX(30);
        rectangle.setY(40);
        rectangle.setWidth(15);
        rectangle.setHeight(10);
        shapes.add(rectangle);

        // Clone shapes
        List<Shape> clonedShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            clonedShapes.add((Shape) shape.clone());
        }

        // Modify cloned shapes
        ((Circle) clonedShapes.get(0)).setColor("Green");
        ((Circle) clonedShapes.get(0)).setX(100);

        ((Rectangle) clonedShapes.get(1)).setColor("Yellow");
        ((Rectangle) clonedShapes.get(1)).setY(200);

        // Display original and cloned shapes
        System.out.println("Original shapes:");
        for (Shape shape : shapes) {
            shape.draw();
        }

        System.out.println("\nCloned and modified shapes:");
        for (Shape shape : clonedShapes) {
            shape.draw();
        }
    }
}
