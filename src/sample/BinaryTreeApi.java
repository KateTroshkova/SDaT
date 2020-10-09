package sample;

public interface BinaryTreeApi {

    UserTypeBuilder getBuilder();

    void setBuilder(UserTypeBuilder builder);

    int size(int subTreeNum);

    Object get(int position);

    void insert(Object o);

    void delete(int position);

    BinaryTree balance();

    void forEach(OnNextListener onNextListener);

    int level(int position);

    void readFrom(String url);

    void save(String url);
}
