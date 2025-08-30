# Bridge Pattern

## UML Class Diagram

```
┌─────────────────┐              ┌─────────────────┐
│     Remote      │              │     Device      │
│  (Abstraction)  │◇────────────▷│  <<interface>>  │
├─────────────────┤              ├─────────────────┤
│ # device: Device│              │+ isEnabled(): boolean│
├─────────────────┤              │+ enable(): void │
│+ Remote(Device) │              │+ disable(): void│
│+ togglePower()  │              │+ getVolume(): int│
│+ volumeDown()   │              │+ setVolume(int) │
│+ volumeUp()     │              │+ getChannel(): int│
│+ channelDown()  │              │+ setChannel(int)│
│+ channelUp()    │              └─────────────────┘
└─────────────────┘                       △
         △                                │
         │                       ┌────────┴────────┐
┌─────────────────┐              │                 │
│ AdvancedRemote  │        ┌─────────────┐   ┌─────────────┐
│(Refined Abstraction)│     │ Television  │   │    Radio    │
├─────────────────┤        ├─────────────┤   ├─────────────┤
│+ AdvancedRemote │        │- on: boolean│   │- on: boolean│
│    (Device)     │        │- volume: int│   │- volume: int│
│+ mute(): void   │        │- channel: int│  │- channel: int│
│+ setChannel(int)│        ├─────────────┤   ├─────────────┤
└─────────────────┘        │+ isEnabled()│   │+ isEnabled()│
                           │+ enable()   │   │+ enable()   │
                           │+ disable()  │   │+ disable()  │
                           │+ getVolume()│   │+ getVolume()│
                           │+ setVolume()│   │+ setVolume()│
                           │+ getChannel()│  │+ getChannel()│
                           │+ setChannel()│  │+ setChannel()│
                           └─────────────┘   └─────────────┘
```

## Key Components
- **Abstraction**: Remote class
- **Refined Abstraction**: AdvancedRemote
- **Implementation**: Device interface
- **Concrete Implementations**: Television, Radio

## Purpose
Separates abstraction from implementation, allowing both to vary independently.
