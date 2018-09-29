package controllers;

import bl.InitService;
import bl.domain.countries.Country;
import controllers.animationHandlers.CountrySelectionAnimationHandler;
import javafx.animation.ParallelTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import tools.Coordinate;
import tools.NodePlacer;
import tools.OverallKeyController;
import tools.ShapeDrawer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hierin gaat de speler zijn land kiezen om een politieke partij op te richten.
 * Hij kan hiervoor filteren op verschillende continenten.
 */
public class CountrySelectionController {

    //DATA
    private InitService initService;
    private HashMap<String, Country> countries;
    // BASE path van waaruit het project vertrekt.
    private String resourcesPath;

    @FXML
    Pane view;

    //Continent & General
    private ComboBox<String> dropDownBox;
    private ImageView continentsIcon;
    private Label screenTitle;
    //Flags
    private Canvas centerDiamonds2ndBorder, centerDiamonds2ndGrunge, centerDiamonds2ndFlag;
    private Canvas centerDiamonds1stBorder, centerDiamonds1stGrunge, centerDiamonds1stFlag;

    private HashMap<Node, String> flags = new HashMap<>();

    //Details
    private Label countryName;
    private Label countryDesc;

    //Animation
    private ParallelTransition showSelected = new ParallelTransition();
    private CountrySelectionAnimationHandler animationHandler = new CountrySelectionAnimationHandler();
    private List<Node> selectedFlag = new LinkedList<>();
    private boolean isEnlarged = false;
    private String selectedFlagName = "none";
    private List<Coordinate> originalCoordinates = new LinkedList<>();


