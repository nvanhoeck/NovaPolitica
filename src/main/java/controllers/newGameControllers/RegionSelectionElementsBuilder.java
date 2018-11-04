package controllers.newGameControllers;

import bl.domain.regions.Region;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import tools.*;

import java.util.*;

public class RegionSelectionElementsBuilder {

    private Pane view;
    private Label screenTitle;
    private ComboBox <String> dropDownBox;
    private Label continueBtn;
    private ImageView continueIcon;


    private int xList = 3;
    private int yList;

    private Map <String, Flag> flags;

    public RegionSelectionElementsBuilder(Pane view) {
        this.view = view;
        this.flags = new HashMap <>();
    }

    public void setupDiamonds() {
        for (int i = 0; i < 2; i++) {
            List <Coordinate> coordinates = new ArrayList <>();
            coordinates.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
            coordinates.add(new Coordinate(1.0, 0.5, Coordinate.Type.PERCENTBASED));
            coordinates.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
            coordinates.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));
            Canvas diamond = ShapeDrawer.drawShape(this.view, Coordinate.Type.PIXELBASED, 30, 35, coordinates, ColorConstants.neutralDarkGrey, ColorConstants.neutralDarkGrey, 1);
            diamond.setLayoutX(ViewConstants.screenwidth * 0.6 - 12);
            diamond.setLayoutY((0.1 * ViewConstants.screenheight) + (i * (ViewConstants.screenheight * 0.8 - 35)));
            this.view.getChildren().add(diamond);
        }
    }

    public void setupSplitLine() {
        List <Coordinate> coordinates = new ArrayList <>();
        coordinates.add(new Coordinate(0, 0, Coordinate.Type.PERCENTBASED));
        coordinates.add(new Coordinate(1.0, 0, Coordinate.Type.PERCENTBASED));
        coordinates.add(new Coordinate(1.0, 1.0, Coordinate.Type.PERCENTBASED));
        coordinates.add(new Coordinate(0, 1.0, Coordinate.Type.PERCENTBASED));
        Canvas splitLine = ShapeDrawer.drawShape(this.view, Coordinate.Type.PIXELBASED, 5, ViewConstants.screenheight * 0.6, coordinates, ColorConstants.neutralDarkGrey, ColorConstants.neutralDarkGrey, 1);
        splitLine.setLayoutX(ViewConstants.screenwidth * 0.6);
        splitLine.setLayoutY(0.2 * ViewConstants.screenheight);
        this.view.getChildren().add(splitLine);
    }

    public void setupBgImage() {
        view.setBackground(new Background(new BackgroundImage(new Image("file:" + Constants.RESOURCESPATH + "\\gui\\backgroundImages\\newGameBg.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void setupScreenTitle() {
        screenTitle = new Label();
        NodePlacer.initLabel(screenTitle, "Select a Region", "Bell MT", FontWeight.MEDIUM, ColorConstants.neutralDarkGrey, 48, FontPosture.REGULAR);
        screenTitle.setAlignment(Pos.TOP_LEFT);
        NodePlacer.placeLabelRelativeToScreen(view, screenTitle, 0.20, 0.05, 0.33, 0.15, 66, 200);

    }

    public void setupRegionFlags(HashMap <String, Region> regions) {
        yList = regions.size() / xList;
        if ( yList == 0 ) {
            yList = 1;
        }

        int counter = 0;

        for (int y = 1; y < yList + 1; y++) {
            for (int x = 0; x < xList; x++) {
                if ( counter < regions.size() ) {
                    setupFlag(x, y, (Region) regions.values().toArray()[counter++]);
                }
            }
        }

    }

    private void setupFlag(int x, int y, Region region) {
        Flag flag = new Flag();
        //Border
        List <Coordinate> coordinatesCenterDiamonds1stBorder = new LinkedList <>();
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(1.0, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0.5, 1.0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));

        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(0, 0, 0)), new Stop(0.49, Color.rgb(0, 0, 0)), new Stop(0.5, Color.rgb(51, 51, 51)), new Stop(1, Color.rgb(51, 51, 51))};
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        flag.setBorder(ShapeDrawer.drawShape(view, Coordinate.Type.PIXELBASED, 190, 190, coordinatesCenterDiamonds1stBorder, gradient, Color.BLACK, 3));
        flag.getBorder().setMouseTransparent(true);

        //Flag
        List <Coordinate> coordinatesCenterDiamonds1stFlag = new LinkedList <>();
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));

        flag.setFlag(ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 170, 170, coordinatesCenterDiamonds1stFlag, "file:" + Constants.REGIONDATAPATH + region.getName() + "_medium.png", Color.BLACK, 0));
        flag.getFlag().setBlendMode(BlendMode.MULTIPLY);

        //Grunge
        List <Coordinate> coordinatesCenterDiamonds1stGrunge = new LinkedList <>();
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));

        flag.setGrunge(ShapeDrawer.drawImage(view, Coordinate.Type.PIXELBASED, 170, 170, coordinatesCenterDiamonds1stGrunge, "file:" + Constants.RESOURCESPATH + "\\data\\gameData\\images\\misc\\paper_flag_medium.png", Color.BLACK, 3));
        flag.getGrunge().setMouseTransparent(true);

        NodePlacer.placeNodeRelativeToScreen(view, flag.getBorder(), flag.getBorder().getWidth(), 0.25 * y, flag.getBorder().getWidth() / view.getWidth(), flag.getBorder().getHeight() / view.getHeight(), 180, 180);
        NodePlacer.placeNodeRelativeToScreen(view, flag.getGrunge(), 0, 0.25 * y, flag.getGrunge().getWidth() / view.getWidth(), flag.getGrunge().getHeight() / view.getHeight(), 170, 170);
        NodePlacer.placeNodeRelativeToScreen(view, flag.getFlag(), 0, 0.25 * y, flag.getFlag().getWidth() / view.getWidth(), flag.getFlag().getHeight() / view.getHeight(), 170, 170);

        // Text
        Label flagName = new Label();
        flag.setName(flagName);
        NodePlacer.initLabel(flag.getName(), region.getName(), "Bell MT", FontWeight.SEMI_BOLD, ColorConstants.neutralDarkGrey, 36, FontPosture.REGULAR);
        NodePlacer.placeLabelNextToObjectPct(view, flag.getFlag(), flag.getName(), 0, 0, 1, 1, flag.getFlag().getWidth(), 75, true);


        flag.getBorder().setLayoutX((view.getWidth() / 12) + (2 * x * (view.getWidth() / 12)) - (flag.getBorder().getWidth() / 3) - 2.5);
        flag.getGrunge().setLayoutX((view.getWidth() / 12) + (2 * x * (view.getWidth() / 12)) - (flag.getGrunge().getWidth() / 3));
        flag.getFlag().setLayoutX((view.getWidth() / 12) + (2 * x * (view.getWidth() / 12)) - (flag.getFlag().getWidth() / 3));
        flag.getBorder().setLayoutY(flag.getBorder().getLayoutY() - 10);

        flag.getName().setLayoutX(flag.getBorder().getLayoutX() + 25);
        flag.getName().setLayoutY(flag.getBorder().getHeight() + 15);
        flag.getName().setTextAlignment(TextAlignment.CENTER);

        flag.getFlag().toFront();
        flag.getFlag().setMouseTransparent(false);
        flag.getFlag().setScaleZ(20);
        flag.getFlag().setTranslateZ(20);
        flag.getFlag().setOpacity(1);


        flags.put(region.getAcronym(), flag);

    }

    void setupDetailsBox() {
        // TODO globaal maken en ophalen uit DAL
        ObservableList <String> details = FXCollections.observableArrayList("General Info");
        dropDownBox = new ComboBox <>(details);
        NodePlacer.placeControlRelativeToScreen(view, dropDownBox, 0.84, 0.025, 0, 0, 35, 150);
        dropDownBox.getSelectionModel().select(0);
        view.getStylesheets().clear();
        dropDownBox.getStylesheets().add("/gui/css/regionScreen.css");
        dropDownBox.getStyleClass().removeAll();
    }

    void setupContinueButton() {
        this.continueBtn = new Label();
        this.continueIcon = new ImageView(new Image("file:" + Constants.RESOURCESPATH + "\\gui\\icons\\general\\nextInactive.png"));

        NodePlacer.initLabel(this.continueBtn, "Continue", "Lucida Sans", FontWeight.BOLD, ColorConstants.neutralDarkGreyBlankedOut, 36, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.view, this.continueBtn, 0.725, 0.9, 0.2, 0.1, 50, 100);

        NodePlacer.initImagePixels(this.view, this.continueIcon, 36, 30, 30, 36);
        NodePlacer.placeNodeNextToObjectPct(this.view, this.continueBtn, this.continueIcon, 0.125, 0.02, 1, 1, 50,50, false);

        this.continueBtn.setScaleZ(50);
        this.continueBtn.setTranslateZ(50);

        handleContinueHovering();
    }

    private void handleContinueHovering() {
        this.continueBtn.toFront();
        this.continueBtn.setMouseTransparent(false);

        this.continueBtn.setOnMouseEntered(event -> {
            this.continueBtn.setTextFill(ColorConstants.primaryBlue);
            this.toggleActive(true);
            this.view.getScene().getRoot().setCursor(Cursor.HAND);
        });
        this.continueBtn.setOnMouseExited(event -> {
            this.continueBtn.setTextFill(ColorConstants.neutralDarkGreyBlankedOut);
            this.toggleActive(false);
            this.view.getScene().getRoot().setCursor(Cursor.DEFAULT);
        });
    }

    private void toggleActive(boolean active) {
        StringBuilder icon = new StringBuilder("file:" + Constants.RESOURCESPATH + "\\gui\\icons\\general\\next");
        if (active) {
            icon.append("Active.png");
        } else {
            icon.append("Inactive.png");
        }
        this.continueIcon.setImage(new Image(icon.toString()));
    }

    public Map <String, Flag> getFlags() {
        return flags;
    }

    public Flag getFlag(String name) {
        return this.flags.get(name);
    }

    public Label getContinueBtn() {
        return continueBtn;
    }

    public ImageView getContinueIcon() {
        return continueIcon;
    }
}
