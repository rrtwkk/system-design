package org.ritwik.design_pattern.structural.proxy;

// Proxy
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            System.out.println("Proxy: First time accessing image, loading...");
            realImage = new RealImage(fileName);
        } else {
            System.out.println("Proxy: Using cached image");
        }
        realImage.display();
    }
}
