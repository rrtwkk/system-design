# Strategy Pattern

## UML Class Diagram

```
┌─────────────────┐              ┌─────────────────┐
│  SortStrategy   │              │   SortContext   │
│  <<interface>>  │              │    (Context)    │
├─────────────────┤              ├─────────────────┤
│+ sort(int[]): void│             │- strategy: SortStrategy│
└─────────────────┘              ├─────────────────┤
         △                       │+ SortContext(SortStrategy)│
         │                       │+ setStrategy(SortStrategy)│
    ┌────┼────┐                  │+ executeSort(int[]): void│
    │    │    │                  └─────────────────┘
┌─────────┐ ┌─────────┐ ┌─────────┐        │
│BubbleSort│ │QuickSort│ │MergeSort│        │uses
├─────────┤ ├─────────┤ ├─────────┤        ▼
│+ sort(int[])│ │+ sort(int[])│ │+ sort(int[])│ ┌─────────────────┐
└─────────┘ │- quickSort()│ │- mergeSort()│ │  SortStrategy   │
            │- partition()│ │- merge()    │ └─────────────────┘
            └─────────┘ └─────────┘
```

## Key Components
- **Strategy**: SortStrategy interface
- **Concrete Strategies**: BubbleSort, QuickSort, MergeSort
- **Context**: SortContext class
- **Client**: StrategyDemo

## Purpose
Defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.
