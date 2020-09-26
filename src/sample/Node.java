package sample;

public class Node implements Comparable<Node> {

    private int value;

    public Node(int n) {
        value = n;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "!";
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(value, o.value);
    }
}
