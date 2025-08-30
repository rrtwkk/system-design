package org.ritwik.design_pattern.behavioural.visitor;

import java.util.ArrayList;
import java.util.List;

// Object structure
public class Document {
    private List<DocumentElement> elements = new ArrayList<>();

    public void addElement(DocumentElement element) {
        elements.add(element);
    }

    public void accept(DocumentVisitor visitor) {
        for (DocumentElement element : elements) {
            element.accept(visitor);
        }
    }
}
