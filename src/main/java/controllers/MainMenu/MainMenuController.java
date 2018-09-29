package controllers.MainMenu;


import controllers.CountrySelectionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sun.misc.Launcher;
import tools.Constants;
import tools.OverallKeyController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import tools.NodePlacer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;


/**
 * Dit is het eerste scherm dat men te zien krijgt wanneer men het spel start.
 * Men kan hier een nieuw spel starten, een oud spel laden, de settings veranderen en afsluiten.
 */
public class MainMenuController{

    @FXML
    Pane view;

    @FXML
    ImageView bigLogo;

    private MainMenuElementsBuilder elementsBuilder;

    public MainMenuController() {
        System.out.println("Construct complete");
    }


    @FXML
    public void initialise() {
        elementsBuilder = new MainMenuElementsBuilder(this.view, this.bigLogo);
        setup();
        System.out.println("Init complete");
    }

    private void setup() {
        elementsBuilder.setupBgImage();
        elementsBuilder.setupBigLogoImage();
        elementsBuilder.setupMainMenu();
        elementsBuilder.setupMainMenuBtns();
        addEventHandlers();
    }

    private void addEventHandlers() {
        elementsBuilder.getBtnExitGame().setOnAction(event -> {
            Button button = (Button) event.getTarget();
            button.setStyle("-fx-background-color: #ff6600;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif");
            System.exit(0);
        });

        elementsBuilder.getBtnNewGame().setOnAction(event -> {
            try {
                newGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    /**
     *
     * @param keyEvent
     * Wordt gebruikt om bepaalde combinaties (zoals de ctrl+shift+alt) van keytoetsen te gebruiken.
     */
    @FXML
    void keyPressed(KeyEvent keyEvent) {
        OverallKeyController.keyPressed(keyEvent);
    }

    private void newGame(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) this.view.getScene().getWindow();
        Scene oldScene = this.view.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/countryselection.fxml"));
        Parent root = fxmlLoader.load();
        CountrySelectionController controller = fxmlLoader.getController();

        Scene scene  = new Scene(root,oldScene.getWidth(),oldScene.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();

        controller.initialise();
        scene.getRoot().requestFocus();
    }
}
