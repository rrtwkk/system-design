package org.ritwik.design_pattern.behavioural.memento;

// Memento class
public class TextEditorMemento {
    private final String content;
    private final int cursorPosition;

    public TextEditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }

    public String getContent() {
        return content;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }
}
