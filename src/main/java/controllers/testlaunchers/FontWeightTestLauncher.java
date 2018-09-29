package controllers.testlaunchers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Wordt gebruikt om te kijken hoe de font weight zich op het scherm weergeeft.
 */
public class FontWeightTestLauncher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Pane view = new Pane();
        ListView<Label> listView = new ListView<>();
        ObservableList list = FXCollections.observableArrayList();
        view.setPrefWidth(1000);
        view.setPrefHeight(750);
        int counter = 0;
        for (FontWeight fontWeight : FontWeight.values()) {
            Label label = new Label(fontWeight.toString() + ": " + "Select a Country\n");
            label.setPrefSize(800, 50);
            label.setFont(Font.font("Lucida Bright", fontWeight,
                    40));
            label.setLayoutY(counter++ * 50);
            list.add(label);
        }
        primaryStage.setTitle("Bookshelf Symbol 7");
        listView.setItems(list);
        listView.setPrefWidth(800);
        listView.setPrefHeight(750);
        view.getChildren().add(listView);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }
}
