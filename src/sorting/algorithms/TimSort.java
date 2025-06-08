package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

import java.util.Arrays;

public class TimSort implements SortAlgorithm {
    private static final int RUN = 32;
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

        for (int i = 0; i < array.length; i += RUN) {
            insertionSort(array, i, Math.min(i + RUN, array.length));
        }

        for (int size = RUN; size < array.length; size *= 2) {
            for (int left = 0; left < array.length; left += 2 * size) {
                int mid = Math.min(left + size - 1, array.length - 1);
                int right = Math.min(left + 2 * size - 1, array.length - 1);
                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort("Гибридная сортировка (TimSort)",
                comparisons, swaps, endTime - startTime, memory);
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= left && array[j] > temp) {
                comparisons++;
                swaps++;
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int len1 = mid - left + 1, len2 = right - mid;
        int[] leftArray = Arrays.copyOfRange(array, left, left + len1);
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, mid + len2 + 1);

        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            comparisons++;
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
            swaps++;
        }
        while (i < len1) {
            array[k++] = leftArray[i++];
            swaps++;
        }
        while (j < len2) {
            array[k++] = rightArray[j++];
            swaps++;
        }
    }
}
