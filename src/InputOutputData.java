import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
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
                    throw new InvalidDataException("В файле содержится больше 100000 чисел");
                }
            }
        } catch (FileNotFoundException e) {
            throw new InvalidDataException("Не найден входной файл по пути " + inputFile);
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public static void CreateOutputFile(InformationSort[] result, String outputFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (InformationSort info: result) {
                writer.println(info.name() + ": ");
                writer.println("Время выполнения - " + ((double) info.time() / 1000000000) + " секунд");
                writer.println("Затраченная память - " + (double) info.memory() / 1024 + " Кб");
                writer.println("Количество сравнений - " + info.comparisons());
                writer.println("Количество перемещений - " + info.swaps());
                writer.println();
            }
        }
    }

    public static void createTimeChart(InformationSort[] result, String fileName) throws IOException {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        for (InformationSort info: result) {
            dataSet.addValue((double) info.time() / 1000000000, "Время (сек)", info.name());
        }

        JFreeChart chart = ChartFactory.createBarChart("Сравнение времени выполнения алгоритмов",
                "Алгоритмы", "Время (секунды)", dataSet, PlotOrientation.VERTICAL,
                true, true, false);

        ChartUtils.saveChartAsPNG(new File(fileName), chart, 1600, 1200);
    }

    public static void createMemoryChart(InformationSort[] result, String fileName) throws IOException {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        for (InformationSort info: result) {
            dataSet.addValue((double) info.memory() / 1024, "Память (Кб)", info.name());
        }

        JFreeChart chart = ChartFactory.createBarChart("Сравнение использования памяти алгоритмами",
                "Алгоритмы", "Память (Килобайты)", dataSet, PlotOrientation.VERTICAL,
                true, true, false);

        ChartUtils.saveChartAsPNG(new File(fileName), chart, 1600, 1200);
    }
}
