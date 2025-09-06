package org.ritwik.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * A simple Kafka producer that can send string messages to topics.
 */
public class SimpleKafkaProducer {
    private final KafkaProducer<String, String> producer;

    public SimpleKafkaProducer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "1"); // Wait for leader acknowledgment
        props.put(ProducerConfig.RETRIES_CONFIG, "3");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

        this.producer = new KafkaProducer<>(props);
    }

    /**
     * Sends a message to the specified topic
     */
    public Future<RecordMetadata> sendMessage(String topic, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        return producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Failed to send message: " + exception.getMessage());
            } else {
                System.out.println("Message sent to topic '" + metadata.topic() +
                        "' partition " + metadata.partition() +
                        " at offset " + metadata.offset());
            }
        });
    }

    /**
     * Sends a message with a specific key to the specified topic
     */
    public Future<RecordMetadata> sendMessage(String topic, String key, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        return producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Failed to send message: " + exception.getMessage());
            } else {
                System.out.println("Message with key '" + key + "' sent to topic '" + metadata.topic() +
                        "' partition " + metadata.partition() +
                        " at offset " + metadata.offset());
            }
        });
    }

    /**
     * Flushes any buffered records and closes the producer
     */
    public void close() {
        if (producer != null) {
            producer.flush();
            producer.close();
            System.out.println("Producer closed");
        }
    }

    /**
     * Flushes all buffered records
     */
    public void flush() {
        producer.flush();
    }
}
