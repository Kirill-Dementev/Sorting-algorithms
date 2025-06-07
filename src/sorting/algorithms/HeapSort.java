package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

public class HeapSort implements SortAlgorithm {
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

        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort(comparisons, swaps, endTime - startTime, memory);
    }

    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1, right = 2 * i + 2;

        if (left < n) {
            comparisons++;
            if (array[left] > array[largest]) {
                largest = left;
            }
        }
        if (right < n) {
            comparisons++;
            if (array[right] > array[largest]) {
                largest = right;
            }
        }
        if (largest != i) {
            swap(array, largest, i);
            heapify(array, n, largest);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
    }
}
