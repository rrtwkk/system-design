# Adapter Pattern

## UML Class Diagram

```
┌─────────────────┐    ┌─────────────────────┐
│   MediaPlayer   │    │ AdvancedMediaPlayer │
│  <<interface>>  │    │   <<interface>>     │
├─────────────────┤    ├─────────────────────┤
│+ play(String,   │    │+ playVlc(String)    │
│       String)   │    │+ playMp4(String)    │
└─────────────────┘    └─────────────────────┘
         △                       △
         │                       │
         │              ┌────────┴────────┐
         │              │                 │
┌─────────────────┐ ┌─────────┐     ┌─────────┐
│   AudioPlayer   │ │VlcPlayer│     │Mp4Player│
├─────────────────┤ ├─────────┤     ├─────────┤
│- mediaAdapter   │ │+ playVlc()│   │+ playMp4()│
├─────────────────┤ │+ playMp4()│   │+ playVlc()│
│+ play(String,   │ └─────────┘     └─────────┘
│       String)   │
└─────────────────┘
         │
         │uses
         ▼
┌─────────────────┐
│  MediaAdapter   │
├─────────────────┤
│- advancedPlayer │
├─────────────────┤
│+ MediaAdapter(  │
│      String)    │
│+ play(String,   │
│       String)   │
└─────────────────┘
```

## Key Components
- **Target**: MediaPlayer interface
- **Adapter**: MediaAdapter class
- **Adaptee**: AdvancedMediaPlayer interface with VlcPlayer, Mp4Player
- **Client**: AudioPlayer class

## Purpose
Allows incompatible interfaces to work together by wrapping an existing class with a new interface.
