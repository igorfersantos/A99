package br.com.igorfernandes.A99.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextVsLabel extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane labelPane = new StackPane();
        labelPane.setMinSize(250, 250);

        Label label = new Label("A Label");
        label.setStyle("-fx-background-color: -fx-background;");

        labelPane.getChildren().add(label);
        addLineToPane(labelPane);

        StackPane textPane = new StackPane();
        textPane.setMinSize(250, 250);
        textPane.getChildren().add(new Text("A Text"));
        addLineToPane(textPane);

        HBox root = new HBox(5, labelPane, textPane);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addLineToPane(Pane pane) {
        Line line = new Line();
        line.startXProperty().bind(pane.widthProperty().divide(2));
        line.endXProperty().bind(line.startXProperty());
        line.setStartY(0);
        line.endYProperty().bind(pane.heightProperty());

        // add line to beginning of pane's parent list, so it appears
        // behind everything else
        pane.getChildren().add(0, line);
    }

    public static void main(String[] args) {
        launch(args);
    }
}