package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

public class QuickSort implements SortAlgorithm {
    private int comparisons = 0;
    private int swaps = 0;

    @Override
    public InformationSort sort(int[] array) {
        long startTime;
        long endTime;
        long memory;
        startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        quickSort(array, 0, array.length - 1);

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort(comparisons, swaps, endTime - startTime, memory);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int left = low + 1;
        int right = high;
        while (true) {
            while (left <= right && array[left] <= pivot) {
                left++;
            }
            while (left <= right && array[right] >= pivot) {
                right--;
            }
            comparisons++;
            if (left > right) {
                break;
            } else {
                swaps++;
                int a = array[left];
                array[left] = array[right];
                array[right] = a;
            }
        }
        array[low] = array[right];
        array[right] = pivot;
        return right;
    }
}
