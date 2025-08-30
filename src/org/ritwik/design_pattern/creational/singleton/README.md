# Singleton Pattern

## UML Class Diagram

```
┌─────────────────────────┐
│    DatabaseConnection   │
├─────────────────────────┤
│ - instance: DatabaseConnection │
│ - connectionString: String │
├─────────────────────────┤
│ - DatabaseConnection()  │
│ + getInstance(): DatabaseConnection │
│ + executeQuery(String): void │
│ + getConnectionString(): String │
└─────────────────────────┘
```

## Key Components
- **Singleton Class**: DatabaseConnection
- **Private Constructor**: Prevents direct instantiation
- **Static Instance**: Holds the single instance
- **Public Access Method**: getInstance() provides global access point

## Implementation Details
- Uses double-checked locking for thread safety
- Volatile keyword ensures proper memory synchronization
- Lazy initialization (instance created only when needed)

## Purpose
Ensures a class has only one instance and provides global access to it.
