package sample;

import java.io.InputStreamReader;

public interface BinaryTreeApi {

    UserTypeBuilder getBuilder();

    void setBuilder(UserTypeBuilder builder);

    int size(int subTreeNum);

    Object get(int position);

    void insertRnd();

    boolean insertNext(InputStreamReader reader);

    void insertFrom(String source);

    void delete(int position);

    BinaryTree balance();

    void forEach(OnNextListener onNextListener);
}
