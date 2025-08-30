package org.ritwik.design_pattern.behavioural.command;

import java.util.Stack;

// Invoker
public class EditorInvoker {
    private Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
            System.out.println("Undo executed");
        } else {
            System.out.println("Nothing to undo");
        }
    }
}
