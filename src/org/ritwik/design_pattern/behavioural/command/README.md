# Command Pattern

## UML Class Diagram

```
┌─────────────────┐      ┌─────────────────┐
│    Command      │      │  EditorInvoker  │
│  <<interface>>  │      │    (Invoker)    │
├─────────────────┤      ├─────────────────┤
│+ execute(): void│      │- history: Stack<Command>│
│+ undo(): void   │      ├─────────────────┤
└─────────────────┘      │+ executeCommand(Command)│
         △               │+ undo(): void   │
         │               └─────────────────┘
    ┌────┴────┐                   │
    │         │                   │uses
┌─────────────┐ ┌─────────────┐   ▼
│WriteCommand │ │DeleteCommand│ ┌─────────────────┐
├─────────────┤ ├─────────────┤ │    Command      │
│- editor: TextEditor│ │- editor: TextEditor│ └─────────────────┘
│- text: String│ │- deletedText: String│
├─────────────┤ │- length: int│
│+ WriteCommand│ ├─────────────┤
│  (TextEditor, String)│ │+ DeleteCommand│
│+ execute(): void│ │  (TextEditor, int)│
│+ undo(): void│ │+ execute(): void│
└─────────────┘ │+ undo(): void│
                └─────────────┘
                        │
                        │operates on
                        ▼
                ┌─────────────────┐
                │   TextEditor    │
                │   (Receiver)    │
                ├─────────────────┤
                │- content: StringBuilder│
                ├─────────────────┤
                │+ write(String): void│
                │+ delete(int): void│
                │+ getContent(): String│
                └─────────────────┘
```

## Key Components
- **Command**: Command interface
- **Concrete Commands**: WriteCommand, DeleteCommand
- **Receiver**: TextEditor class
- **Invoker**: EditorInvoker with undo functionality

## Purpose
Encapsulates requests as objects, allowing parameterization, queuing, logging, and undo operations.
