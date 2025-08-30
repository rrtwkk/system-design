package org.ritwik.design_pattern.behavioural.strategy;

import java.util.Arrays;

// Context class
public class SortContext {
    private SortStrategy strategy;

    public SortContext(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSort(int[] array) {
        System.out.println("Original array: " + Arrays.toString(array));
        strategy.sort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
