package org.ritwik.design_pattern.behavioural.command;

// Receiver
public class TextEditor {
    private StringBuilder content = new StringBuilder();

    public void write(String text) {
        content.append(text);
        System.out.println("Written: '" + text + "' | Current: '" + content.toString() + "'");
    }

    public void delete(int length) {
        if (content.length() >= length) {
            content.delete(content.length() - length, content.length());
            System.out.println("Deleted " + length + " chars | Current: '" + content.toString() + "'");
        }
    }

    public String getContent() {
        return content.toString();
    }
}
