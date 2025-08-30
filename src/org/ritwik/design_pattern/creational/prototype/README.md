# Prototype Pattern

## UML Class Diagram

```
┌─────────────────┐
│   Prototype     │
│  <<interface>>  │
├─────────────────┤
│ + clone(): Prototype │
└─────────────────┘
         △
         │
┌─────────────────┐
│     Shape       │
│  <<abstract>>   │
├─────────────────┤
│ # color: String │
│ # x: int        │
│ # y: int        │
├─────────────────┤
│ + Shape()       │
│ + Shape(Shape)  │
│ + draw(): void  │
│ + getters/setters │
└─────────────────┘
         △
         │
    ┌────┴────┐
    │         │
┌─────────┐ ┌─────────────┐
│ Circle  │ │ Rectangle   │
├─────────┤ ├─────────────┤
│- radius:int│ │- width: int │
│         │ │- height: int│
├─────────┤ ├─────────────┤
│+ Circle()│ │+ Rectangle()│
│+ Circle(Circle)│ │+ Rectangle(Rectangle)│
│+ clone(): Prototype│ │+ clone(): Prototype│
│+ draw(): void│ │+ draw(): void│
│+ getters/setters│ │+ getters/setters│
└─────────┘ └─────────────┘
```

## Key Components
- **Prototype Interface**: Defines clone() method
- **Abstract Prototype**: Shape class with common properties
- **Concrete Prototypes**: Circle, Rectangle with specific properties
- **Client**: PrototypeDemo that clones objects

## Implementation Details
- Copy constructor pattern for deep cloning
- Each concrete class implements its own clone method
- Supports both shallow and deep copying as needed

## Purpose
Creates new objects by cloning existing instances rather than creating from scratch.
