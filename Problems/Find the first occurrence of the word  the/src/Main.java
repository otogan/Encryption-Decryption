import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        char[] sentence = scanner.nextLine().toLowerCase().toCharArray();
        int index = -1;
        char next = 't';
        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i] == next) {
                switch (next) {
                    case 't':
                        index = i;
                        next = 'h';
                        break;
                    case 'h':
                        next = 'e';
                        break;
                    case 'e':
                        System.out.println(index);
                        return;
                }
            } else {
                index = -1;
                next = 't';
            }
        }
        System.out.println(-1);
    }
}