package sorting.algorithms;

import sorting.InformationSort;
import sorting.SortAlgorithm;

import java.util.Arrays;

public class CountingSort implements SortAlgorithm {
    @Override
    public InformationSort sort(int[] array) {
        long startTime;
        long endTime;
        long memory;
        startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        if (array.length != 0) {
            int max = Arrays.stream(array).max().getAsInt();
            int min = Arrays.stream(array).min().getAsInt();
            int range = max - min + 1;
            int[] count = new int[range];
            for (int number: array) {
                count[number - min]++;
            }

            int index = 0;
            for (int i = 0; i < range; i++) {
                while (count[i] > 0) {
                    array[index++] = i + min;
                    count[i]--;
                }
            }
        }

        endTime = System.nanoTime();
        memory = (runtime.totalMemory() - runtime.freeMemory()) - initialMemory;
        return new InformationSort("CountingSort",
                2L * array.length, array.length, endTime - startTime, memory);
    }
}
