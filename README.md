# System Design Implementations

This repository contains simple, easy-to-understand implementations of various system design patterns and algorithms.

## Rate Limiting Algorithms

### 1. Sliding Window Log Rate Limiter

A simple, thread-safe implementation of the sliding window log rate limiting algorithm located in `src/org/ritwik/rate-limiter/sliding-window-log/`.

#### Overview

The sliding window log rate limiter maintains a log of timestamps for each client and uses a sliding time window to enforce rate limits. It provides exact counting within the time window.

**Time Complexity:** O(N) where N is the number of requests in the current window  
**Space Complexity:** O(M×N) where M is number of clients and N is requests per window

#### Files

- **`SlidingWindowLogRateLimiter.java`** - Simple rate limiter with exact timestamp tracking
- **`RateLimiterDemo.java`** - Basic demonstration

#### Features

- ✅ **Simple & Clean**: Easy to understand implementation
- ✅ **Thread-safe**: Uses synchronized methods
- ✅ **Exact counting**: Precise request counting within time windows
- ✅ **Per-client tracking**: Independent rate limits for each client

#### Usage

```java
// Create rate limiter: 10 requests per 60 seconds
SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(10, 60000);

// Check if request is allowed
if (rateLimiter.allowRequest("user123")) {
    System.out.println("Request allowed");
} else {
    System.out.println("Rate limit exceeded");
}

// Get current statistics
int currentCount = rateLimiter.getCurrentRequestCount("user123");
int remaining = rateLimiter.getRemainingRequests("user123");

System.out.printf("Current: %d, Remaining: %d%n", currentCount, remaining);
```

#### Running the Demo

```bash
cd src/org/ritwik/rate-limiter/sliding-window-log
javac *.java
java RateLimiterDemo
```

### 2. Sliding Window Counter Rate Limiter

A simple, memory-efficient implementation of the sliding window counter rate limiting algorithm located in `src/org/ritwik/rate-limiter/sliding-window-counter/`.

#### Overview

The sliding window counter rate limiter uses time-based buckets to track request counts within a sliding window. It provides approximate counting with better memory efficiency than the log-based approach.

**Time Complexity:** O(B) where B is the number of active buckets  
**Space Complexity:** O(M×B) where M is number of clients and B is buckets per client

#### Files

- **`SlidingWindowCounterRateLimiter.java`** - Simple bucket-based rate limiter
- **`CounterRateLimiterDemo.java`** - Basic demonstration with bucket comparison

#### Features

- ✅ **Memory efficient**: Uses buckets instead of individual timestamps
- ✅ **Simple bucket logic**: Easy-to-understand time bucket management
- ✅ **Configurable buckets**: Adjustable bucket size for precision vs memory trade-off
- ✅ **Thread-safe**: Uses synchronized methods
- ✅ **Auto cleanup**: Automatically removes old buckets

#### Usage

```java
// Create rate limiter: 100 requests per 60 seconds (default buckets)
SlidingWindowCounterRateLimiter rateLimiter = 
    new SlidingWindowCounterRateLimiter(100, 60000);

// Or with custom bucket size
SlidingWindowCounterRateLimiter customLimiter = 
    new SlidingWindowCounterRateLimiter(100, 60000, 5000); // 5-second buckets

// Check if request is allowed
if (rateLimiter.allowRequest("user123")) {
    System.out.println("Request allowed");
} else {
    System.out.println("Rate limit exceeded");
}

// Get statistics
int currentCount = rateLimiter.getCurrentRequestCount("user123");
int remaining = rateLimiter.getRemainingRequests("user123");

System.out.printf("Current: %d, Remaining: %d%n", currentCount, remaining);
```

#### Running the Demo

```bash
cd src/org/ritwik/rate-limiter/sliding-window-counter
javac *.java
java CounterRateLimiterDemo
```

## Algorithm Comparison

| Feature | Sliding Window Log | Sliding Window Counter |
|---------|-------------------|----------------------|
| **Accuracy** | Exact | Approximate |
| **Memory Usage** | Higher (stores timestamps) | Lower (stores counters) |
| **Time Complexity** | O(N) per request | O(B) per request |
| **Implementation** | Simple timestamp list | Simple bucket map |
| **Best For** | When exact counting needed | When memory efficiency important |

## Implementation Details

### Sliding Window Log Algorithm

- Maintains a list of timestamps per client
- Removes expired timestamps on each request
- Exact counting but higher memory usage

### Sliding Window Counter Algorithm

- Divides time into buckets and counts requests per bucket
- Removes expired buckets automatically
- Approximate counting but lower memory usage

### Thread Safety

Both implementations use simple `synchronized` methods for thread safety, making them easy to understand and safe for concurrent use.

## When to Use Each Algorithm

### Choose Sliding Window Log when:
- **Exact precision** is required
- Memory usage is acceptable
- Simple implementation preferred

### Choose Sliding Window Counter when:
- **Memory efficiency** is important
- Approximate counting is acceptable
- Want **configurable precision** via bucket size

Both implementations are simplified for clarity and ease of understanding while maintaining the core functionality of their respective algorithms.