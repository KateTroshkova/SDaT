package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainScene extends BorderPane {
    @FXML
    private Button createButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button balanceButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button addIdButton;
    @FXML
    private Button deleteIdButton;
    @FXML
    private Button getIdButton;
    @FXML
    private TextField addIdField;
    @FXML
    private TextField deleteIdField;
    @FXML
    private TextField getIdField;
    @FXML
    private TextField getResultField;
    @FXML
    private Pane treeField;
    @FXML
    private TextField urlField;
    @FXML
    private ChoiceBox<String> typeBox;

    private BinaryTree tree;
    private Canvas canvas;
    private GraphicsContext gc;
    private String type = "Dolphin";

    public MainScene() {
        tree = new BinaryTree();
        tree.setBuilder(TypeFactory.getByName(type)); //TODO
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        addIdButton.setOnAction(event -> {
            tree.insertFrom(addIdField.getText());
            drawTree();
        });
        getIdButton.setOnAction(event -> {
            Object value = tree.get(Integer.parseInt(getIdField.getText()));
            if (value != null) {
                getResultField.setText(value.toString());
            } else {
                getResultField.setText("No value!");
            }
        });
        deleteIdButton.setOnAction(event -> {
            tree.delete(Integer.parseInt(deleteIdField.getText()));
            drawTree();
        });
        balanceButton.setOnAction(event -> {
            tree = tree.balance();
            drawTree();
        });
        saveButton.setOnAction(event -> {
            //C:\Users\Екатерина\Desktop\sdat\tree.txt
            tree.save(urlField.getText());
        });
        loadButton.setOnAction(event -> {
            tree.readFrom(urlField.getText());
            tree = tree.balance();
            drawTree();
        });
        canvas = new Canvas(1900, 1900);
        gc = canvas.getGraphicsContext2D();
        treeField.getChildren().add(canvas);
    }

    private void drawTree() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        int size = tree.size(1);
        for (int i = 1; i <= size; i++) {
            int level = tree.level(i);
            Object value = tree.get(i);
            gc.fillOval(i * 40, level * 40, 40, 40);
            gc.strokeText(value.toString(), i * 40 + 5, level * 40 + 20);
        }
    }
}
