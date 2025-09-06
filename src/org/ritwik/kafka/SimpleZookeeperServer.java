package org.ritwik.kafka;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * A simple embedded Zookeeper server for testing Kafka.
 * Zookeeper is required for Kafka to manage cluster coordination.
 */
public class SimpleZookeeperServer {
    private Thread zookeeperThread;
    private Path tempDataDir;
    private final int port;
    private volatile boolean running = false;

    public SimpleZookeeperServer(int port) {
        this.port = port;
    }

    /**
     * Starts the Zookeeper server in a separate thread
     */
    public void start() throws IOException {
        tempDataDir = Files.createTempDirectory("zookeeper-data");

        Properties props = new Properties();
        props.put("dataDir", tempDataDir.toAbsolutePath().toString());
        props.put("clientPort", String.valueOf(port));
        props.put("tickTime", "2000");
        props.put("initLimit", "5");
        props.put("syncLimit", "2");

        zookeeperThread = new Thread(() -> {
            try {
                QuorumPeerConfig quorumConfig = new QuorumPeerConfig();
                quorumConfig.parseProperties(props);

                ServerConfig serverConfig = new ServerConfig();
                serverConfig.readFrom(quorumConfig);

                ZooKeeperServerMain zooKeeperServer = new ZooKeeperServerMain();
                running = true;
                zooKeeperServer.runFromConfig(serverConfig);
            } catch (Exception e) {
                System.err.println("Zookeeper startup failed: " + e.getMessage());
                running = false;
            }
        });

        zookeeperThread.setDaemon(true);
        zookeeperThread.start();

        // Wait a bit for Zookeeper to start
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Zookeeper Server started on port: " + port);
    }

    /**
     * Stops the Zookeeper server
     */
    public void stop() {
        running = false;
        if (zookeeperThread != null) {
            zookeeperThread.interrupt();
            try {
                zookeeperThread.join(5000); // Wait up to 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Clean up temporary data directory
        if (tempDataDir != null) {
            try {
                deleteDirectory(tempDataDir);
            } catch (IOException e) {
                System.err.println("Failed to clean up temp directory: " + e.getMessage());
            }
        }

        System.out.println("Zookeeper Server stopped");
    }

    /**
     * Check if the server is running
     */
    public boolean isRunning() {
        return running && zookeeperThread != null && zookeeperThread.isAlive();
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
