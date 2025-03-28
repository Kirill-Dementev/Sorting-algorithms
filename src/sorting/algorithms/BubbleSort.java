package sorting.algorithms;

import sorting.SortAlgorithm;

public class BubbleSort implements SortAlgorithm {
    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }
}
