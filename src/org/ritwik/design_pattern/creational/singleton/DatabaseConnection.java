package org.ritwik.design_pattern.creational.singleton;

// Thread-safe Singleton implementation
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private String connectionString;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        // Simulate expensive connection setup
        this.connectionString = "Connected to database at localhost:5432";
        System.out.println("Database connection established");
    }

    // Thread-safe getInstance method using double-checked locking
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }

    public String getConnectionString() {
        return connectionString;
    }
}
