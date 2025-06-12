package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

public class InsertionSort implements SortAlgorithm {
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

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (array[j] > current) {
                    swaps++;
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = current;
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort("InsertionSort", comparisons, swaps, endTime - startTime, memory);
    }
}
