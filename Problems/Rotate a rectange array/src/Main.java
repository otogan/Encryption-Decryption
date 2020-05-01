import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] rotate = new int[m][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                rotate[j][i] = scanner.nextInt();
            }
        }
        for (int[] i : rotate) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}