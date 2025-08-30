package org.ritwik.design_pattern.behavioural.mediator;

// Mediator interface
public interface Mediator {
    void sendMessage(String message, User user);

    void addUser(User user);
}
