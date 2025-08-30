package org.ritwik.setimplementation;

/**
 * Demo class showcasing SimpleHashSet operations
 * Demonstrates key concepts for coding interviews
 */
public class SetDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Simple HashSet Demo ===\n");
        
        SimpleHashSet<String> stringSet = new SimpleHashSet<>();
        
        // Demo 1: Basic Operations
        System.out.println("1. Basic Operations:");
        System.out.println("Initial state: " + stringSet.getStats());
        
        // Adding elements
        System.out.println("\nAdding elements:");
        String[] elements = {"apple", "banana", "cherry", "date", "elderberry"};
        for (String element : elements) {
            boolean added = stringSet.add(element);
            System.out.println("Added '" + element + "': " + added);
        }
        
        System.out.println("After additions: " + stringSet.getStats());
        System.out.println("Set contents: " + stringSet);
        
        // Trying to add duplicates
        System.out.println("\nTrying to add duplicates:");
        System.out.println("Added 'apple' again: " + stringSet.add("apple"));
        System.out.println("Added 'banana' again: " + stringSet.add("banana"));
        
        // Demo 2: Contains operation
        System.out.println("\n2. Contains Operations:");
        String[] testElements = {"apple", "grape", "banana", "kiwi"};
        for (String element : testElements) {
            System.out.println("Contains '" + element + "': " + stringSet.contains(element));
        }
        
        // Demo 3: Remove operation
        System.out.println("\n3. Remove Operations:");
        String[] toRemove = {"banana", "kiwi", "cherry"};
        for (String element : toRemove) {
            boolean removed = stringSet.remove(element);
            System.out.println("Removed '" + element + "': " + removed);
        }
        
        System.out.println("After removals: " + stringSet.getStats());
        System.out.println("Set contents: " + stringSet);
        
        // Demo 4: Demonstrating resize behavior
        System.out.println("\n4. Resize Behavior:");
        SimpleHashSet<Integer> intSet = new SimpleHashSet<>();
        System.out.println("Initial: " + intSet.getStats());
        
        // Add many elements to trigger resize
        System.out.println("\nAdding 20 integers to trigger resize:");
        for (int i = 1; i <= 20; i++) {
            intSet.add(i);
            if (i % 5 == 0) {
                System.out.println("After adding " + i + " elements: " + intSet.getStats());
            }
        }
        
        // Demo 5: Hash collision demonstration
        System.out.println("\n5. Hash Collision Handling:");
        SimpleHashSet<String> collisionDemo = new SimpleHashSet<>();
        
        // These strings might hash to same bucket in small capacity
        String[] potentialCollisions = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (String s : potentialCollisions) {
            collisionDemo.add(s);
        }
        
        System.out.println("Collision demo stats: " + collisionDemo.getStats());
        System.out.println("All elements preserved: " + collisionDemo);
        
        // Demo 6: Performance characteristics
        System.out.println("\n6. Performance Test:");
        SimpleHashSet<Integer> perfSet = new SimpleHashSet<>();
        
        long startTime = System.nanoTime();
        
        // Add 1000 elements
        for (int i = 0; i < 1000; i++) {
            perfSet.add(i);
        }
        
        long addTime = System.nanoTime() - startTime;
        System.out.println("Time to add 1000 elements: " + addTime / 1000000.0 + " ms");
        
        startTime = System.nanoTime();
        
        // Check 1000 elements
        for (int i = 0; i < 1000; i++) {
            perfSet.contains(i);
        }
        
        long containsTime = System.nanoTime() - startTime;
        System.out.println("Time to check 1000 elements: " + containsTime / 1000000.0 + " ms");
        
        System.out.println("Final performance set stats: " + perfSet.getStats());
        
        System.out.println("\n=== Demo Complete ===");
    }
}
