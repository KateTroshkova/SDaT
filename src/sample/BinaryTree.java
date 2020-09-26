package sample;

/**
 * Array cast is one of the suggested ways of implementing a generic collection in Effective Java; Item 26.
 * No type errors, no need to cast the array repeatedly.
 * However this triggers a warning because it is potentially dangerous, and should be used with caution:
 * It can cause unexpected errors or ClassCastExceptions if used unsafely.
 * This behavior is safe as long as the cast array is used internally (e.g. to back a data structure),
 * and not returned or exposed to client code.
 */
@SuppressWarnings("unchecked")
public class BinaryTree<T extends Comparable<T>> {

    private T[] nodes;
    private int capacity;

    public BinaryTree() {
        //Ну мы же ебанутые, у нас же нумерация поддеревьев с 1
        //У нас же и нумерация массива с 1...
        initEmpty();
    }

    public int size(int n) {
        if (n >= capacity || nodes[n] == null) return 0;
        return 1 + size(2 * n) + size(2 * n + 1);
    }

    public T get(int n, int subTreeNum) {
        if (n >= capacity || n >= size(subTreeNum)) return null;
        int leftNum = size(2 * n);
        if (n < leftNum) return get(n, 2 * subTreeNum);
        n -= leftNum;
        if (n - 1 == 0) return nodes[subTreeNum];
        return get(n - 1, 2 * subTreeNum + 1);
    }

    public void insert(T node) {
        insert(1, node);
    }

    public void delete(int n) {
        if (n >= capacity || nodes[n] == null) return;
        if (nodes[2 * n + 1] == null && nodes[2 * n] == null) nodes[n] = null; //нет потомков
        if (nodes[2 * n] != null && nodes[2 * n + 1] == null) { //только левый потомок
            nodes[n] = nodes[2 * n];
            nodes[2 * n] = null;
            return;
        }
        if (nodes[2 * n] == null && nodes[2 * n + 1] != null) { //только правый потомок
            nodes[n] = nodes[2 * n + 1];
            nodes[2 * n + 1] = null;
            return;
        }
        int max = 2 * n + 1;
        while (2 * max < capacity && nodes[2 * max] != null) {
            max *= 2;
        }
        if (2 * max + 1 < capacity && nodes[2 * max + 1] != null) {
            max = 2 * max + 1;
        }
        nodes[n] = nodes[max];
        nodes[max] = null;
    }

    public void balance() {
        int size = size(1);
        int nodeCount = 0;
        T[] subTree = (T[]) new Comparable[size];
        set(subTree, 1, nodeCount);
        initEmpty();
        balance(subTree, 0, size - 1);
    }

    public void forEach(OnNextListener<T> onNextListener) {
        forEach(1, onNextListener);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (T node : nodes) {
            res.append(node).append(" ");
        }
        return res.toString(); //toStringRecursive(1, 0, "");
    }

    private void initEmpty() {
        capacity = 2;
        nodes = (T[]) new Comparable[capacity];
    }

    private String toStringRecursive(int subTreeNum, int level, String res) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return res;
        res = toStringRecursive(2 * subTreeNum, level + 1, res);
        res += "\n" + "level = " + level + " value = " + nodes[subTreeNum];
        res = toStringRecursive(2 * subTreeNum + 1, level + 1, res);
        return res;
    }

    private int set(T[] subTree, int subTreeNum, int nodeCount) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return nodeCount;
        nodeCount = set(subTree, 2 * subTreeNum, nodeCount);
        subTree[nodeCount++] = nodes[subTreeNum];
        nodeCount = set(subTree, 2 * subTreeNum + 1, nodeCount);
        return nodeCount;
    }

    private void insert(int n, T node) {
        if (n >= capacity) {
            T[] values = (T[]) new Comparable[capacity];
            System.arraycopy(nodes, 0, values, 0, nodes.length);
            capacity = 2 * capacity + 2;
            nodes = (T[]) new Comparable[capacity];
            System.arraycopy(values, 0, nodes, 0, values.length);
        }
        if (nodes[n] == null) {
            nodes[n] = node;
            return;
        }
        if (node.compareTo(nodes[n]) < 0) {
            insert(2 * n, node);
        } else {
            insert(2 * n + 1, node);
        }
    }

    private void balance(T[] subTree, int a, int b) {
        if (a > b) return;
        int m = (a + b) / 2;
        insert(1, subTree[m]);
        balance(subTree, a, m - 1);
        balance(subTree, m + 1, b);
    }

    private void forEach(int subTreeNum, OnNextListener<T> listener) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return;
        forEach(2 * subTreeNum, listener);
        listener.toDo(nodes[subTreeNum]);
        forEach(2 * subTreeNum + 1, listener);
    }
}
