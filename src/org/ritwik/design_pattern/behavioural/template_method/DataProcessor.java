package org.ritwik.design_pattern.behavioural.template_method;

// Abstract class with template method
public abstract class DataProcessor {

    // Template method - defines the skeleton
    public final void processData() {
        readData();
        processData_internal();
        writeData();
    }

    // Concrete method
    private void readData() {
        System.out.println("Reading data from source...");
    }

    // Abstract methods - to be implemented by subclasses
    protected abstract void processData_internal();

    // Hook method - can be overridden
    protected void writeData() {
        System.out.println("Writing processed data to output...");
    }
}
