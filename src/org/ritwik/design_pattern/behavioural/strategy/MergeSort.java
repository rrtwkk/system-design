package org.ritwik.design_pattern.behavioural.strategy;

// Concrete Strategy
public class MergeSort implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Performing Merge Sort...");
        mergeSort(array, 0, array.length - 1);
        System.out.println("Merge Sort completed");
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int[] leftArray = new int[middle - left + 1];
        int[] rightArray = new int[right - middle];

        System.arraycopy(array, left, leftArray, 0, leftArray.length);
        System.arraycopy(array, middle + 1, rightArray, 0, rightArray.length);

        int i = 0, j = 0, k = left;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArray.length) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
