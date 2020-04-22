package encryptdecrypt;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] message = scanner.nextLine().toLowerCase().toCharArray();
        int key = scanner.nextInt();
        HashMap<Character, Character> encrypt = new HashMap<>();
        for (byte i = 0; i < 26; i++) {
            char next = (char)('a' + (i + key) % 26);
            encrypt.put((char)('a' + i), next);
        }
        for (int i = 0; i < message.length; i++) {
            message[i] = encrypt.getOrDefault(message[i], message[i]);
        }
        System.out.println(String.valueOf(message));
    }
}
