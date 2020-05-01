import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = 1;
        int sq = 1;
        do {
            System.out.println(sq);
            i++;
            sq = i * i;
        } while (sq <= n);
    }
}