package org.ritwik.design_pattern.behavioural.memento;

import java.util.ArrayList;
import java.util.List;

// Caretaker
public class EditorHistory {
    private List<TextEditorMemento> history = new ArrayList<>();

    public void save(TextEditor editor) {
        history.add(editor.save());
    }

    public void undo(TextEditor editor) {
        if (history.size() > 1) {
            history.remove(history.size() - 1); // Remove current state
            TextEditorMemento previousState = history.get(history.size() - 1);
            editor.restore(previousState);
        } else {
            System.out.println("No previous state to restore");
        }
    }

    public int getHistorySize() {
        return history.size();
    }
}
