package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

public class GnomeSort implements SortAlgorithm {
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

        int index = 0;
        while (index < array.length) {
            comparisons++;
            if (index == 0 || array[index] >= array[index - 1]) {
                index++;
            } else {
                swap(array, index, index - 1);
                index--;
            }
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort("GnomeSort", comparisons, swaps, endTime - startTime, memory);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
    }
}
