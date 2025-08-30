# Composite Pattern

## UML Class Diagram

```
┌─────────────────────────┐
│   FileSystemComponent   │
│     <<abstract>>        │
├─────────────────────────┤
│ # name: String          │
├─────────────────────────┤
│ + FileSystemComponent(String) │
│ + display(int): void    │
│ + getSize(): int        │
│ + add(FileSystemComponent): void │
│ + remove(FileSystemComponent): void │
│ + getName(): String     │
└─────────────────────────┘
           △
           │
      ┌────┴────┐
      │         │
┌──────────┐ ┌─────────────────────┐
│   File   │ │     Directory       │
│ (Leaf)   │ │   (Composite)       │
├──────────┤ ├─────────────────────┤
│- size: int│ │- children: List<FileSystemComponent>│
├──────────┤ ├─────────────────────┤
│+ File(String,│ │+ Directory(String) │
│       int)│ │+ add(FileSystemComponent)│
│+ display(int)│ │+ remove(FileSystemComponent)│
│+ getSize()│ │+ display(int): void │
└──────────┘ │+ getSize(): int     │
             └─────────────────────┘
                        │
                        │contains
                        ▼
              ┌─────────────────────────┐
              │   FileSystemComponent   │
              │        (children)       │
              └─────────────────────────┘
```

## Key Components
- **Component**: FileSystemComponent abstract class
- **Leaf**: File class (cannot have children)
- **Composite**: Directory class (can contain other components)
- **Client**: CompositeDemo

## Purpose
Composes objects into tree structures to represent part-whole hierarchies, allowing clients to treat individual objects and compositions uniformly.
