import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создание первого экземпляра Singleton
        TextProcessorSingleton processor1 = TextProcessorSingleton.getInstance();
        System.out.println("Вы хотите модифицировать согласные? (true/false):");
        boolean choice = scanner.nextBoolean();
        processor1.setModifySogl(choice);

        // Создание второго экземпляра Singleton (без установки нового значения)
        TextProcessorSingleton processor2 = TextProcessorSingleton.getInstance();

        // Проверка, что значение перенеслось
        System.out.println("Состояние modifySogl у второго экземпляра: " + processor2.isModifySogl());

        // Ввод текста пользователем
        scanner.nextLine(); // Очистка буфера
        System.out.println("Введите текст:");
        String inputText = scanner.nextLine();

        // Обработка текста
        String processedText = processor2.processText(inputText);
        System.out.println("Результат обработки: " + processedText);
    }
}