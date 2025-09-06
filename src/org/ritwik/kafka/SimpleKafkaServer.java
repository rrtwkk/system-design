package org.ritwik.kafka;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import org.apache.kafka.common.utils.Time;
import scala.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * A simple embedded Kafka server for demonstration purposes.
 * This class provides basic functionality to start and stop a Kafka broker.
 */
public class SimpleKafkaServer {
    private KafkaServer kafkaServer;
    private Path tempLogDir;
    private final int port;
    private final String zookeeperConnect;

    public SimpleKafkaServer(int port, String zookeeperConnect) {
        this.port = port;
        this.zookeeperConnect = zookeeperConnect;
    }

    /**
     * Starts the Kafka server with basic configuration
     */
    public void start() throws IOException {
        // Create temporary directory for Kafka logs
        tempLogDir = Files.createTempDirectory("kafka-logs");

        Properties props = new Properties();
        props.put("broker.id", "1");
        props.put("listeners", "PLAINTEXT://localhost:" + port);
        props.put("log.dirs", tempLogDir.toAbsolutePath().toString());
        props.put("zookeeper.connect", zookeeperConnect);
        props.put("num.network.threads", "3");
        props.put("num.io.threads", "8");
        props.put("socket.send.buffer.bytes", "102400");
        props.put("socket.receive.buffer.bytes", "102400");
        props.put("socket.request.max.bytes", "104857600");
        props.put("num.partitions", "1");
        props.put("num.recovery.threads.per.data.dir", "1");
        props.put("offsets.topic.replication.factor", "1");
        props.put("transaction.state.log.replication.factor", "1");
        props.put("transaction.state.log.min.isr", "1");
        props.put("log.retention.hours", "168");
        props.put("log.segment.bytes", "1073741824");
        props.put("log.retention.check.interval.ms", "300000");
        props.put("auto.create.topics.enable", "true");

        KafkaConfig config = new KafkaConfig(props);
        kafkaServer = new KafkaServer(config, Time.SYSTEM, Option.empty(), false);
        kafkaServer.startup();

        System.out.println("Kafka Server started on port: " + port);
    }

    /**
     * Stops the Kafka server and cleans up resources
     */
    public void stop() {
        if (kafkaServer != null) {
            kafkaServer.shutdown();
            kafkaServer.awaitShutdown();
            System.out.println("Kafka Server stopped");
        }

        // Clean up temporary log directory
        if (tempLogDir != null) {
            try {
                deleteDirectory(tempLogDir);
            } catch (IOException e) {
                System.err.println("Failed to clean up temp directory: " + e.getMessage());
            }
        }
    }

    /**
     * Check if the server is running
     */
    public boolean isRunning() {
        return kafkaServer != null;
    }

    /**
     * Get the server's port
     */
    public int getPort() {
        return port;
    }

    /**
     * Recursively delete a directory and its contents
     */
    private void deleteDirectory(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walk(path)
                    .sorted((p1, p2) -> p2.compareTo(p1)) // Delete files before directories
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            System.err.println("Failed to delete: " + p + " - " + e.getMessage());
                        }
                    });
        }
    }
}
