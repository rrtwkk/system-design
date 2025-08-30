package org.ritwik.design_pattern.behavioural.command;

public class CommandDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorInvoker invoker = new EditorInvoker();

        // Execute commands
        invoker.executeCommand(new WriteCommand(editor, "Hello "));
        invoker.executeCommand(new WriteCommand(editor, "World!"));
        invoker.executeCommand(new DeleteCommand(editor, 6));
        invoker.executeCommand(new WriteCommand(editor, "Java!"));

        System.out.println("\nFinal content: '" + editor.getContent() + "'");

        // Undo operations
        System.out.println("\n--- Undoing operations ---");
        invoker.undo(); // Undo "Java!"
        invoker.undo(); // Undo delete
        invoker.undo(); // Undo "World!"
        invoker.undo(); // Undo "Hello "
        invoker.undo(); // Nothing to undo
    }
}
