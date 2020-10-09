package sample;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DolphinUserTypeBuilder implements UserTypeBuilder {

    private InputStreamReader currentIO;
    private Scanner scanner;
    private Random rand;

    @Override
    public String getName() {
        return "Dolphin";
    }

    @Override
    public Object create() {
        if (rand == null) rand = new Random();
        return new Dolphin("Unknown", rand.nextInt(1000), rand.nextInt(100), rand.nextInt(240));
    }

    @Override
    public Object readValue(InputStreamReader io) {
        if (currentIO != io) {
            currentIO = io;
            scanner = new Scanner(currentIO);
        }
        try {
            if (scanner.hasNext()) {
                String name = scanner.next();
                int weight = scanner.nextInt();
                int age = scanner.nextInt();
                int smart = scanner.nextInt();
                return new Dolphin(name, weight, age, smart);
            }
        } catch (InputMismatchException e) {
            return null;
        }
        return null;
    }

    @Override
    public Object parseValue(String value) {
        try {
            String[] content = value.split(" ");
            String name = content[0];
            int weight = Integer.parseInt(content[1]);
            int age = Integer.parseInt(content[2]);
            int smart = Integer.parseInt(content[3]);
            return new Dolphin(name, weight, age, smart);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
            return null;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        boolean correctO1 = o1 instanceof Dolphin;
        boolean correctO2 = o2 instanceof Dolphin;
        if (correctO1 && correctO2) {
            int firstMeasure = ((Dolphin) o1).getAge() + ((Dolphin) o1).getWeight() + ((Dolphin) o1).getSmart();
            int secondMeasure = ((Dolphin) o2).getAge() + ((Dolphin) o2).getWeight() + ((Dolphin) o2).getSmart();
            return Integer.compare(firstMeasure, secondMeasure);
        }
        if (correctO1) return 1;
        if (correctO2) return -1;
        return 0;
    }

    @Override
    public String toString(Object o) {
        if (o instanceof Dolphin) return o.toString();
        return null;
    }

    @Override
    public Object clone(Object o) {
        if (o instanceof Dolphin) return ((Dolphin) o).clone();
        return null;
    }
}
