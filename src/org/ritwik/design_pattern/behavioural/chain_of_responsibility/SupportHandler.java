package org.ritwik.design_pattern.behavioural.chain_of_responsibility;

// Handler interface
public abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNext(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(SupportTicket ticket);
}
