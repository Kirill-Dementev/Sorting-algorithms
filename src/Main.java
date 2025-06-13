import sorting.InformationSort;
import sorting.SortAlgorithm;
import sorting.algorithms.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);
        out.println("Введите путь к входному файлу:");
        String inputFile = scanner.nextLine();
        int[] array = InputOutputData.readNumFile(inputFile);

        out.println("Выберите алгоритмы сортировки (числа через пробел):");
        out.println("1 - Пузырьковая сортировка (BubbleSort)");
        out.println("2 - Быстрая сортировка (QuickSort)");
        out.println("3 - Сортировка слиянием (MergeSort)");
        out.println("4 - Сортировка вставками (InsertionSort)");
        out.println("5 - Сортировка выбором (SelectionSort)");
        out.println("6 - Бинарная сортировка вставками (BinaryInsertionSort)");
        out.println("7 - Сортировка подсчётом (CountingSort)");
        out.println("8 - Гномья сортировка (GnomeSort)");
        out.println("9 - Пирамидальная сортировка (HeapSort)");
        out.println("10 - Блинная сортировка (PancakeSort)");
        out.println("11 - Гибридная сортировка (TimSort)");
        out.println("12 - Сортировка деревом (TreeSort)");

        String[] numbers = scanner.nextLine().split(" ");
        List<SortAlgorithm> algorithms = new ArrayList<>();
        for (String num: numbers) {
            switch (num.trim()) {
                case "1" -> algorithms.add(new BubbleSort());
                case "2" -> algorithms.add(new QuickSort());
                case "3" -> algorithms.add(new MergeSort());
                case "4" -> algorithms.add(new InsertionSort());
                case "5" -> algorithms.add(new SelectionSort());
                case "6" -> algorithms.add(new BinaryInsertionSort());
                case "7" -> algorithms.add(new CountingSort());
                case "8" -> algorithms.add(new GnomeSort());
                case "9" -> algorithms.add(new HeapSort());
                case "10" -> algorithms.add(new PancakeSort());
                case "11" -> algorithms.add(new TimSort());
                case "12" -> algorithms.add(new TreeSort());
                default -> throw new InvalidDataException("Неверный выбор алгоритма");
            }
        }

        InformationSort[] result = new InformationSort[algorithms.size()];
        int k = 0;
        for (SortAlgorithm algorithm: algorithms) {
            int[] copyArray = Arrays.copyOf(array, array.length);
            result[k++] = algorithm.sort(copyArray);
        }

        out.println("Введите имя выходного файла:");
        String outputFile = scanner.nextLine();
        try {
            InputOutputData.CreateOutputFile(result, outputFile);
            System.out.println("Отчёт записан в файл " + outputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка записи в файл");
        }

        try {
            InputOutputData.createTimeChart(result, outputFile + "_time.png");
            InputOutputData.createMemoryChart(result, outputFile + "_memory.png");
            out.println("Диаграммы сохраны в файлы: " + outputFile + "_time.png" + ", " + outputFile + "_memory.png");
        } catch (IOException e) {
            System.err.println("Ошибка создания диаграммы");
        }
    }
}
