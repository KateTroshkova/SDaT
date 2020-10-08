package sample;

public class Dolphin {

    private String name;
    private int weight;
    private int age;
    private int smart;

    public Dolphin(String name, int weight, int age, int smart) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.smart = smart;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSmart() {
        return smart;
    }

    public void setSmart(int smart) {
        this.smart = smart;
    }

    @Override
    public String toString() {
        return name + " " + weight + " " + age + " " + smart;
    }

    @Override
    public Dolphin clone() {
        return new Dolphin(name, weight, age, smart);
    }
}
