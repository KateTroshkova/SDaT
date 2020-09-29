package sample;

public class Main/* extends Application */ {

    public static void main(String[] args) {
        //launch(args);
        UserTypeBuilder builder = TypeFactory.getByName("String");
        BinaryTreeApi tree = new BinaryTree();
        tree.setBuilder(builder);
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
        });
    }

/*    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/
}
