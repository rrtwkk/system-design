package org.ritwik.design_pattern.behavioural.template_method;

// Concrete class
public class CSVProcessor extends DataProcessor {
    @Override
    protected void processData_internal() {
        System.out.println("Processing CSV data: parsing columns, validating format...");
    }

    @Override
    protected void writeData() {
        System.out.println("Writing CSV data to database...");
    }
}
