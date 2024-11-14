import java.util.Scanner;

public class TelegramBill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите стоимость одного слова: ");
        double wordCost = scanner.nextDouble();
        System.out.print("Введите текст телеграммы: ");
        scanner.nextLine(); // consume newline left-over
        String message = scanner.nextLine();
        int wordCount = countWords(message);
        double totalCost = wordCount * wordCost;
        System.out.printf("Квитанция об оплате телеграммы:%n%n");
        System.out.printf("Текст телеграммы:%n%s%n", message);
        System.out.printf("Стоимость одного слова: %.2f%n", wordCost);
        System.out.printf("Количество слов: %d%n", wordCount);
        System.out.printf("Итого к оплате: %.2f%n", totalCost);
    }

    private static int countWords(String message) {
        String[] words = message.split("\\s+");
        return words.length;
    }
}
