package org.ritwik.design_pattern.behavioural.chain_of_responsibility;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Create handlers
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();

        // Setup chain
        level1.setNext(level2);
        level2.setNext(level3);

        // Create tickets
        SupportTicket[] tickets = {
                new SupportTicket("Password reset", SupportTicket.Priority.LOW),
                new SupportTicket("Database connection error", SupportTicket.Priority.MEDIUM),
                new SupportTicket("Server crash", SupportTicket.Priority.HIGH),
                new SupportTicket("Security breach", SupportTicket.Priority.CRITICAL)
        };

        // Process tickets
        for (SupportTicket ticket : tickets) {
            System.out.println("\n--- Processing Ticket ---");
            level1.handleRequest(ticket);
        }
    }
}
