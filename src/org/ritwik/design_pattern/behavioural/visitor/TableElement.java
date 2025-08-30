package org.ritwik.design_pattern.behavioural.visitor;

// Concrete Element
public class TableElement implements DocumentElement {
    private int rows, columns;

    public TableElement(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
