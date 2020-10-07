package sample;

import java.io.InputStreamReader;

/**
 * Array cast is one of the suggested ways of implementing a generic collection in Effective Java; Item 26.
 * No type errors, no need to cast the array repeatedly.
 * However this triggers a warning because it is potentially dangerous, and should be used with caution:
 * It can cause unexpected errors or ClassCastExceptions if used unsafely.
 * This behavior is safe as long as the cast array is used internally (e.g. to back a data structure),
 * and not returned or exposed to client code.
 */
public class BinaryTree implements BinaryTreeApi {

    private Object[] nodes;
    private int capacity;
    private UserTypeBuilder builder;

    public BinaryTree() {
        initEmpty();
    }

    @Override
    public UserTypeBuilder getBuilder() {
        return builder;
    }

    @Override
    public void setBuilder(UserTypeBuilder builder) {
        this.builder = builder;
        // can not work with old values
        initEmpty();
    }

    @Override
    public int size(int subTreeNum) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return 0;
        return 1 + size(2 * subTreeNum) + size(2 * subTreeNum + 1);
    }

    public int level(int position) {
        int index = getIndex(position, 1);
        return level(index, 0);
    }

    public int level(int index, int level) {
        while (index / 2 > 0) {
            index /= 2;
            level++;
        }
        return level + 1;
    }

    @Override
    public Object get(int position) {
        if (position < 1 || position >= capacity) return null;
        return get(position, 1);
    }

    // not safe!
    /*@Override
    public void insert(Object node) {
        insert(1, node);
    }*/

    @Override
    public void insertRnd() {
        insert(1, builder.create());
    }

    @Override
    public boolean insertNext(InputStreamReader reader) {
        Object value = builder.readValue(reader);
        if (value == null) return false;
        insert(1, value);
        return true;
    }

    @Override
    public void insertFrom(String source) {
        insert(1, builder.parseValue(source));
    }

    @Override
    public void delete(int position) {
        if (position < 1 || position >= capacity) return;
        int index = getIndex(position, 1);
        if (nodes[2 * index + 1] == null && nodes[2 * index] == null) nodes[index] = null; //нет потомков
        if (nodes[2 * index] != null && nodes[2 * index + 1] == null) { //только левый потомок
            nodes[index] = nodes[2 * index];
            nodes[2 * index] = null;
            return;
        }
        if (nodes[2 * index] == null && nodes[2 * index + 1] != null) { //только правый потомок
            nodes[index] = nodes[2 * index + 1];
            nodes[2 * index + 1] = null;
            return;
        }
        int max = 2 * index + 1;
        while (2 * max < capacity && nodes[2 * max] != null) {
            max *= 2;
        }
        if (2 * max + 1 < capacity && nodes[2 * max + 1] != null) {
            max = 2 * max + 1;
        }
        nodes[index] = nodes[max];
        nodes[max] = null;
    }

    @Override
    public BinaryTree balance() {
        int size = size(1);
        int nodeCount = 0;
        Object[] subTree = new Object[size];
        set(subTree, 1, nodeCount);
        BinaryTree balanced = new BinaryTree();
        balanced.capacity = capacity;
        balanced.nodes = new Object[capacity];
        balanced.builder = builder;
        balanced.balance(subTree, 0, size - 1);
        return balanced;
    }

    @Override
    public void forEach(OnNextListener onNextListener) {
        forEach(1, onNextListener);
    }

    @Override
    public String toString() {
        return toStringRecursive(1, 0, "");
    }

    private void initEmpty() {
        //Нумерация начинается с 1, т.к. данные храним в массиве
        capacity = 2;
        nodes = new Object[capacity];
    }

    private String toStringRecursive(int subTreeNum, int level, String res) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return res;
        res = toStringRecursive(2 * subTreeNum, level + 1, res);
        res += "\n";
        StringBuilder resBuilder = new StringBuilder(res);
        int maxLevel = (int) Math.sqrt(capacity);
        for (int i = 0; i < maxLevel - level; i++) {
            resBuilder.append("    ");
        }
        res = resBuilder.toString();
        res += builder.toString(nodes[subTreeNum]);
        res = toStringRecursive(2 * subTreeNum + 1, level + 1, res);
        return res;
    }

    private Object get(int position, int subTreeNum) {
        if (position < 1 || position >= capacity || position > size(subTreeNum)) return null;
        int leftNum = size(2 * subTreeNum);
        if (position <= leftNum) return get(position, 2 * subTreeNum);
        position -= leftNum;
        if (position - 1 == 0) return nodes[subTreeNum];
        return get(position - 1, 2 * subTreeNum + 1);
    }

    private int getIndex(int position, int subTreeNum) {
        if (position < 1 || position >= capacity || position > size(subTreeNum)) return 0;
        int leftNum = size(2 * subTreeNum);
        if (position <= leftNum) return getIndex(position, 2 * subTreeNum);
        position -= leftNum;
        if (position - 1 == 0) return subTreeNum;
        return getIndex(position - 1, 2 * subTreeNum + 1);
    }

    private int set(Object[] subTree, int subTreeNum, int nodeCount) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return nodeCount;
        nodeCount = set(subTree, 2 * subTreeNum, nodeCount);
        subTree[nodeCount++] = builder.clone(nodes[subTreeNum]);
        nodeCount = set(subTree, 2 * subTreeNum + 1, nodeCount);
        return nodeCount;
    }

    private void insert(int position, Object node) {
        if (position < 1) return;
        if (position >= capacity) {
            Object[] values = new Object[capacity];
            System.arraycopy(nodes, 0, values, 0, nodes.length);
            capacity = 2 * capacity + 2;
            nodes = new Object[capacity];
            System.arraycopy(values, 0, nodes, 0, values.length);
        }
        if (nodes[position] == null) {
            nodes[position] = node;
            return;
        }
        if (builder.compare(node, nodes[position]) < 0) {
            insert(2 * position, node);
        } else {
            insert(2 * position + 1, node);
        }
    }

    private void balance(Object[] subTree, int a, int b) {
        if (a > b) return;
        int m = (a + b) / 2;
        insert(1, subTree[m]);
        balance(subTree, a, m - 1);
        balance(subTree, m + 1, b);
    }

    private void forEach(int subTreeNum, OnNextListener listener) {
        if (subTreeNum >= capacity || nodes[subTreeNum] == null) return;
        forEach(2 * subTreeNum, listener);
        listener.toDo(nodes[subTreeNum]);
        forEach(2 * subTreeNum + 1, listener);
    }
}
