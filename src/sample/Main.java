package sample;

import java.security.SecureRandom;

public class Main/* extends Application */ {

    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) {
        //launch(args);
        BinaryTreeApi<Node<String>> tree = new BinaryTree<>();
        for (int i = 0; i < 15; i++) {
            tree.insert(new Node<>(randomString(4)));
            if (i > 2) {
                tree.balance();
            }
        }
        System.out.println(tree.get(1).getValue());
        tree.delete(1);
        tree.balance();
        tree.forEach(node -> {
            String newValue = node.getValue() + "~";
            node.setValue(newValue);
        });
        System.out.println(tree.toString());
    }

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        int maxLength = CHARS.length();
        for (int i = 0; i < length; i++)
            sb.append(CHARS.charAt(rnd.nextInt(maxLength)));
        return sb.toString();
    }

/*    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/
}
