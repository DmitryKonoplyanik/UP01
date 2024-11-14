import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("¬ведите размерность матрицы: ");
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        Random random = new Random();
        int min = -n;
        int max = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt((max - min) + 1) + min;
            }
        }
        System.out.println("»сходна€ матрица:");
        printMatrix(a);
        int[] rows = new int[n];
        int[] columns = new int[n];
        int rowCount = 0;
        int columnCount = 0;
        for (int i = 0; i < n; i++) {
            boolean rowHasZero = false;
            boolean columnHasZero = false;
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    rowHasZero = true;
                }
                if (a[j][i] == 0) {
                    columnHasZero = true;
                }
            }
            if (!rowHasZero) {
                rows[rowCount] = i;
                rowCount++;
            }
            if (!columnHasZero) {
                columns[columnCount] = i;
                columnCount++;
            }
        }
        int[][] b = new int[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                b[i][j] = a[rows[i]][columns[j]];
            }
        }
        System.out.println("”плотненна€ матрица:");
        printMatrix(b);
    }

    public static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
