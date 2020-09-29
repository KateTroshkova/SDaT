package sample;

import java.io.InputStreamReader;

public interface UserTypeBuilder {
    String getName();

    Object create();

    Object readValue(InputStreamReader io);

    Object parseValue(String value);

    int compare(Object o1, Object o2);

    String toString(Object o);

    Object clone(Object o);
}
