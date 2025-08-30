# Mediator Pattern

## UML Class Diagram

```
┌─────────────────┐              ┌─────────────────┐
│    Mediator     │              │      User       │
│  <<interface>>  │              │   <<abstract>>  │
├─────────────────┤              ├─────────────────┤
│+ sendMessage(String, User)│    │# mediator: Mediator│
│+ addUser(User)  │              │# name: String   │
└─────────────────┘              ├─────────────────┤
         △                       │+ User(Mediator, String)│
         │                       │+ send(String): void│
         │                       │+ receive(String): void│
┌─────────────────┐              │+ getName(): String│
│    ChatRoom     │              └─────────────────┘
│(Concrete Mediator)│                     △
├─────────────────┤                      │
│- users: List<User>│                     │
├─────────────────┤              ┌─────────────────┐
│+ sendMessage(String, User)│    │    ChatUser     │
│+ addUser(User): void│          │(Concrete Colleague)│
└─────────────────┘              ├─────────────────┤
         △                       │+ ChatUser(Mediator, String)│
         │knows about             │+ send(String): void│
         │                       │+ receive(String): void│
         └───────────────────────┤─ ─ ─ ─ ─ ─ ─ ─ ─│
                  communicates via mediator
```

## Key Components
- **Mediator**: Mediator interface
- **Concrete Mediator**: ChatRoom class
- **Colleague**: User abstract class
- **Concrete Colleague**: ChatUser class

## Purpose
Defines how objects interact with each other through a mediator, promoting loose coupling by preventing direct references.
