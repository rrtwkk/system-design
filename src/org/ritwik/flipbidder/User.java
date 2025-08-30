package org.ritwik.flipbidder;

/**
 * Represents a user in the auction system (can be both buyer and seller)
 */
public class User {
    private final String name;
    
    public User(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        this.name = name.trim();
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return name.equals(user.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public String toString() {
        return "User{name='" + name + "'}";
    }
}
