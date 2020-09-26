package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        //launch(args);
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < 15; i++) {
            tree.insert(new Node(i));
            if (i > 2) {
                tree.balance();
            }
        }
        tree.delete(1);
        tree.balance();
        System.out.println(tree.toString());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
