package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

public class PancakeSort implements SortAlgorithm {
    private long comparisons = 0;
    private long swaps = 0;

    @Override
    public InformationSort sort(int[] array) {
        long startTime;
        long endTime;
        long memory;
        startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        for (int size = array.length; size > 1; size--) {
            int maxIndex = findMax(array, size);
            if (maxIndex != size - 1) {
                flip(array, maxIndex);
                flip(array, size - 1);
            }
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort("Блинная сортировка (PancakeSort)",
                comparisons, swaps, endTime - startTime, memory);
    }

    private int findMax(int[] array, int size) {
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            comparisons++;
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void flip(int[] array, int j) {
        int i = 0;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
    }
}