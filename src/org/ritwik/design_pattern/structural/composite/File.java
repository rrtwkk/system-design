package org.ritwik.design_pattern.structural.composite;

// Leaf class
public class File extends FileSystemComponent {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public void display(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        System.out.println(indent + "📄 " + name + " (" + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}
