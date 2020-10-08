package sample;

import java.util.ArrayList;
import java.util.List;

public class TypeFactory {

    public static List<String> getListNames() {
        ArrayList<String> builders = new ArrayList<>();
        builders.add("String");
        builders.add("Int");
        builders.add("Dolphin");
        return builders;
    }

    public static UserTypeBuilder getByName(String name) {
        switch (name) {
            case "String":
                return new StringUserTypeBuilder();
            case "Int":
                return new IntUserTypeBuilder();
            case "Dolphin":
                return new DolphinUserTypeBuilder();
            default:
                return null;
        }
    }
}
