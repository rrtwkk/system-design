package org.ritwik.design_pattern.creational.singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        // Get the first instance
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.executeQuery("SELECT * FROM users");

        // Get the second instance
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.executeQuery("SELECT * FROM products");

        // Verify they are the same instance
        System.out.println("Are both instances the same? " + (db1 == db2));
        System.out.println("DB1 connection: " + db1.getConnectionString());
        System.out.println("DB2 connection: " + db2.getConnectionString());
    }
}
