import sorting.InformationSort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputData {
    public static int[] readNumFile(String inputFile) throws InvalidDataException {
        File file = new File(inputFile);
        List<Integer> numbers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if (!scanner.hasNextInt()) {
                    throw new InvalidDataException("Неверный тип данных в файле");
                }
                numbers.add(scanner.nextInt());
                if (numbers.size() > 100000) {
                    throw new InvalidDataException("В файле содержиться больше 10000 чисел");
                }
            }
        } catch (FileNotFoundException e) {
            throw new InvalidDataException("Не найден входной файл по пути " + inputFile);
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public static void CreateOutputFile(InformationSort[] result, String outputFile, String[] nums) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            String[] algorithms = {"Пузырьковая сортировка (BubbleSort)",
                    "Быстрая сортировка (QuickSort)",
                    "Сортировка слиянием (MergeSort)",
                    "Сортировка вставками (InsertionSort)",
                    "Сортировка выбором (SelectionSort)"
            };
            for (int i = 0; i < result.length; i++) {
                writer.println(algorithms[Integer.parseInt(nums[i]) - 1] + ": ");
                writer.println("Время выполнения - " + ((double) result[i].getTime() / 1000000000) + " секунд");
                writer.println("Затраченная память - " + (double) result[i].getMemory() / 1024 + " Кб");
                writer.println("Количество сравнений - " + result[i].getComparisons());
                writer.println("Количество перемещений - " + result[i].getSwaps());
                writer.println();
            }
        }
    }
}
