import sorting.InformationSort;
import sorting.SortAlgorithm;
import sorting.algorithms.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к входному файлу:");
        String inputFile = scanner.nextLine();
        int[] array = InputOutputData.readNumFile(inputFile);
        System.out.println("Выберите алгоритмы сортировки (цифры через пробел):");
        System.out.println("1 - Пузырьковая сортировка (BubbleSort)");
        System.out.println("2 - Быстрая сортировка (QuickSort)");
        System.out.println("3 - Сортировка слиянием (MergeSort)");
        System.out.println("4 - Сортировка вставками (InsertionSort)");
        System.out.println("5 - Сортировка выбором (SelectionSort)");

        String[] nums = scanner.nextLine().split(" ");
        List<SortAlgorithm> algorithms = new ArrayList<>();
        for (String num: nums) {
            switch (num.trim()) {
                case "1":
                    algorithms.add(new BubbleSort());
                    break;
                case "2":
                    algorithms.add(new QuickSort());
                    break;
                case "3":
                    algorithms.add(new MergeSort());
                    break;
                case "4":
                    algorithms.add(new InsertionSort());
                    break;
                case "5":
                    algorithms.add(new SelectionSort());
                    break;
                default:
                    throw new InvalidDataException("Неверный выбор алгоритма");
            }
        }

        InformationSort[] result = new InformationSort[algorithms.size()];
        int k = 0;
        for (SortAlgorithm algorithm: algorithms) {
            int[] copyArray = Arrays.copyOf(array, array.length);
            result[k++] = algorithm.sort(copyArray);
        }

        System.out.println("Введите имя выходного файла:");
        String outputFile = scanner.nextLine();
        try {
            InputOutputData.CreateOutputFile(result, outputFile, nums);
            System.out.println("Отчёт записан в файл " + outputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка записи в файл");
        }
    }
}