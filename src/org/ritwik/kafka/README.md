# Simple Kafka Implementation

A basic Kafka server implementation in Java for demonstration and learning purposes.

## Components

### 1. SimpleZookeeperServer
- Embedded Zookeeper server required for Kafka coordination
- Automatically manages temporary data directories
- Configurable port (default: 2181)

### 2. SimpleKafkaServer  
- Embedded Kafka broker with basic configuration
- Depends on Zookeeper for cluster coordination
- Configurable port (default: 9092)
- Auto-creates topics when needed

### 3. SimpleKafkaProducer
- Sends messages to Kafka topics
- Supports both keyed and keyless messages
- Provides callback handling for send confirmations

### 4. SimpleKafkaConsumer
- Subscribes to topics and processes messages
- Supports consumer groups
- Configurable message handlers

## Usage

### Quick Start
```bash
# Build the project
mvn compile

# Run the demo
mvn exec:java -Dexec.mainClass="org.ritwik.kafka.KafkaDemo"
```

### Programmatic Usage

```java
// Start servers
SimpleZookeeperServer zookeeper = new SimpleZookeeperServer(2181);
zookeeper.start();

SimpleKafkaServer kafka = new SimpleKafkaServer(9092, "localhost:2181");
kafka.start();

// Create producer and send messages
SimpleKafkaProducer producer = new SimpleKafkaProducer("localhost:9092");
producer.sendMessage("my-topic", "Hello Kafka!");

// Create consumer and process messages
SimpleKafkaConsumer consumer = new SimpleKafkaConsumer("localhost:9092", "my-group");
consumer.subscribe("my-topic", message -> {
    System.out.println("Received: " + message);
});

// Cleanup
consumer.stop();
producer.close();
kafka.stop();
zookeeper.stop();
```

## Configuration

The implementation uses sensible defaults suitable for development and testing:
- Single broker setup
- Auto-topic creation enabled
- Minimal replication (factor of 1)
- Simple string serialization

## Notes

- This is a simplified implementation for educational purposes
- Not suitable for production use
- Temporary directories are automatically cleaned up
- All components run in embedded mode within the same JVM
