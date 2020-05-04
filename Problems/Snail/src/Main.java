import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int h = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int distance = 0;
        int days = 0;
        distance += b;
        do {
            distance -= b;
            days++;
            distance += a;
        } while (distance < h);
        System.out.println(days);
    }
}