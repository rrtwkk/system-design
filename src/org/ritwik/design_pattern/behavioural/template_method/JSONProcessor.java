package org.ritwik.design_pattern.behavioural.template_method;

// Concrete class
public class JSONProcessor extends DataProcessor {
    @Override
    protected void processData_internal() {
        System.out.println("Processing JSON data: parsing objects, validating structure...");
    }

    // Uses default writeData() implementation
}
