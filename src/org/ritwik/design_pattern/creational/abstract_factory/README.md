# Abstract Factory Pattern

## UML Class Diagram

```
┌─────────────────┐
│   GUIFactory    │
│   <<interface>> │
├─────────────────┤
│ + createButton()│
│ + createCheckbox()│
└─────────────────┘
         △
         │
    ┌────┴────┐
    │         │
┌───────────┐ ┌──────────┐
│WindowsFactory│ │MacFactory│
├───────────┤ ├──────────┤
│+ createButton()│ │+ createButton()│
│+ createCheckbox()│ │+ createCheckbox()│
└───────────┘ └──────────┘
    │             │
    │creates      │creates
    ▼             ▼
┌─────────────┐ ┌─────────────┐
│   Button    │ │  Checkbox   │
│<<interface>>│ │<<interface>>│
├─────────────┤ ├─────────────┤
│+ render()   │ │+ render()   │
│+ onClick()  │ │+ toggle()   │
└─────────────┘ └─────────────┘
      △               △
      │               │
  ┌───┴───┐       ┌───┴───┐
  │       │       │       │
┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐
│WindowsButton│ │MacButton│ │WindowsCheckbox│ │MacCheckbox│
├─────────┤ ├─────────┤ ├─────────┤ ├─────────┤
│+ render()│ │+ render()│ │+ render()│ │+ render()│
│+ onClick()│ │+ onClick()│ │+ toggle()│ │+ toggle()│
└─────────┘ └─────────┘ └─────────┘ └─────────┘
```

## Key Components
- **Abstract Factory**: GUIFactory interface
- **Concrete Factories**: WindowsFactory, MacFactory
- **Abstract Products**: Button, Checkbox interfaces
- **Concrete Products**: WindowsButton, MacButton, WindowsCheckbox, MacCheckbox
- **Client**: AbstractFactoryDemo

## Purpose
Creates families of related objects without specifying their concrete classes.
