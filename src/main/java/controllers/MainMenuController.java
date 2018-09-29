package controllers;


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
 */
public class MainMenuController{

    @FXML
    Pane view;

    @FXML
    ImageView bigLogo;

    private VBox menuCollections;

    private String resourcesPath;
    private  Button btnNewGame,btnLoadGame,btnSettings,btnTutorial,btnExitGame,btnEmptyUpper,btnEmptyLower;

    public MainMenuController() {
        System.out.println("Construct complete");
        //resourcesPath = System.getProperty("user.dir")+"\\src\\main\\resources\\";
        try {
            resourcesPath = String.valueOf(new File(MainMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
            System.out.println(resourcesPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialise() {
        setup();
        System.out.println("Init complete");
    }

    private void setup() {
        setupBgImage();
        setupBigLogoImage();
        setupMainMenu();
        setupMainMenuBtns();
        addEventHandlers();
    }

    private void addEventHandlers() {
        btnExitGame.setOnAction(event -> {
            Button button = (Button) event.getTarget();
            button.setStyle("-fx-background-color: #ff6600;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif");
            System.exit(0);
        });

        btnNewGame.setOnAction(event -> {
            try {
                newGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupMainMenuBtns() {
        btnEmptyUpper = new Button();
        btnNewGame = new Button("New Game");
        btnLoadGame = new Button("Load Game");
        btnTutorial = new Button("Tutorial");
        btnSettings = new Button("Settings");
        btnExitGame = new Button("Exit Game");
        btnEmptyLower = new Button();

        List<Button> buttonList = new LinkedList<>();
        buttonList.add(btnNewGame);
        buttonList.add(btnLoadGame);
        buttonList.add(btnTutorial);
        buttonList.add(btnSettings);
        buttonList.add(btnExitGame);
        for (int i = 0; i < buttonList.size(); i++) {
            NodePlacer.initControl(view,buttonList.get(i),0.15,0.12,70,90);
            buttonList.get(i).setStyle("-fx-background-color: #4e5661;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif");
            if(i!=0||i!=buttonList.size()-1) {
                buttonList.get(i).setOnMouseEntered(event -> {
                    Button btn = (Button) event.getTarget();
                    btn.setStyle("-fx-background-color: #37a0e2;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif");
                });
                buttonList.get(i).setOnMouseExited(event -> {
                    Button btn = (Button) event.getTarget();
                    btn.setStyle("-fx-background-color: #4e5661;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif");
                });
            }
        }
        NodePlacer.initControl(view,btnEmptyUpper,0.15,0.12,70,90);
        NodePlacer.initControl(view,btnEmptyLower,0.15,0.12,70,90);
        btnEmptyUpper.setStyle("-fx-background-color: #4e5661;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif");
        btnEmptyUpper.setOpacity(0.33);
        btnEmptyLower.setStyle("-fx-background-color: #4e5661;-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: sans-serif;");
        btnEmptyLower.setOpacity(0.33);

        menuCollections.getChildren().addAll(btnEmptyUpper,btnNewGame,btnLoadGame,btnTutorial,btnSettings,btnExitGame,btnEmptyLower);

    }

    private void setupMainMenu() {
        menuCollections = new VBox();
        //menuCollections.setStyle("-fx-background-color: #c1c2c6");
        menuCollections.setId("menuCollections");
        menuCollections.setSpacing(10);
        menuCollections.setPadding(new Insets(view.getScene().getHeight()*0.033,0,0,0));
        NodePlacer.placeCollectorRelativeToScreen(view,menuCollections,0.75,0,0.15,1,800,90);
    }

    private void setupBigLogoImage() {
        Image gameLogoBig = new Image("file:"+resourcesPath+"\\gui\\images\\mainScreenLogo.png");
        ImageView gameLogoContainer = new ImageView(gameLogoBig);
        gameLogoContainer.setId("gameLogoContainer");
        NodePlacer.placeImageRelativeToScreen(view,gameLogoContainer,0.25,0.125,0.6,0.7,100,200);
    }

    private void setupBgImage() {
        view.setBackground(new Background(new BackgroundImage(new Image("file:"+resourcesPath+"\\gui\\backgroundImages\\bgMain.jpg"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/countryselection.fxml"));
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
