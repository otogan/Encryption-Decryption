import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int n = scanner.nextInt();
        boolean isWeekend = scanner.nextBoolean();
        int minCups = isWeekend ? 15 : 10;
        System.out.println(n >= minCups && n <= (minCups + 10));
    }
}