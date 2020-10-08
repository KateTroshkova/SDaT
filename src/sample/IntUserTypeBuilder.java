package sample;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class IntUserTypeBuilder implements UserTypeBuilder {

    private InputStreamReader currentIO;
    private Scanner scanner;
    private Random rand;

    @Override
    public String getName() {
        return "Int";
    }

    @Override
    public Object create() {
        if (rand == null) rand = new Random();
        return rand.nextInt(32000) - 16000;
    }

    @Override
    public Object readValue(InputStreamReader io) {
        if (currentIO != io) {
            currentIO = io;
            scanner = new Scanner(currentIO);
        }
        try {
            if (scanner.hasNext()) {
                return scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            return null;
        }
        return null;
    }

    @Override
    public Object parseValue(String value) {
        int res = 0;
        try {
            res = Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
        }
        return res;
    }

    @Override
    public int compare(Object o1, Object o2) {
        boolean correctO1 = o1 instanceof Integer;
        boolean correctO2 = o2 instanceof Integer;
        if (correctO1 && correctO2) {
            return ((Integer) o1).compareTo((Integer) o2);
        }
        if (correctO1) return 1;
        if (correctO2) return -1;
        return 0;
    }

    @Override
    public String toString(Object o) {
        if (o instanceof Integer) return o + "";
        return null;
    }

    @Override
    public Object clone(Object o) {
        if (o instanceof Integer) return o;
        return null;
    }
}