    /**
     * Initialisatie waarbij men de Base path gaat bepalen.
     * Alle nodige elementen worden hier ook aangeroepen.
     * @throws FileNotFoundException
     */
    @SuppressWarnings("JavaDoc")
    @FXML
    public void initialise() throws FileNotFoundException {
        try {
            resourcesPath = String.valueOf(new File(CountrySelectionController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
            System.out.println(resourcesPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        initService = new InitService();
        loadingGameData();
        setupBgImage();
        setupScreenTitle();
        setupContinentBox();
        setupContinentIcon();
        setupCountryFlags();
        setupDetails();
        addEventHandlers();
    }

    private void setupDetails() {
        countryName = new Label();
        //TODO mogelijk een textarea ipv een label
        countryDesc = new Label();
        NodePlacer.initLabel(countryName, "", "Bell MT", FontWeight.BOLD, Color.rgb(51,51,51), 36, FontPosture.REGULAR);
        NodePlacer.initLabel(countryDesc, "", "Lucida Sans", FontWeight.MEDIUM, Color.rgb(51,51,51), 18, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(view, countryName, 0.33, 0.675, 0.66, 0.25, 50,100);
        NodePlacer.placeLabelRelativeToScreen(view, countryDesc, 0.15, 0.75, 0.7, 0.225, 150, 250);
        countryName.setVisible(false);
        countryName.setAlignment(Pos.TOP_LEFT);
        countryName.setTextAlignment(TextAlignment.CENTER);
        countryDesc.setVisible(false);
        //TODO Maak tekst in het midden zodat je links niet alles in blok hebt ==> zie javafx scene builder
        countryDesc.setAlignment(Pos.TOP_CENTER);
        countryDesc.setTextAlignment(TextAlignment.CENTER);
        countryDesc.setWrapText(true);
        countryDesc.setStyle("-fx-font-scale: true");
    }

    private void setupCountryFlags() {
        List<String> countryNames = countries.values().stream().map(Country::getName).collect(Collectors.toList());
        setup1stCenterDiamond(countryNames);
        setup2ndCenterDiamond(countryNames);
    }


    // TODO Remove code duplication + aanpassen want dit was voor te testen
    private void setup1stCenterDiamond(List<String> countryNames) {
        //Border
        List<Coordinate> coordinatesCenterDiamonds1stBorder = new LinkedList<>();
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(1.0, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0.5, 1.0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(0, 0, 0)), new Stop(0.49, Color.rgb(0, 0, 0)), new Stop(0.5, Color.rgb(51, 51, 51)), new Stop(1, Color.rgb(51, 51, 51))};
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        centerDiamonds1stBorder = ShapeDrawer.drawShape(view, Coordinate.Type.PIXELBASED, 240, 240, coordinatesCenterDiamonds1stBorder, gradient, Color.BLACK, 3);

        //Flag
        List<Coordinate> coordinatesCenterDiamonds1stFlag = new LinkedList<>();
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        centerDiamonds1stFlag = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds1stFlag, "file:" + resourcesPath + "\\data\\gameData\\images\\countries\\romania_medium.png", Color.BLACK, 3);
        centerDiamonds1stFlag.setBlendMode(BlendMode.COLOR_BURN);

        //Grunge
        List<Coordinate> coordinatesCenterDiamonds1stGrunge = new LinkedList<>();
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        centerDiamonds1stGrunge = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds1stGrunge, "file:" + resourcesPath + "\\data\\gameData\\images\\misc\\paper_flag_medium.png", Color.BLACK, 3);

        NodePlacer.placeNodeRelativeToScreen(view, centerDiamonds1stBorder, centerDiamonds1stBorder.getWidth(), 0.3, centerDiamonds1stBorder.getWidth() / view.getWidth(), centerDiamonds1stBorder.getHeight() / view.getHeight(), 230, 230);
        NodePlacer.placeNodeRelativeToScreen(view, centerDiamonds1stGrunge, 0, 0.3, centerDiamonds1stGrunge.getWidth() / view.getWidth(), centerDiamonds1stGrunge.getHeight() / view.getHeight(), 220, 220);
        NodePlacer.placeNodeRelativeToScreen(view, centerDiamonds1stFlag, 0, 0.3, centerDiamonds1stFlag.getWidth() / view.getWidth(), centerDiamonds1stFlag.getHeight() / view.getHeight(), 220, 220);

        //TODO automatisch de naam bepalen
        flags.put(centerDiamonds1stBorder, "Romania");
        flags.put(centerDiamonds1stGrunge, "Romania");
        flags.put(centerDiamonds1stFlag, "Romania");

        centerDiamonds1stBorder.setLayoutX((view.getWidth() / 3) - (centerDiamonds1stBorder.getWidth() / 3)-2.5);
        centerDiamonds1stGrunge.setLayoutX((view.getWidth() / 3) - (centerDiamonds1stGrunge.getWidth() / 3));
        centerDiamonds1stFlag.setLayoutX((view.getWidth() / 3) - (centerDiamonds1stFlag.getWidth() / 3));
        centerDiamonds1stBorder.setLayoutY(centerDiamonds1stBorder.getLayoutY() - 10);

        Reflection reflectionBase = new Reflection();
        reflectionBase.setFraction(0.5);
        reflectionBase.setTopOffset(10 * 2);
        reflectionBase.setBottomOpacity(0);
        reflectionBase.setTopOpacity(0.2);
        Reflection reflectionBorder = new Reflection();
        reflectionBorder.setFraction(0.5);
        reflectionBorder.setTopOffset(5);
        reflectionBorder.setBottomOpacity(0);
        reflectionBorder.setTopOpacity(0.2);
        centerDiamonds1stBorder.setEffect(reflectionBorder);
        centerDiamonds1stGrunge.setEffect(reflectionBase);
        centerDiamonds1stFlag.setEffect(reflectionBase);

    }

    private void setup2ndCenterDiamond(List<String> countryNames) {
        //Border
        List<Coordinate> coordinatesCenterDiamonds2ndBorder = new LinkedList<>();
        coordinatesCenterDiamonds2ndBorder.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndBorder.add(new Coordinate(1.0, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndBorder.add(new Coordinate(0.5, 1.0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndBorder.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(0, 0, 0)), new Stop(0.49, Color.rgb(0, 0, 0)), new Stop(0.5, Color.rgb(51, 51, 51)), new Stop(1, Color.rgb(51, 51, 51))};
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        centerDiamonds2ndBorder = ShapeDrawer.drawShape(view, Coordinate.Type.PIXELBASED, 240, 240, coordinatesCenterDiamonds2ndBorder, gradient, Color.BLACK, 3);

        //Flag
        List<Coordinate> coordinatesCenterDiamonds2ndFlag = new LinkedList<>();
        coordinatesCenterDiamonds2ndFlag.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndFlag.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndFlag.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndFlag.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        centerDiamonds2ndFlag = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds2ndFlag, "file:" + resourcesPath + "\\data\\gameData\\images\\countries\\" + countryNames.get(0) + "_medium.png", Color.BLACK, 3);
        centerDiamonds2ndFlag.setBlendMode(BlendMode.COLOR_BURN);

        //Grunge
        List<Coordinate> coordinatesCenterDiamonds2ndGrunge = new LinkedList<>();
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        centerDiamonds2ndGrunge = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds2ndGrunge, "file:" + resourcesPath + "\\data\\gameData\\images\\misc\\paper_flag_medium.png", Color.BLACK, 3);

        NodePlacer.placeNodeRelativeToScreen(view, centerDiamonds2ndBorder, centerDiamonds2ndBorder.getWidth(), 0.3, centerDiamonds2ndBorder.getWidth() / view.getWidth(), centerDiamonds2ndBorder.getHeight() / view.getHeight(), 230, 230);
        NodePlacer.placeNodeRelativeToScreen(view, centerDiamonds2ndGrunge, 0.4, 0.3, centerDiamonds2ndGrunge.getWidth() / view.getWidth(), centerDiamonds2ndGrunge.getHeight() / view.getHeight(), 220, 220);
        NodePlacer.placeNodeRelativeToScreen(view, centerDiamonds2ndFlag, 0.4, 0.3, centerDiamonds2ndFlag.getWidth() / view.getWidth(), centerDiamonds2ndFlag.getHeight() / view.getHeight(), 220, 220);

        //TODO automatisch de naam bepalen
        flags.put(centerDiamonds2ndBorder, countryNames.get(0));
        flags.put(centerDiamonds2ndGrunge, countryNames.get(0));
        flags.put(centerDiamonds2ndFlag, countryNames.get(0));

        centerDiamonds2ndBorder.setLayoutX((view.getWidth() / 2) - (centerDiamonds2ndBorder.getWidth() / 2));
        centerDiamonds2ndGrunge.setLayoutX((view.getWidth() / 2) - (centerDiamonds2ndGrunge.getWidth() / 2));
        centerDiamonds2ndFlag.setLayoutX((view.getWidth() / 2) - (centerDiamonds2ndFlag.getWidth() / 2));
        centerDiamonds2ndBorder.setLayoutY(centerDiamonds2ndBorder.getLayoutY() - 10);

        Reflection reflectionBase = new Reflection();
        reflectionBase.setFraction(0.5);
        reflectionBase.setTopOffset(10 * 2);
        reflectionBase.setBottomOpacity(0);
        reflectionBase.setTopOpacity(0.2);
        Reflection reflectionBorder = new Reflection();
        reflectionBorder.setFraction(0.5);
        reflectionBorder.setTopOffset(5);
        reflectionBorder.setBottomOpacity(0);
        reflectionBorder.setTopOpacity(0.2);
        centerDiamonds2ndBorder.setEffect(reflectionBorder);
        centerDiamonds2ndGrunge.setEffect(reflectionBase);
        centerDiamonds2ndFlag.setEffect(reflectionBase);

    }

    private void setupContinentIcon() {
        continentsIcon = new ImageView(new Image("file:" + resourcesPath + "\\gui\\icons\\general\\globe.png"));
        NodePlacer.placeImageRelativeToScreen(view, continentsIcon, dropDownBox.getLayoutX()/view.getWidth(), dropDownBox.getLayoutY()/view.getHeight(), ((dropDownBox.getWidth()/view.getWidth())*0.25),dropDownBox.getHeight()/view.getHeight(),50,50);
        continentsIcon.setFitHeight(dropDownBox.getMinHeight()*0.75);
        continentsIcon.setLayoutY(continentsIcon.getLayoutY()+5);
        continentsIcon.setLayoutX(continentsIcon.getLayoutX()+5);
        continentsIcon.setPreserveRatio(true);
    }

    private void setupContinentBox() {
        // TODO globaal maken en ophalen uit DAL
        ObservableList<String> continents = FXCollections.observableArrayList("EUROPE");
        dropDownBox = new ComboBox<>(continents);
        NodePlacer.placeControlRelativeToScreen(view, dropDownBox, 0.05, 0.1, 0, 0, 35, 175);
        dropDownBox.getSelectionModel().select(0);
        view.getStylesheets().clear();
        dropDownBox.getStyleClass().removeAll();
        System.out.println("../css/newGame.Styles.css");
        dropDownBox.getStylesheets().add("/gui/css/newGameStyles.css");
        dropDownBox.getStyleClass().addAll("combo-box");
    }

    private void setupScreenTitle() {
        screenTitle = new Label();
        NodePlacer.initLabel(screenTitle, "Select Country", "Bell MT", FontWeight.MEDIUM, Color.rgb(51, 51, 51), 48, FontPosture.REGULAR);
        screenTitle.setAlignment(Pos.TOP_LEFT);
        NodePlacer.placeLabelRelativeToScreen(view, screenTitle, ((this.view.getWidth()/2) - ((double)(this.screenTitle.getText().length()*48)/5)) / this.view.getWidth(), 0.025, 0.33, 0.15, 66, 200);
    }

    private void setupBgImage() {
        view.setBackground(new Background(new BackgroundImage(new Image("file:" + resourcesPath + "\\gui\\backgroundImages\\newGameBg.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    private void loadingGameData() throws FileNotFoundException {
        this.countries = (HashMap<String, Country>) initService.initCountries();
    }

    /**
     * Wordt gebruikt om animaties te tonen via de animationHandler.
     */
    private void showSelectionAnimation() {
        for (Node flag : this.selectedFlag) {
            this.originalCoordinates.add(new Coordinate(flag.getLayoutX(), flag.getLayoutY(), Coordinate.Type.PIXELBASED));
        }
        this.showSelected = this.animationHandler.showSelected(this.selectedFlag);
        this.showSelected.play();
    }

    private void addEventHandlers() {
        //TODO deze gaat wrschnlijk niet meer kloppen wanneer er meerdere landen bij komen kijken
        this.flags.keySet().forEach(flag -> {
            flag.setOnMouseEntered(event -> {
                if(selectedFlag.contains(flag)) {
                    showSelected.pause();
                }
            });
            flag.setOnMouseExited(reflag -> {
                if(selectedFlag.contains(flag)) {
                    showSelected.play();
                }
            });
        });

        for (Map.Entry<Node, String> flagEntry : flags.entrySet()) {
            Node flag = flagEntry.getKey();
            flag.setOnMouseClicked(event -> {
                if (isEnlarged && !selectedFlagName.equals(flagEntry.getValue())) {
                    showSelected.stop();
                    ParallelTransition reset = animationHandler.resetShowSelected();
                    reset.play();
                    //Todo checken of er nog iets moet gebeuren
                }else if (!isEnlarged) {
                    //Verplaatst alle vlaggen naar omhoog zodat de details te voorschijn komen
                    ParallelTransition showDetails = animationHandler.displayDetails(flags.keySet());
                    showDetails.play();
                    showDetails.setOnFinished(event1 -> {
                        isEnlarged = true;
                        int flagCounter = 0;
                        for (Node flag1 : selectedFlag) {
                            flag1.setLayoutX(originalCoordinates.get(flagCounter).getX());
                            flag1.setLayoutY(originalCoordinates.get(flagCounter++).getY());
                        }
                        selectedFlag = new LinkedList<>();
                        for (Map.Entry<Node, String> entry : flags.entrySet()) {
                            if (Objects.equals(flagEntry.getValue(), entry.getValue())) {
                                selectedFlag.add(entry.getKey());
                            }
                        }
                        selectedFlagName = flagEntry.getValue();
                        countryName.setText(countries.get(selectedFlagName).getName().toUpperCase());
                        countryName.setVisible(true);
                        countryName.setLayoutX((view.getWidth()/2)-((countryName.getText().length()*countryName.getFont().getSize()))/3);
                        countryDesc.setText(countries.get(selectedFlagName).getDesc());
                        countryDesc.setVisible(true);
                        showSelectionAnimation();
                    });

                }
            });
        }
    }

    /**
     * Kan nodig zijn om key combo's te capturen (zoals ctrl+shift+alt + E)
     * @param keyEvent
     */
    @SuppressWarnings("JavaDoc")
    @FXML
    void keyPressed(KeyEvent keyEvent) {
        OverallKeyController.keyPressed(keyEvent);
    }
}
