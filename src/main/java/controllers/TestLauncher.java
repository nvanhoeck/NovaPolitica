package controllers;

import controllers.countryselection.CountrySelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import tools.Constants;

import java.awt.*;


/**
 * Wordt gebruikt om bepaalde schermen te testen zonder telkens te moeten klikken.
 */
public class TestLauncher extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        String version = Constants.VERSION;
        String author = "Niko Van Hoeck";
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenwidth= screenSize.getWidth();
        double screenheight = screenSize.getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/countryselection.fxml"));
        Parent root = fxmlLoader.load();
        CountrySelectionController controller = fxmlLoader.getController();

        Scene scene  = new Scene(root,screenwidth,screenheight);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        // Prevents exit showup
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("Nova Politica - " + version);
        primaryStage.show();

        controller.initialise();
        scene.getRoot().requestFocus();
    }
}
