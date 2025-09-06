package org.ritwik.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * A simple Kafka consumer that can subscribe to topics and process messages.
 */
public class SimpleKafkaConsumer {
    private final KafkaConsumer<String, String> consumer;
    private volatile boolean running = false;
    private Thread consumerThread;

    public SimpleKafkaConsumer(String bootstrapServers, String groupId) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        this.consumer = new KafkaConsumer<>(props);
    }

    /**
     * Subscribes to a topic and starts consuming messages.
     * The messageHandler will be called for each received message.
     */
    public void subscribe(String topic, Consumer<String> messageHandler) {
        consumer.subscribe(Collections.singletonList(topic));
        running = true;

        consumerThread = new Thread(() -> {
            try {
                while (running) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message from topic '" + record.topic() +
                                "' partition " + record.partition() +
                                " at offset " + record.offset() +
                                ": " + record.value());
                        messageHandler.accept(record.value());
                    }
                }
            } catch (Exception e) {
                if (running) {
                    System.err.println("Consumer error: " + e.getMessage());
                }
            } finally {
                consumer.close();
            }
        });

        consumerThread.start();
        System.out.println("Consumer subscribed to topic: " + topic);
    }

    /**
     * Subscribes to a topic and starts consuming messages with key-value
     * processing.
     */
    public void subscribe(String topic, MessageHandler messageHandler) {
        consumer.subscribe(Collections.singletonList(topic));
        running = true;

        consumerThread = new Thread(() -> {
            try {
                while (running) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message from topic '" + record.topic() +
                                "' partition " + record.partition() +
                                " at offset " + record.offset() +
                                " with key '" + record.key() + "': " + record.value());
                        messageHandler.handle(record.key(), record.value());
                    }
                }
            } catch (Exception e) {
                if (running) {
                    System.err.println("Consumer error: " + e.getMessage());
                }
            } finally {
                consumer.close();
            }
        });

        consumerThread.start();
        System.out.println("Consumer subscribed to topic: " + topic);
    }

    /**
     * Stops the consumer
     */
    public void stop() {
        running = false;
        if (consumerThread != null) {
            try {
                consumerThread.join(5000); // Wait up to 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumer stopped");
    }

    /**
     * Check if the consumer is running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Functional interface for handling messages with key and value
     */
    @FunctionalInterface
    public interface MessageHandler {
        void handle(String key, String value);
    }
}
