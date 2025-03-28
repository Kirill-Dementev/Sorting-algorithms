package sorting.algorithms;

import sorting.SortAlgorithm;

public class InsertionSort implements SortAlgorithm {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0) {
                if (array[j] > current) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = current;
        }
    }
}
