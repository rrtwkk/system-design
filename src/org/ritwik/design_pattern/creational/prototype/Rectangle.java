package org.ritwik.design_pattern.creational.prototype;

// Concrete prototype
public class Rectangle extends Shape {
    private int width, height;

    public Rectangle() {
    }

    public Rectangle(Rectangle source) {
        super(source);
        if (source != null) {
            this.width = source.width;
            this.height = source.height;
        }
    }

    @Override
    public Prototype clone() {
        return new Rectangle(this);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle: color=" + color +
                ", position=(" + x + "," + y + "), size=" + width + "x" + height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
