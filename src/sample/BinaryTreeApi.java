package sample;

public interface BinaryTreeApi<T extends Comparable<T>> {
    int size(int subTreeNum);

    T get(int position);

    void insert(T node);

    void delete(int position);

    void balance();

    void forEach(OnNextListener<T> onNextListener);
}
