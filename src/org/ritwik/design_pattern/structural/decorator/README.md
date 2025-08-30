# Decorator Pattern

## UML Class Diagram

```
┌─────────────────┐
│     Coffee      │
│  <<interface>>  │
├─────────────────┤
│+ getDescription(): String │
│+ getCost(): double │
└─────────────────┘
         △
         │
    ┌────┴────┐
    │         │
┌─────────────┐ ┌─────────────────┐
│SimpleCoffee │ │ CoffeeDecorator │
├─────────────┤ │  <<abstract>>   │
│+ getDescription()│ ├─────────────────┤
│+ getCost()  │ │ # coffee: Coffee│
└─────────────┘ ├─────────────────┤
                │+ CoffeeDecorator│
                │    (Coffee)     │
                │+ getDescription()│
                │+ getCost()      │
                └─────────────────┘
                         △
                         │
              ┌──────────┼──────────┐
              │          │          │
        ┌─────────────┐ ┌─────────────┐ ┌─────────────┐
        │MilkDecorator│ │SugarDecorator│ │WhipDecorator│
        ├─────────────┤ ├─────────────┤ ├─────────────┤
        │+ MilkDecorator│ │+ SugarDecorator│ │+ WhipDecorator│
        │    (Coffee) │ │    (Coffee) │ │    (Coffee) │
        │+ getDescription()│ │+ getDescription()│ │+ getDescription()│
        │+ getCost()  │ │+ getCost()  │ │+ getCost()  │
        └─────────────┘ └─────────────┘ └─────────────┘
```

## Key Components
- **Component**: Coffee interface
- **Concrete Component**: SimpleCoffee
- **Base Decorator**: CoffeeDecorator abstract class
- **Concrete Decorators**: MilkDecorator, SugarDecorator, WhipDecorator

## Purpose
Adds new functionality to objects dynamically without altering their structure by wrapping them in decorator objects.
