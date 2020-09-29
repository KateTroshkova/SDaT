package sample;

import java.util.ArrayList;

public class TypeFactory {

    public static ArrayList<String> getArrayListNames() {
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
