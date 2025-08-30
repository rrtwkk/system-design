package org.ritwik.design_pattern.behavioural.visitor;

// Concrete Visitor
public class HTMLExportVisitor implements DocumentVisitor {
    private StringBuilder html = new StringBuilder();

    @Override
    public void visit(TextElement element) {
        html.append("<p>").append(element.getText()).append("</p>\n");
        System.out.println("Exporting text to HTML: " + element.getText());
    }

    @Override
    public void visit(ImageElement element) {
        html.append("<img src=\"").append(element.getImagePath())
                .append("\" width=\"").append(element.getWidth())
                .append("\" height=\"").append(element.getHeight())
                .append("\" />\n");
        System.out.println("Exporting image to HTML: " + element.getImagePath());
    }

    @Override
    public void visit(TableElement element) {
        html.append("<table>\n");
        for (int i = 0; i < element.getRows(); i++) {
            html.append("  <tr>");
            for (int j = 0; j < element.getColumns(); j++) {
                html.append("<td>Cell</td>");
            }
            html.append("</tr>\n");
        }
        html.append("</table>\n");
        System.out.println("Exporting table to HTML: " + element.getRows() + "x" + element.getColumns());
    }

    public String getHTML() {
        return html.toString();
    }
}
