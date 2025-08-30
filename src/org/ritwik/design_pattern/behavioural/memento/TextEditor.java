package org.ritwik.design_pattern.behavioural.memento;

// Originator
public class TextEditor {
    private StringBuilder content = new StringBuilder();
    private int cursorPosition = 0;

    public void write(String text) {
        content.insert(cursorPosition, text);
        cursorPosition += text.length();
        System.out.println("Written: '" + text + "' | Content: '" + content.toString() + "'");
    }

    public void setCursorPosition(int position) {
        this.cursorPosition = Math.max(0, Math.min(position, content.length()));
        System.out.println("Cursor moved to position: " + this.cursorPosition);
    }

    public void showStatus() {
        System.out.println("Content: '" + content.toString() + "' | Cursor at: " + cursorPosition);
    }

    // Create memento
    public TextEditorMemento save() {
        System.out.println("Saving state...");
        return new TextEditorMemento(content.toString(), cursorPosition);
    }

    // Restore from memento
    public void restore(TextEditorMemento memento) {
        this.content = new StringBuilder(memento.getContent());
        this.cursorPosition = memento.getCursorPosition();
        System.out.println("State restored | Content: '" + content.toString() + "' | Cursor: " + cursorPosition);
    }
}
