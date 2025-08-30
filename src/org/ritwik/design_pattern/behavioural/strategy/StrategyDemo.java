package org.ritwik.design_pattern.behavioural.strategy;

public class StrategyDemo {
    public static void main(String[] args) {
        int[] array1 = { 64, 34, 25, 12, 22, 11, 90 };
        int[] array2 = { 64, 34, 25, 12, 22, 11, 90 };
        int[] array3 = { 64, 34, 25, 12, 22, 11, 90 };

        SortContext context = new SortContext(new BubbleSort());

        System.out.println("=== Using Bubble Sort ===");
        context.executeSort(array1);

        System.out.println("\n=== Using Quick Sort ===");
        context.setStrategy(new QuickSort());
        context.executeSort(array2);

        System.out.println("\n=== Using Merge Sort ===");
        context.setStrategy(new MergeSort());
        context.executeSort(array3);
    }
}
