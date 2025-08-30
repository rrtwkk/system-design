package org.ritwik.set;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple HashSet implementation for coding interviews
 * 
 * Key Components:
 * 1. Hash Function: Maps elements to bucket indices
 * 2. Collision Handling: Separate chaining with LinkedList
 * 3. Dynamic Resizing: Maintains load factor < 0.75
 * 4. Core Operations: O(1) average time complexity
 */
public class SimpleHashSet<T> {

    // Default initial capacity
    private static final int INITIAL_CAPACITY = 16;

    // Load factor threshold for resizing
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    // Array of buckets for separate chaining
    private LinkedList<T>[] buckets;

    // Current number of elements
    private int size;

    // Current capacity
    private int capacity;

    /**
     * Constructor with default capacity
     */
    @SuppressWarnings("unchecked")
    public SimpleHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.buckets = new LinkedList[capacity];
        this.size = 0;

        // Initialize each bucket
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Hash function - maps element to bucket index
     * Uses modulo operation for simplicity
     */
    private int hash(T element) {
        if (element == null) {
            return 0;
        }
        return Math.abs(element.hashCode()) % capacity;
    }

    /**
     * Add element to set
     * Returns true if element was added, false if already exists
     * Time Complexity: O(1) average, O(n) worst case
     */
    public boolean add(T element) {
        int bucketIndex = hash(element);
        LinkedList<T> bucket = buckets[bucketIndex];

        // Check if element already exists
        if (bucket.contains(element)) {
            return false;
        }

        // Add element to bucket
        bucket.add(element);
        size++;

        // Check if resize is needed
        if (getCurrentLoadFactor() > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        return true;
    }

    /**
     * Remove element from set
     * Returns true if element was removed, false if not found
     * Time Complexity: O(1) average, O(n) worst case
     */
    public boolean remove(T element) {
        int bucketIndex = hash(element);
        LinkedList<T> bucket = buckets[bucketIndex];

        boolean removed = bucket.remove(element);
        if (removed) {
            size--;
        }

        return removed;
    }

    /**
     * Check if element exists in set
     * Time Complexity: O(1) average, O(n) worst case
     */
    public boolean contains(T element) {
        int bucketIndex = hash(element);
        return buckets[bucketIndex].contains(element);
    }

    /**
     * Get current size of set
     */
    public int size() {
        return size;
    }

    /**
     * Check if set is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get current load factor
     */
    private double getCurrentLoadFactor() {
        return (double) size / capacity;
    }

    /**
     * Resize the hash table when load factor exceeds threshold
     * Doubles the capacity and rehashes all elements
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // Store old buckets
        LinkedList<T>[] oldBuckets = buckets;
        int oldCapacity = capacity;

        // Create new larger bucket array
        capacity *= 2;
        buckets = new LinkedList[capacity];
        size = 0; // Reset size as we'll re-add all elements

        // Initialize new buckets
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }

        // Rehash all elements from old buckets
        for (int i = 0; i < oldCapacity; i++) {
            for (T element : oldBuckets[i]) {
                add(element); // This will use new hash function with new capacity
            }
        }
    }

    /**
     * Get all elements in the set
     */
    public List<T> getAllElements() {
        List<T> elements = new ArrayList<>();
        for (LinkedList<T> bucket : buckets) {
            elements.addAll(bucket);
        }
        return elements;
    }

    /**
     * Get statistics about the hash table for debugging
     */
    public String getStats() {
        int nonEmptyBuckets = 0;
        int maxBucketSize = 0;

        for (LinkedList<T> bucket : buckets) {
            if (!bucket.isEmpty()) {
                nonEmptyBuckets++;
                maxBucketSize = Math.max(maxBucketSize, bucket.size());
            }
        }

        return String.format(
                "Size: %d, Capacity: %d, Load Factor: %.2f, Non-empty buckets: %d, Max bucket size: %d",
                size, capacity, getCurrentLoadFactor(), nonEmptyBuckets, maxBucketSize);
    }

    @Override
    public String toString() {
        return getAllElements().toString();
    }
}
