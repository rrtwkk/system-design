package org.ritwik.design_pattern.behavioural.visitor;

// Element interface
public interface DocumentElement {
    void accept(DocumentVisitor visitor);
}
