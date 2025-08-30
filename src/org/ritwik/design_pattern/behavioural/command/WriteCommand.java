package org.ritwik.design_pattern.behavioural.command;

// Concrete Command
public class WriteCommand implements Command {
    private TextEditor editor;
    private String text;

    public WriteCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.text = text;
    }

    @Override
    public void execute() {
        editor.write(text);
    }

    @Override
    public void undo() {
        editor.delete(text.length());
    }
}
