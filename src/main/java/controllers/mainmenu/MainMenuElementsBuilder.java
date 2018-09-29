package controllers.mainmenu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import tools.Constants;
import tools.NodePlacer;

import java.util.LinkedList;
import java.util.List;

public class MainMenuElementsBuilder {

    private ImageView bigLogo;
    private Pane view;
    private  Button btnNewGame,btnLoadGame,btnSettings,btnTutorial,btnExitGame,btnEmptyUpper,btnEmptyLower;

    private VBox menuCollections;

    public MainMenuElementsBuilder(Pane view, ImageView bigLogo) {
        this.view = view;
        this.bigLogo = bigLogo;
    }

    public void setupMainMenuBtns() {
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

    public void setupMainMenu() {
        menuCollections = new VBox();
        //menuCollections.setStyle("-fx-background-color: #c1c2c6");
        menuCollections.setId("menuCollections");
        menuCollections.setSpacing(10);
        menuCollections.setPadding(new Insets(view.getScene().getHeight()*0.033,0,0,0));
        NodePlacer.placeCollectorRelativeToScreen(view,menuCollections,0.75,0,0.15,1,800,90);
    }

    public void setupBigLogoImage() {
        Image gameLogoBig = new Image("file:"+ Constants.RESOURCESPATH+"\\gui\\images\\mainScreenLogo.png");
        ImageView gameLogoContainer = new ImageView(gameLogoBig);
        gameLogoContainer.setId("gameLogoContainer");
        NodePlacer.placeImageRelativeToScreen(view,gameLogoContainer,0.25,0.125,0.6,0.7,100,200);
    }

    public void setupBgImage() {
        view.setBackground(new Background(new BackgroundImage(new Image("file:"+Constants.RESOURCESPATH+"\\gui\\backgroundImages\\bgMain.jpg"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public ImageView getBigLogo() {
        return bigLogo;
    }

    public Pane getView() {
        return view;
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Button getBtnLoadGame() {
        return btnLoadGame;
    }

    public Button getBtnSettings() {
        return btnSettings;
    }

    public Button getBtnTutorial() {
        return btnTutorial;
    }

    public Button getBtnExitGame() {
        return btnExitGame;
    }

    public Button getBtnEmptyUpper() {
        return btnEmptyUpper;
    }

    public Button getBtnEmptyLower() {
        return btnEmptyLower;
    }

    public VBox getMenuCollections() {
        return menuCollections;
    }
}
