# Memento Pattern

## UML Class Diagram

```
┌─────────────────────┐    ┌─────────────────────┐
│     TextEditor      │    │ TextEditorMemento   │
│    (Originator)     │    │     (Memento)       │
├─────────────────────┤    ├─────────────────────┤
│- content: StringBuilder│ │- content: String    │
│- cursorPosition: int│    │- cursorPosition: int│
├─────────────────────┤    ├─────────────────────┤
│+ write(String): void│    │+ TextEditorMemento( │
│+ setCursorPosition(int)│ │   String, int)      │
│+ showStatus(): void │    │+ getContent(): String│
│+ save(): TextEditorMemento│ │+ getCursorPosition(): int│
│+ restore(TextEditorMemento)│ └─────────────────────┘
└─────────────────────┘              △
         │                           │
         │creates                    │
         ▼                           │
┌─────────────────────┐              │
│   EditorHistory     │              │
│   (Caretaker)       │              │
├─────────────────────┤              │
│- history: List<TextEditorMemento>  │
├─────────────────────┤              │
│+ save(TextEditor): void│───────────┘
│+ undo(TextEditor): void│
│+ getHistorySize(): int│
└─────────────────────┘
```

## Key Components
- **Originator**: TextEditor class (creates and restores mementos)
- **Memento**: TextEditorMemento class (stores state)
- **Caretaker**: EditorHistory class (manages mementos)

## Purpose
Captures and restores an object's internal state without violating encapsulation, enabling undo functionality.
