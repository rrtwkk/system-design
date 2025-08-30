package org.ritwik.design_pattern.structural.composite;

public class CompositeDemo {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("document.txt", 10);
        File file2 = new File("image.jpg", 500);
        File file3 = new File("video.mp4", 2000);
        File file4 = new File("config.xml", 5);

        // Create directories
        Directory root = new Directory("root");
        Directory documents = new Directory("documents");
        Directory media = new Directory("media");

        // Build file system tree
        documents.add(file1);
        documents.add(file4);

        media.add(file2);
        media.add(file3);

        root.add(documents);
        root.add(media);

        // Display the entire file system
        System.out.println("File System Structure:");
        root.display(0);

        System.out.println("\nTotal size: " + root.getSize() + " KB");
        System.out.println("Documents folder size: " + documents.getSize() + " KB");
        System.out.println("Media folder size: " + media.getSize() + " KB");
    }
}
