package sample;

import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Scanner;

public class StringUserTypeBuilder implements UserTypeBuilder {

    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private InputStreamReader currentIO;
    private Scanner scanner;

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        int maxLength = CHARS.length();
        for (int i = 0; i < length; i++)
            sb.append(CHARS.charAt(rnd.nextInt(maxLength)));
        return sb.toString();
    }

    @Override
    public String getName() {
        return "String";
    }

    @Override
    public Object create() {
        return randomString(4);
    }

    @Override
    public Object readValue(InputStreamReader io) {
        if (currentIO != io) {
            currentIO = io;
            scanner = new Scanner(currentIO);
        }
        if (scanner.hasNext()) {
            return scanner.useDelimiter(" ").next();
        }
        return null;
    }

    @Override
    public Object parseValue(String value) {
        return value;
    }

    @Override
    public int compare(Object o1, Object o2) {
        boolean correctO1 = o1 instanceof String;
        boolean correctO2 = o2 instanceof String;
        if (correctO1 && correctO2) {
            return ((String) o1).compareTo((String) o2);
        }
        if (correctO1) return 1;
        if (correctO2) return -1;
        return 0;
    }

    @Override
    public String toString(Object o) {
        if (o instanceof String) return (String) o;
        return null;
    }

    @Override
    public Object clone(Object o) {
        if (o instanceof String) return o;
        return null;
    }
}
