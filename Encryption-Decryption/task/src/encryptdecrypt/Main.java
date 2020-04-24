package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String mode = checkArgs(args, "-mode", "enc").toLowerCase();
        int key = Integer.parseInt(checkArgs(args, "-key", "0"));
        String data = checkArgs(args, "-data", "");
        String pathIn = checkArgs(args, "-in");
        String pathOut = checkArgs(args, "-out");

        if (data.equals("") && pathIn != null) {
            data = importData(pathIn);
        }
        char[] chars = data.toCharArray();
//        Scanner scanner = new Scanner(System.in);

        if (Objects.equals(mode, "enc")) {
            for (int i = 0; i < chars.length; i++) {
                chars[i] = (char) (chars[i] + key);
            }
        } else {
            for (int i = 0; i < chars.length; i++) {
                chars[i] = (char) (chars[i] - key);
            }
        }
        data = String.valueOf(chars);
        if (pathOut == null) {
            System.out.println(data);
        } else {
            exportData(pathOut, data);
        }
    }

    private static String checkArgs(String[] args, String arg) {
        return checkArgs(args, arg, null);
    }

    private static String checkArgs(String[] args, String arg, String defaultValue) {
        var iterator = Arrays.stream(args).iterator();
        String value = defaultValue;
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (Objects.equals(arg, next) && iterator.hasNext()) {
                value = iterator.next();
                break;
            }
        }
        return value;
    }

    public static String importData(String pathToFile) {
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        return null;
    }

    public static boolean exportData(String pathToFile, String data) {
        try (PrintWriter printWriter = new PrintWriter(new File(pathToFile))) {
            printWriter.println(data);
            printWriter.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

