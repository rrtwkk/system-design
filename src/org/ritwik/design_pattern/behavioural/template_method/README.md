# Template Method Pattern

## UML Class Diagram

```
┌─────────────────────────┐
│     DataProcessor       │
│     <<abstract>>        │
├─────────────────────────┤
├─────────────────────────┤
│+ processData(): void (final)│
│- readData(): void       │
│# processData_internal(): void │
│# writeData(): void      │
└─────────────────────────┘
           △
           │
    ┌──────┼──────┐
    │      │      │
┌─────────┐ ┌─────────┐ ┌─────────┐
│CSVProcessor│ │XMLProcessor│ │JSONProcessor│
├─────────┤ ├─────────┤ ├─────────┤
│+ processData_internal()│ │+ processData_internal()│ │+ processData_internal()│
│+ writeData()│ │+ writeData()│ │(uses inherited writeData)│
└─────────┘ └─────────┘ └─────────┘
```

## Key Components
- **Abstract Class**: DataProcessor with template method
- **Template Method**: processData() - defines algorithm skeleton
- **Primitive Operations**: readData(), processData_internal(), writeData()
- **Concrete Classes**: CSVProcessor, XMLProcessor, JSONProcessor

## Implementation Details
- Template method is marked `final` to prevent overriding
- Abstract methods must be implemented by subclasses
- Hook methods can optionally be overridden

## Purpose
Defines the skeleton of an algorithm in a base class, letting subclasses override specific steps without changing the algorithm's structure.
