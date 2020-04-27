import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int count = 0;
        while (scanner.hasNextInt()) {
            if (scanner.nextInt() > 0) {
                count++;
            }
        }
        System.out.println(count == 1);
    }
}