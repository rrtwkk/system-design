package org.ritwik.design_pattern.behavioural.visitor;

public class VisitorDemo {
    public static void main(String[] args) {
        // Create document with different elements
        Document document = new Document();
        document.addElement(new TextElement("Welcome to our website"));
        document.addElement(new ImageElement("logo.png", 200, 100));
        document.addElement(new TableElement(3, 4));
        document.addElement(new TextElement("Thank you for visiting"));

        // Export to HTML
        System.out.println("=== Exporting to HTML ===");
        HTMLExportVisitor htmlVisitor = new HTMLExportVisitor();
        document.accept(htmlVisitor);
        System.out.println("\nGenerated HTML:\n" + htmlVisitor.getHTML());

        // Export to PDF
        System.out.println("=== Exporting to PDF ===");
        PDFExportVisitor pdfVisitor = new PDFExportVisitor();
        document.accept(pdfVisitor);
        System.out.println("\nGenerated PDF content:\n" + pdfVisitor.getPDF());
    }
}
