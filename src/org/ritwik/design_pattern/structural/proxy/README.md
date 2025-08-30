# Proxy Pattern

## UML Class Diagram

```
┌─────────────────┐
│     Image       │
│  <<interface>>  │
├─────────────────┤
│+ display(): void│
└─────────────────┘
         △
         │
    ┌────┴────┐
    │         │
┌─────────────┐ ┌─────────────┐
│  RealImage  │ │ ProxyImage  │
├─────────────┤ ├─────────────┤
│- fileName: String│ │- realImage: RealImage│
├─────────────┤ │- fileName: String│
│+ RealImage(String)│ ├─────────────┤
│- loadFromDisk()│ │+ ProxyImage(String)│
│+ display(): void│ │+ display(): void│
└─────────────┘ └─────────────┘
                        │
                        │creates when needed
                        ▼
                ┌─────────────┐
                │  RealImage  │
                │  (subject)  │
                └─────────────┘
```

## Key Components
- **Subject**: Image interface
- **Real Subject**: RealImage class (expensive to create)
- **Proxy**: ProxyImage class (controls access to RealImage)
- **Client**: ProxyDemo

## Purpose
Provides a placeholder/surrogate for another object to control access to it, often for lazy loading, caching, or access control.
