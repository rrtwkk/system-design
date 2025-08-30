package org.ritwik.design_pattern.behavioural.chain_of_responsibility;

// Concrete Handler
public class Level2Support extends SupportHandler {
    @Override
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getPriority() == SupportTicket.Priority.MEDIUM) {
            System.out.println("Level 2 Support: Handling medium priority issue - " + ticket.getIssue());
        } else if (nextHandler != null) {
            System.out.println("Level 2 Support: Escalating to next level");
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("Level 2 Support: No handler available for this request");
        }
    }
}
