package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

import java.util.Arrays;

public class BinaryInsertionSort implements SortAlgorithm {
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
            int key = array[i];
            int j = Math.abs(Arrays.binarySearch(array, 0, i, key) + 1);
            comparisons += (long) (Math.log(i) / Math.log(2)) + 1;

            System.arraycopy(array, j, array, j + 1, i - j);
            array[j] = key;
            swaps += i - j;
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort("Бинарная сортировка вставками (BinaryInsertionSort)",
                comparisons, swaps, endTime - startTime, memory);
    }
}
