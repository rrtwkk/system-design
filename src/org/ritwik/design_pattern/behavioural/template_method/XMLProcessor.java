package org.ritwik.design_pattern.behavioural.template_method;

// Concrete class
public class XMLProcessor extends DataProcessor {
    @Override
    protected void processData_internal() {
        System.out.println("Processing XML data: parsing tags, validating schema...");
    }

    @Override
    protected void writeData() {
        System.out.println("Writing XML data to file system...");
    }
}
