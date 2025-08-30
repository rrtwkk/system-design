package org.ritwik.design_pattern.behavioural.mediator;

import java.util.ArrayList;
import java.util.List;

// Concrete Mediator
public class ChatRoom implements Mediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " joined the chat room");
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
