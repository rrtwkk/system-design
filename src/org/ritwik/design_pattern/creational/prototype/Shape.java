package org.ritwik.design_pattern.creational.prototype;

// Abstract prototype class
public abstract class Shape implements Prototype {
    public String color;
    public int x, y;

    public Shape() {
    }

    public Shape(Shape source) {
        if (source != null) {
            this.color = source.color;
            this.x = source.x;
            this.y = source.y;
        }
    }

    public abstract void draw();

    // Implement the Prototype interface
    @Override
    public abstract Prototype clone();

    // Getters and setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
