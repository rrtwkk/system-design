package org.ritwik.design_pattern.behavioural.visitor;

// Visitor interface
public interface DocumentVisitor {
    void visit(TextElement element);

    void visit(ImageElement element);

    void visit(TableElement element);
}
