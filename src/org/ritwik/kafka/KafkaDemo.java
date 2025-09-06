package org.ritwik.kafka;

/**
 * Demo class showcasing the simple Kafka implementation.
 * This demonstrates how to start a Kafka server, producer, and consumer,
 * and how they work together to send and receive messages.
 */
public class KafkaDemo {
    private static final int ZOOKEEPER_PORT = 2181;
    private static final int KAFKA_PORT = 9092;
    private static final String TOPIC_NAME = "test-topic";

    public static void main(String[] args) {
        SimpleZookeeperServer zookeeperServer = null;
        SimpleKafkaServer kafkaServer = null;
        SimpleKafkaConsumer consumer = null;
        SimpleKafkaProducer producer = null;

        try {
            System.out.println("=== Starting Simple Kafka Demo ===");

            // 1. Start Zookeeper server
            System.out.println("\n1. Starting Zookeeper server...");
            zookeeperServer = new SimpleZookeeperServer(ZOOKEEPER_PORT);
            zookeeperServer.start();

            // 2. Start Kafka server
            System.out.println("\n2. Starting Kafka server...");
            kafkaServer = new SimpleKafkaServer(KAFKA_PORT, "localhost:" + ZOOKEEPER_PORT);
            kafkaServer.start();

            // Wait for Kafka to fully start
            Thread.sleep(3000);

            // 3. Create and start consumer
            System.out.println("\n3. Starting Kafka consumer...");
            consumer = new SimpleKafkaConsumer("localhost:" + KAFKA_PORT, "demo-consumer-group");
            consumer.subscribe(TOPIC_NAME, (key, value) -> {
                System.out.println("🔔 Processed message with key='" + key + "', value='" + value + "'");
            });

            // Wait for consumer to be ready
            Thread.sleep(2000);

            // 4. Create producer and send messages
            System.out.println("\n4. Creating producer and sending messages...");
            producer = new SimpleKafkaProducer("localhost:" + KAFKA_PORT);

            // Send some test messages
            for (int i = 1; i <= 5; i++) {
                String message = "Hello Kafka! Message #" + i;
                String key = "key-" + i;
                producer.sendMessage(TOPIC_NAME, key, message);
                Thread.sleep(1000); // Wait between messages
            }

            // Send a few more messages without keys
            for (int i = 6; i <= 8; i++) {
                String message = "Simple message #" + i;
                producer.sendMessage(TOPIC_NAME, message);
                Thread.sleep(1000);
            }

            producer.flush();

            System.out.println("\n5. All messages sent! Waiting for consumer to process...");
            Thread.sleep(3000);

            System.out.println("\n=== Demo completed successfully! ===");

        } catch (Exception e) {
            System.err.println("Demo failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cleanup resources
            System.out.println("\n6. Cleaning up resources...");

            if (consumer != null) {
                consumer.stop();
            }

            if (producer != null) {
                producer.close();
            }

            if (kafkaServer != null) {
                kafkaServer.stop();
            }

            if (zookeeperServer != null) {
                zookeeperServer.stop();
            }

            System.out.println("Demo cleanup completed.");
        }
    }

    /**
     * Helper method to demonstrate producer usage separately
     */
    public static void demonstrateProducer(String bootstrapServers, String topic) {
        SimpleKafkaProducer producer = new SimpleKafkaProducer(bootstrapServers);

        try {
            // Send sample messages
            producer.sendMessage(topic, "demo-key", "Hello from Simple Kafka Producer!");
            producer.sendMessage(topic, "System Design Demo Message");
            producer.flush();

            System.out.println("Producer demo completed");
        } finally {
            producer.close();
        }
    }

    /**
     * Helper method to demonstrate consumer usage separately
     */
    public static void demonstrateConsumer(String bootstrapServers, String topic, String groupId) {
        SimpleKafkaConsumer consumer = new SimpleKafkaConsumer(bootstrapServers, groupId);

        try {
            // Subscribe and process messages for 30 seconds
            consumer.subscribe(topic, message -> {
                System.out.println("Consumer received: " + message);
            });

            Thread.sleep(30000); // Run for 30 seconds

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            consumer.stop();
        }
    }
}
