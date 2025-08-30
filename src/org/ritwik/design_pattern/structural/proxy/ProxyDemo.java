package org.ritwik.design_pattern.structural.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        System.out.println("=== Using Proxy Pattern ===");

        // Create proxy images
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // First display - will load from disk
        System.out.println("\nFirst display of image1:");
        image1.display();

        System.out.println("\nFirst display of image2:");
        image2.display();

        // Second display - will use cached version
        System.out.println("\nSecond display of image1 (cached):");
        image1.display();

        System.out.println("\nSecond display of image2 (cached):");
        image2.display();
    }
}
