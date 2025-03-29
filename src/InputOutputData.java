import java.io.File;
import java.io.FileNotFoundException;
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
                if (numbers.size() > 10000) {
                    throw new InvalidDataException("В файле содержиться больше 10000 чисел");
                }
            }
        } catch (FileNotFoundException e) {
            throw new InvalidDataException("Не найден входной файл по пути " + inputFile);
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }
}
