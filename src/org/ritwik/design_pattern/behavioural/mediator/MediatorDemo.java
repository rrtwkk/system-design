package org.ritwik.design_pattern.behavioural.mediator;

public class MediatorDemo {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        System.out.println("\n--- Chat Session ---");
        alice.send("Hello everyone!");
        bob.send("Hey Alice!");
        charlie.send("Good morning all!");
    }
}
