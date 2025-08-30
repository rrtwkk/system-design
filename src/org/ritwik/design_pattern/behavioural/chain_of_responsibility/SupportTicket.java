package org.ritwik.design_pattern.behavioural.chain_of_responsibility;

// Request class
public class SupportTicket {
    public enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    private String issue;
    private Priority priority;

    public SupportTicket(String issue, Priority priority) {
        this.issue = issue;
        this.priority = priority;
    }

    public String getIssue() {
        return issue;
    }

    public Priority getPriority() {
        return priority;
    }
}
