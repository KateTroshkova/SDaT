package sample;

import java.util.ArrayList;
import java.util.List;

public class TypeFactory {

    public static List<String> getListNames() {
        ArrayList<String> builders = new ArrayList<>();
        builders.add("String");
        return builders;
    }

    public static UserTypeBuilder getByName(String name) {
        switch (name) {
            case "String":
                return new StringUserTypeBuilder();
            default:
                return null;
        }
    }
}
