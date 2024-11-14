import java.util.Scanner;

public class TelegramBill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������� ��������� ������ �����: ");
        double wordCost = scanner.nextDouble();
        System.out.print("������� ����� ����������: ");
        scanner.nextLine(); // consume newline left-over
        String message = scanner.nextLine();
        int wordCount = countWords(message);
        double totalCost = wordCount * wordCost;
        System.out.printf("��������� �� ������ ����������:%n%n");
        System.out.printf("����� ����������:%n%s%n", message);
        System.out.printf("��������� ������ �����: %.2f%n", wordCost);
        System.out.printf("���������� ����: %d%n", wordCount);
        System.out.printf("����� � ������: %.2f%n", totalCost);
    }

    private static int countWords(String message) {
        String[] words = message.split("\\s+");
        return words.length;
    }
}
