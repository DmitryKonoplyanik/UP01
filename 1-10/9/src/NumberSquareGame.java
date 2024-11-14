import java.util.*;

public class NumberSquareGame {
    private static final int SIZE = 5;
    private static final int[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private static final int[][] SCORES = {
            {0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6},
            {7, 7, 7, 7, 7},
            {8, 8, 8, 8, 8},
            {9, 9, 9, 9, 9},
            {10, 10, 10, 10, 10},
            {11, 11, 11, 11, 11},
            {12, 12, 12, 12, 12},
            {13, 13, 13, 13, 13}
    };

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];
        List<Integer> numbers = new ArrayList<>();
        for (int value : VALUES) {
            numbers.add(value);
        }
        Collections.shuffle(numbers);

        int playerScore = 0;
        int computerScore = 0;

        for (int i = 0; i < SIZE * SIZE; i++) {
            int number = numbers.get(i);
            System.out.println("Выпало число: " + number);

            // Игрок делает ход
            System.out.print("Ваш ход (введите координаты x и y через пробел): ");
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            board[x][y] = number;
            playerScore += SCORES[number - 1][x] + SCORES[number - 1][y];

            // Компьютер делает ход
            int computerX = -1;
            int computerY = -1;
            int maxScore = Integer.MIN_VALUE;
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (board[j][k] == 0) {
                        int score = SCORES[number - 1][j] + SCORES[number - 1][k];
                        if (score > maxScore) {
                            maxScore = score;
                            computerX = j;
                            computerY = k;
                        }
                    }
                }
            }
            board[computerX][computerY] = number;
            computerScore += maxScore;

            // Выводим текущее состояние доски
            printBoard(board);
            System.out.println("Ваши очки: " + playerScore);
            System.out.println("Очки компьютера: " + computerScore);
        }

        if (playerScore > computerScore) {
            System.out.println("Вы выиграли!");
        } else if (computerScore > playerScore) {
            System.out.println("Компьютер выиграл!");
        } else {
            System.out.println("Ничья!");
        }
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
