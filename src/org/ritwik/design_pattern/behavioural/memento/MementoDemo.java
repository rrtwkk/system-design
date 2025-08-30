package org.ritwik.design_pattern.behavioural.memento;

public class MementoDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        // Save initial state
        history.save(editor);

        // Make changes and save states
        editor.write("Hello ");
        history.save(editor);

        editor.write("World!");
        history.save(editor);

        editor.setCursorPosition(6);
        editor.write("Java ");
        history.save(editor);

        System.out.println("\n--- Current State ---");
        editor.showStatus();

        // Undo operations
        System.out.println("\n--- Undoing Changes ---");
        history.undo(editor);
        history.undo(editor);
        history.undo(editor);
    }
}
