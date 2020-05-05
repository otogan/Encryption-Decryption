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
        Arguments arguments = new Arguments(args);
        String alg = arguments.get("-alg", "shift");
        String mode = arguments.get("-mode", "enc").toLowerCase();
        int key = Integer.parseInt(arguments.get("-key", "0"));
        String data = arguments.get("-data", "");
        String pathIn = arguments.get("-in");
        String pathOut = arguments.get("-out");

        if (data.equals("") && pathIn != null) {
            data = importData(pathIn);
        }
        char[] chars = data.toCharArray();

        Encryption encryption = Encryption.getEncryption(alg, key);
        if (Objects.equals(mode, "enc")) {
            chars = encryption.encode(chars);
        } else {
            chars = encryption.decode(chars);
        }
        data = String.valueOf(chars);
        if (pathOut == null) {
            System.out.println(data);
        } else {
            exportData(pathOut, data);
        }
    }

    public static String importData(String pathToFile) {
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        return "";
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

class Arguments {
    private final String[] args;

    public Arguments(String[] args) {
        this.args = args.clone();
    }

    public String get(String arg) {
        return get(arg, null);
    }

    public String get(String arg, String defaultValue) {
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
}

abstract class Encryption {
    int key;

    public Encryption(int key) {
        this.key = key;
    }

    public static Encryption getEncryption(String alg, int key) {
        switch (alg) {
            case "shift":
                return new Shift(key);
            case "unicode":
                return new Unicode(key);
            default:
                return null;
        }
    }

    abstract public char[] encode(char[] data);

    abstract public char[] decode(char[] data);
}

class Shift extends Encryption {

    HashMap<Character, Character> encoder = new HashMap<>();
    HashMap<Character, Character> decoder = new HashMap<>();

    public Shift(int key) {
        super(key);
        fillCoders();
    }

    private void fillCoders() {
        for (byte i = 0; i < 26; i++) {
            char kLower = (char) ('a' + i);
            char vLower = (char) ('a' + (i + key) % 26);
            encoder.put(kLower, vLower);
            decoder.put(vLower, kLower);
            char kUpper = (char) ('A' + i);
            char vUpper = (char) ('A' + (i + key) % 26);
            encoder.put(kUpper, vUpper);
            decoder.put(vUpper, kUpper);
        }
    }

    @Override
    public char[] encode(char[] data) {
        char[] encoded = data.clone();
        for (int i = 0; i < encoded.length; i++) {
            encoded[i] = encoder.getOrDefault(encoded[i], encoded[i]);
        }
        return encoded;
    }

    @Override
    public char[] decode(char[] data) {
        char[] decoded = data.clone();
        for (int i = 0; i < decoded.length; i++) {
            decoded[i] = decoder.getOrDefault(decoded[i], decoded[i]);
        }
        return decoded;
    }
}

class Unicode extends Encryption {

    public Unicode(int key) {
        super(key);
    }

    @Override
    public char[] encode(char[] data) {
        char[] encoded = data.clone();
        for (int i = 0; i < encoded.length; i++) {
            encoded[i] = (char) (encoded[i] + key);
        }
        return encoded;
    }

    @Override
    public char[] decode(char[] data) {
        char[] decoded = data.clone();
        for (int i = 0; i < decoded.length; i++) {
            decoded[i] = (char) (decoded[i] - key);
        }
        return decoded;
    }
}