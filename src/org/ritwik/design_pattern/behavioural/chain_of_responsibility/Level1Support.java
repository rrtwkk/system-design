package org.ritwik.design_pattern.behavioural.chain_of_responsibility;

// Concrete Handler
public class Level1Support extends SupportHandler {
    @Override
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getPriority() == SupportTicket.Priority.LOW) {
            System.out.println("Level 1 Support: Handling low priority issue - " + ticket.getIssue());
        } else if (nextHandler != null) {
            System.out.println("Level 1 Support: Escalating to next level");
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("Level 1 Support: No handler available for this request");
        }
    }
}
