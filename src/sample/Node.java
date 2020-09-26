package sample;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private T value;

    public Node(T n) {
        value = n;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "!";
    }

    @Override
    public int compareTo(Node<T> o) {
        return value.compareTo(o.value);
    }
}
