package br.com.igorfernandes.A99.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CharactersAroundCircle extends Application implements ChangeListener<Number> {
    Circle circle;
    Text text;
    String str;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        str = "Welcome To Java";
        circle = new Circle();
        System.out.println(str.length());
        Pane pane = new Pane();
        circle.setRadius(125);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        pane.widthProperty().addListener(this);
        pane.heightProperty().addListener(this);

        circle.setStroke(Color.BLACK);
        circle.setFill(new Color(1, 1, 1, 0));

        text = new Text();
        text.setText(String.valueOf(str.charAt(0)));
        text.setStroke(Color.BLACK);
        text.setFont(Font.font("Times New Roman", 20));
        pane.getChildren().add(text);

        pane.getChildren().add(circle);

        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("CharactersAroundCircle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        text.setX(circle.getCenterX() + Math.toRadians(Math.sin(360 / str.length()) * circle.getRadius()));
        text.setY(circle.getCenterY() + Math.toRadians(Math.cos(360 / str.length()) * circle.getRadius()));
    }
}