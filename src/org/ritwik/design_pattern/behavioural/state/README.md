# State Pattern

## UML Class Diagram

```
┌─────────────────┐
│     State       │
│  <<interface>>  │
├─────────────────┤
│+ insertCoin(VendingMachine)│
│+ selectProduct(VendingMachine)│
│+ dispenseProduct(VendingMachine)│
└─────────────────┘
         △
         │
    ┌────┼────┐
    │    │    │
┌─────────┐ ┌─────────────────┐ ┌─────────────────────┐
│IdleState│ │CoinInsertedState│ │ProductSelectedState │
├─────────┤ ├─────────────────┤ ├─────────────────────┤
│+ insertCoin()│ │+ insertCoin()  │ │+ insertCoin()       │
│+ selectProduct()│ │+ selectProduct()│ │+ selectProduct()    │
│+ dispenseProduct()│ │+ dispenseProduct()│ │+ dispenseProduct()  │
└─────────┘ └─────────────────┘ └─────────────────────┘
     △               △                    △
     │               │                    │
     └───────────────┼────────────────────┘
                     │
┌─────────────────────────┐
│    VendingMachine       │
│      (Context)          │
├─────────────────────────┤
│- idleState: State       │
│- coinInsertedState: State│
│- productSelectedState: State│
│- currentState: State    │
│- productCount: int      │
├─────────────────────────┤
│+ VendingMachine()       │
│+ setState(State): void  │
│+ insertCoin(): void     │
│+ selectProduct(): void  │
│+ dispenseProduct(): void│
│+ getStates...(): State  │
│+ getProductCount(): int │
│+ decrementProductCount()│
└─────────────────────────┘
```

## Key Components
- **State**: State interface
- **Concrete States**: IdleState, CoinInsertedState, ProductSelectedState
- **Context**: VendingMachine class

## Purpose
Allows an object to alter its behavior when its internal state changes, appearing as if the object changed its class.
