package org.ritwik.design_pattern.behavioural.visitor;

// Concrete Element
public class ImageElement implements DocumentElement {
    private String imagePath;
    private int width, height;

    public ImageElement(String imagePath, int width, int height) {
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
