package org.ritwik.design_pattern.structural.composite;

// Component interface
public abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void display(int depth);

    public abstract int getSize();

    // Default implementations for composite operations
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }

    public String getName() {
        return name;
    }
}
