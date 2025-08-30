package org.ritwik.design_pattern.structural.proxy;

// Real Subject
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
        // Simulate expensive loading operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
