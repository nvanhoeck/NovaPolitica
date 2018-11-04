package controllers;

import bl.InitService;
import bl.domain.countries.Country;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import tools.Constants;
import tools.ViewConstants;

import javax.swing.text.View;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * Wordt gebruikt om bepaalde schermen te testen zonder telkens te moeten klikken.
 */
public class TestLauncher extends Application {
    private InitService initService;
    private HashMap<String, Country> countries;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String version = Constants.VERSION;
        String author = "Niko Van Hoeck";
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenwidth = screenSize.getWidth();
        double screenheight = screenSize.getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/regionselection.fxml"));
        Parent root = fxmlLoader.load();
        Controllers controller = fxmlLoader.getController();

        ViewConstants.screenheight = screenheight;
        ViewConstants.screenwidth = screenwidth;

        Scene scene = new Scene(root, screenwidth, screenheight);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        // Prevents exit showup
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("Nova Politica - " + version);
        primaryStage.show();

        // Load startupGameData
        initService = new InitService();
        loadingGameData();

        controller.loadData(this.countries.get("Belgium"));

        controller.initialise();
        scene.getRoot().requestFocus();
    }

    private void loadingGameData() throws FileNotFoundException {
        this.countries = (HashMap<String, Country>) initService.initCountries();
    }
}
