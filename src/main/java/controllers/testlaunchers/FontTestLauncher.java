package controllers.testlaunchers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Wordt gebruikt om de fonts weer te geven op het scherm.
 * Wordt zelf manueel aangepast.
 */
public class FontTestLauncher extends Application {
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
        for(String font : Font.getFamilies()){
            Label label = new Label(font + ": " + "Select a Country\n");
            label.setPrefSize(800, 75);
            label.setFont(Font.font(font, FontWeight.MEDIUM,
                    64));
            label.setLayoutY(counter++ * 75);
            list.add(label);
        }
        primaryStage.setTitle("Sitka Small");
        listView.setItems(list);
        listView.setPrefWidth(800);
        listView.setPrefHeight(750);
        view.getChildren().add(listView);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }
}
