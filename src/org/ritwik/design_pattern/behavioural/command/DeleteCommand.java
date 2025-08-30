package org.ritwik.design_pattern.behavioural.command;

// Concrete Command
public class DeleteCommand implements Command {
    private TextEditor editor;
    private String deletedText;
    private int length;

    public DeleteCommand(TextEditor editor, int length) {
        this.editor = editor;
        this.length = length;
    }

    @Override
    public void execute() {
        String content = editor.getContent();
        if (content.length() >= length) {
            deletedText = content.substring(content.length() - length);
            editor.delete(length);
        }
    }

    @Override
    public void undo() {
        if (deletedText != null) {
            editor.write(deletedText);
        }
    }
}
