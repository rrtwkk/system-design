package org.ritwik.design_pattern.behavioural.template_method;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== Processing CSV Data ===");
        DataProcessor csvProcessor = new CSVProcessor();
        csvProcessor.processData();

        System.out.println("\n=== Processing XML Data ===");
        DataProcessor xmlProcessor = new XMLProcessor();
        xmlProcessor.processData();

        System.out.println("\n=== Processing JSON Data ===");
        DataProcessor jsonProcessor = new JSONProcessor();
        jsonProcessor.processData();
    }
}
