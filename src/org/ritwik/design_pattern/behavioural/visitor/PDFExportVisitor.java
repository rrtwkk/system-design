package org.ritwik.design_pattern.behavioural.visitor;

// Concrete Visitor
public class PDFExportVisitor implements DocumentVisitor {
    private StringBuilder pdf = new StringBuilder();

    @Override
    public void visit(TextElement element) {
        pdf.append("[TEXT] ").append(element.getText()).append("\n");
        System.out.println("Exporting text to PDF: " + element.getText());
    }

    @Override
    public void visit(ImageElement element) {
        pdf.append("[IMAGE] ").append(element.getImagePath())
                .append(" (").append(element.getWidth()).append("x").append(element.getHeight()).append(")\n");
        System.out.println("Exporting image to PDF: " + element.getImagePath());
    }

    @Override
    public void visit(TableElement element) {
        pdf.append("[TABLE] ").append(element.getRows()).append("x").append(element.getColumns()).append(" table\n");
        System.out.println("Exporting table to PDF: " + element.getRows() + "x" + element.getColumns());
    }

    public String getPDF() {
        return pdf.toString();
    }
}
