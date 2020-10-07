package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        /*BinaryTreeApi tree = new BinaryTree();
        tree.setBuilder(TypeFactory.getByName("String"));
        for (int i = 0; i < 15; i++) {
            tree.insertRnd();
            if (i > 0) {
                tree = tree.balance();
            }
        }
        System.out.println("Тестовая программа для лабораторной 1 (консольное бинарное дерево)");
        System.out.println("Заполненное случайными строками и сбалансированное дерево");
        System.out.println(tree.toString());
        System.out.println("...");
        System.out.print("Элемент для удаления ");
        System.out.println(tree.get(1));
        tree.delete(1);
        tree = tree.balance();
        System.out.println("Дерево без удаленного элемента");
        System.out.println(tree.toString());
        System.out.println("Операция для каждого элемента дерева: вывод заглавными буквами");
        tree.forEach(node -> {
            System.out.print(((String) node).toUpperCase() + " ");
        });*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SDAT2");
        MainScene mainScene = new MainScene();
        Scene scene = new Scene(mainScene, 620, 400);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());
    }
}
