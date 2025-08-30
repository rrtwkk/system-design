# Observer Pattern

## UML Class Diagram

```
┌─────────────────┐              ┌─────────────────┐
│     Subject     │              │    Observer     │
│  <<interface>>  │              │  <<interface>>  │
├─────────────────┤              ├─────────────────┤
│+ addObserver(Observer)│         │+ update(String): void│
│+ removeObserver(Observer)│      └─────────────────┘
│+ notifyObservers(): void│               △
└─────────────────┘                      │
         △                               │
         │                               │
┌─────────────────┐                      │
│   NewsAgency    │                      │
│(Concrete Subject)│                     │
├─────────────────┤              ┌─────────────────┐
│- observers: List<Observer>│     │   NewsChannel   │
│- latestNews: String│           │(Concrete Observer)│
├─────────────────┤              ├─────────────────┤
│+ addObserver(Observer)│         │- name: String   │
│+ removeObserver(Observer)│      ├─────────────────┤
│+ notifyObservers(): void│       │+ NewsChannel(String)│
│+ setNews(String): void│         │+ update(String): void│
└─────────────────┘              │+ getName(): String│
         │                       └─────────────────┘
         │notifies                        △
         └────────────────────────────────┘
```

## Key Components
- **Subject**: Subject interface
- **Concrete Subject**: NewsAgency class
- **Observer**: Observer interface  
- **Concrete Observer**: NewsChannel class

## Purpose
Defines a one-to-many dependency between objects so that when one object changes state, all dependents are notified automatically.
