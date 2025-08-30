package org.ritwik.design_pattern.creational.prototype;

// Concrete prototype
public class Circle extends Shape {
    private int radius;

    public Circle() {
    }

    public Circle(Circle source) {
        super(source);
        if (source != null) {
            this.radius = source.radius;
        }
    }

    @Override
    public Prototype clone() {
        return new Circle(this);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle: color=" + color +
                ", position=(" + x + "," + y + "), radius=" + radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
