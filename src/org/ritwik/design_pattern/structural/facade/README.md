# Facade Pattern

## UML Class Diagram

```
┌─────────────────────┐
│   ComputerFacade    │
├─────────────────────┤
│ - cpu: CPU          │
│ - memory: Memory    │
│ - hardDrive: HardDrive │
├─────────────────────┤
│ + ComputerFacade()  │
│ + start(): void     │
└─────────────────────┘
          │
          │uses
          ▼
    ┌─────────────┐
    │ Subsystems  │
    └─────────────┘
          │
    ┌─────┼─────┐
    │     │     │
┌─────┐ ┌─────────┐ ┌─────────────┐
│ CPU │ │ Memory  │ │  HardDrive  │
├─────┤ ├─────────┤ ├─────────────┤
│+ freeze()│ │+ load(long,│ │+ read(long, int):│
│+ jump(long)│ │    byte[])│ │      byte[]     │
│+ execute()│ └─────────┘ └─────────────┘
└─────┘
```

## Key Components
- **Facade**: ComputerFacade class
- **Subsystems**: CPU, Memory, HardDrive classes
- **Client**: FacadeDemo

## Purpose
Provides a simplified interface to a complex subsystem, making it easier to use and reducing dependencies.
