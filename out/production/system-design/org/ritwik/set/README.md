# Simple HashSet Implementation

A from-scratch implementation of a HashSet in Java, designed for backend coding interviews.

## Key Algorithms & Concepts

### 1. Hash Function
- **Algorithm**: `Math.abs(element.hashCode()) % capacity`
- **Purpose**: Maps elements to bucket indices
- **Time Complexity**: O(1)

### 2. Collision Handling
- **Strategy**: Separate Chaining with LinkedList
- **Why**: Simple to implement and understand
- **Alternative**: Open Addressing (linear/quadratic probing)

### 3. Dynamic Resizing
- **Trigger**: Load factor > 0.75
- **Strategy**: Double the capacity and rehash all elements
- **Purpose**: Maintain O(1) average performance

### 4. Load Factor Management
- **Formula**: size / capacity
- **Target**: < 0.75 to minimize collisions
- **Trade-off**: Memory vs. Performance

## Core Operations

| Operation | Average Time | Worst Case | Space |
|-----------|-------------|------------|-------|
| `add()`   | O(1)        | O(n)       | O(1)  |
| `remove()`| O(1)        | O(n)       | O(1)  |
| `contains()` | O(1)     | O(n)       | O(1)  |

## Interview Points

1. **Hash Function Quality**: A good hash function distributes elements uniformly
2. **Collision Resolution**: Know both chaining and open addressing
3. **Resizing Strategy**: When and how to resize the hash table
4. **Memory vs Performance**: Load factor trade-offs
5. **Worst Case Scenarios**: What causes O(n) operations?

## Usage

```java
SimpleHashSet<String> set = new SimpleHashSet<>();

// Basic operations
set.add("element");
boolean exists = set.contains("element");
boolean removed = set.remove("element");

// Size and statistics
int size = set.size();
String stats = set.getStats();
```

## Run Demo

```bash
javac -d . src/org/ritwik/set/*.java
java org.ritwik.set.SetDemo
```

The demo showcases:
- Basic CRUD operations
- Duplicate handling
- Resize behavior
- Collision handling
- Performance characteristics
