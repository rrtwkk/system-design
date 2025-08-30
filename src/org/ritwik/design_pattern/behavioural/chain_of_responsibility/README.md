# Chain of Responsibility Pattern

## UML Class Diagram

```
┌─────────────────────┐
│   SupportHandler    │
│    <<abstract>>     │
├─────────────────────┤
│ # nextHandler: SupportHandler │
├─────────────────────┤
│ + setNext(SupportHandler): void │
│ + handleRequest(SupportTicket): void │
└─────────────────────┘
           △
           │
    ┌──────┼──────┐
    │      │      │
┌─────────┐ ┌─────────┐ ┌─────────┐
│Level1Support│ │Level2Support│ │Level3Support│
├─────────┤ ├─────────┤ ├─────────┤
│+ handleRequest()│ │+ handleRequest()│ │+ handleRequest()│
└─────────┘ └─────────┘ └─────────┘

┌─────────────────────┐
│   SupportTicket     │
├─────────────────────┤
│ - issue: String     │
│ - priority: Priority│
├─────────────────────┤
│ + SupportTicket(String, Priority) │
│ + getIssue(): String│
│ + getPriority(): Priority │
└─────────────────────┘

┌─────────────────────┐
│      Priority       │
│      <<enum>>       │
├─────────────────────┤
│ LOW, MEDIUM, HIGH,  │
│ CRITICAL            │
└─────────────────────┘
```

## Key Components
- **Handler**: SupportHandler abstract class
- **Concrete Handlers**: Level1Support, Level2Support, Level3Support
- **Request**: SupportTicket with priority levels
- **Client**: ChainOfResponsibilityDemo

## Purpose
Passes requests along a chain of handlers until one handles it, avoiding coupling between sender and receiver.
