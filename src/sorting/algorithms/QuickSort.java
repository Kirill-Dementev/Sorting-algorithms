package sorting.algorithms;

import sorting.SortAlgorithm;

public class QuickSort implements SortAlgorithm {
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
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
            if (left > right) {
                break;
            } else {
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
