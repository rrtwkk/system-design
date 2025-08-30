package org.ritwik.design_pattern.behavioural.visitor;

// Concrete Element
public class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

    public String getText() {
        return text;
    }
}
