package controllers.countryselection;

import bl.domain.countries.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import tools.Coordinate;
import tools.NodePlacer;
import tools.ShapeDrawer;
import tools.Constants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CountrySelectionElementsBuilder {

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
    
    private Pane view;

    CountrySelectionElementsBuilder(Pane view) {
        this.view = view;
    }

    void setupDetails() {
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

    void setupCountryFlags(HashMap<String, Country> countries) {
        List<String> countryNames = countries.values().stream().map(Country::getName).collect(Collectors.toList());
        setup1stCenterDiamond(countryNames);
        setup2ndCenterDiamond(countryNames);
    }


    // TODO Remove code duplication + aanpassen want dit was voor te testen
    void setup1stCenterDiamond(List<String> countryNames) {
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
        centerDiamonds1stFlag = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds1stFlag, "file:" + Constants.RESOURCESPATH + "\\data\\gameData\\images\\countries\\romania_medium.png", Color.BLACK, 3);
        centerDiamonds1stFlag.setBlendMode(BlendMode.COLOR_BURN);

        //Grunge
        List<Coordinate> coordinatesCenterDiamonds1stGrunge = new LinkedList<>();
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        centerDiamonds1stGrunge = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds1stGrunge, "file:" + Constants.RESOURCESPATH + "\\data\\gameData\\images\\misc\\paper_flag_medium.png", Color.BLACK, 3);

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

    void setup2ndCenterDiamond(List<String> countryNames) {
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
        centerDiamonds2ndFlag = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds2ndFlag, "file:" + Constants.RESOURCESPATH + "\\data\\gameData\\images\\countries\\" + countryNames.get(0) + "_medium.png", Color.BLACK, 3);
        centerDiamonds2ndFlag.setBlendMode(BlendMode.COLOR_BURN);

        //Grunge
        List<Coordinate> coordinatesCenterDiamonds2ndGrunge = new LinkedList<>();
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds2ndGrunge.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
        //TODO 0 moet 3 worden indien minsten 4 landen
        centerDiamonds2ndGrunge = ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 220, 220, coordinatesCenterDiamonds2ndGrunge, "file:" + Constants.RESOURCESPATH + "\\data\\gameData\\images\\misc\\paper_flag_medium.png", Color.BLACK, 3);

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

    void setupContinentIcon() {
        continentsIcon = new ImageView(new Image("file:" + Constants.RESOURCESPATH + "\\gui\\icons\\general\\globe.png"));
        NodePlacer.placeImageRelativeToScreen(view, continentsIcon, dropDownBox.getLayoutX()/view.getWidth(), dropDownBox.getLayoutY()/view.getHeight(), ((dropDownBox.getWidth()/view.getWidth())*0.25),dropDownBox.getHeight()/view.getHeight(),50,50);
        continentsIcon.setFitHeight(dropDownBox.getMinHeight()*0.75);
        continentsIcon.setLayoutY(continentsIcon.getLayoutY()+5);
        continentsIcon.setLayoutX(continentsIcon.getLayoutX()+5);
        continentsIcon.setPreserveRatio(true);
    }

    void setupContinentBox() {
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

    void setupScreenTitle() {
        screenTitle = new Label();
        NodePlacer.initLabel(screenTitle, "Select Country", "Bell MT", FontWeight.MEDIUM, Color.rgb(51, 51, 51), 48, FontPosture.REGULAR);
        screenTitle.setAlignment(Pos.TOP_LEFT);
        NodePlacer.placeLabelRelativeToScreen(view, screenTitle, ((this.view.getWidth()/2) - ((double)(this.screenTitle.getText().length()*48)/5)) / this.view.getWidth(), 0.025, 0.33, 0.15, 66, 200);
    }

    void setupBgImage() {
        view.setBackground(new Background(new BackgroundImage(new Image("file:" + Constants.RESOURCESPATH + "\\gui\\backgroundImages\\newGameBg.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public ComboBox<String> getDropDownBox() {
        return dropDownBox;
    }

    public ImageView getContinentsIcon() {
        return continentsIcon;
    }

    public Label getScreenTitle() {
        return screenTitle;
    }

    public Canvas getCenterDiamonds2ndBorder() {
        return centerDiamonds2ndBorder;
    }

    public Canvas getCenterDiamonds2ndGrunge() {
        return centerDiamonds2ndGrunge;
    }

    public Canvas getCenterDiamonds2ndFlag() {
        return centerDiamonds2ndFlag;
    }

    public Canvas getCenterDiamonds1stBorder() {
        return centerDiamonds1stBorder;
    }

    public Canvas getCenterDiamonds1stGrunge() {
        return centerDiamonds1stGrunge;
    }

    public Canvas getCenterDiamonds1stFlag() {
        return centerDiamonds1stFlag;
    }

    public HashMap<Node, String> getFlags() {
        return flags;
    }

    public Label getCountryName() {
        return countryName;
    }

    public Label getCountryDesc() {
        return countryDesc;
    }

    public Pane getView() {
        return view;
    }
}
