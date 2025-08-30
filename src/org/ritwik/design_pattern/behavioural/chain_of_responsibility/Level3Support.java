package org.ritwik.design_pattern.behavioural.chain_of_responsibility;

// Concrete Handler
public class Level3Support extends SupportHandler {
    @Override
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getPriority() == SupportTicket.Priority.HIGH ||
                ticket.getPriority() == SupportTicket.Priority.CRITICAL) {
            System.out.println("Level 3 Support: Handling " + ticket.getPriority().toString().toLowerCase() +
                    " priority issue - " + ticket.getIssue());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("Level 3 Support: No handler available for this request");
        }
    }
}
