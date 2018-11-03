package controllers.newGameControllers.regionSelectionDetailsScreens;

import bl.domain.regions.Region;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import org.apache.log4j.Logger;
import tools.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RegionDetailsGeneralInfo implements RegionDetails {
    final org.apache.log4j.Logger logger = Logger.getLogger(RegionDetailsGeneralInfo.class);


    private Pane view;
    private Pane container;

    private Region region;

    private Label title;
    private Label tabTitle;
    private Label regionHistoryDescription;

    private Label historyTab;
    private Label generalInfoTab;

    public RegionDetailsGeneralInfo(Pane view, Region selectedRegion) {
        this.view = view;
        this.container = new Pane();
        this.region = selectedRegion;
    }

    @Override
    public void setupElements(double xPercent, double yPercent, double widthPercent, double heightPercent) {
        this.container.setMouseTransparent(false);
        this.container.setLayoutX(xPercent * ViewConstants.screenwidth);
        this.container.setLayoutY(yPercent * ViewConstants.screenheight);
        this.container.setPrefWidth(widthPercent * ViewConstants.screenwidth);
        this.container.setPrefHeight(heightPercent * ViewConstants.screenheight);

        this.view.getChildren().add(this.container);
    }

    private void placeBaseElements() {
        this.placeTitle();
        this.placeFlag();
        this.placeTabs();
    }

    private void placeTabs() {
        this.historyTab = new Label();
        NodePlacer.initLabel(historyTab, "History", "Lucida Sans", FontWeight.BOLD, ColorConstants.neutralDarkGreyBlankedOut, 24, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.container, historyTab, 0.0125, 0.66, this.container.getPrefWidth() / ViewConstants.screenwidth, 0.1, 50, 150);
        historyTab.setAlignment(Pos.CENTER_LEFT);
        historyTab.setTextAlignment(TextAlignment.LEFT);
        historyTab.setMouseTransparent(false);
        historyTab.toFront();

        this.generalInfoTab = new Label();
        NodePlacer.initLabel(generalInfoTab, "General Info", "Lucida Sans", FontWeight.BOLD, ColorConstants.neutralDarkGreyBlankedOut, 24, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.container, generalInfoTab, 0.125, 0.66, this.container.getPrefWidth() / ViewConstants.screenwidth / 2, 0.1, 50, 250);
        generalInfoTab.setAlignment(Pos.CENTER_RIGHT);
        generalInfoTab.setTextAlignment(TextAlignment.RIGHT);
        generalInfoTab.setMouseTransparent(false);
        generalInfoTab.toFront();
    }

    private void placeGeneralInfo() {
        this.tabTitle = new Label();
        NodePlacer.initLabel(tabTitle, "General Info", "Bell MT", FontWeight.SEMI_BOLD, ColorConstants.neutralDarkGrey, 30, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.container, tabTitle, 0.01, 0.15, 0, 0, 50, 350);
        this.placeGeneralInfoData();
    }

    private void placeGeneralInfoData() {
        for (int i = 0; i < 7; i++) {
            Label textTopic = new Label();
            Label textResult = new Label();
            String topic;
            String result;
            switch (i) {
                case 0:
                    topic = "Population:";
                    result = String.format("%,d", region.getPopulation().getAmount());
                    break;
                case 1:
                    topic = "Surface:";
                    result = String.format("%,d", region.getSurface());
                    break;
                case 2:
                    topic = "Government:";
                    result = String.valueOf(region.getRegionalParliament().getName());
                    break;
                case 3:
                    topic = "Main Religion:";
                    result = "TODO"; //TODO
                    break;
                case 4:
                    topic = "Primary Language:";
                    result = region.getPrimaryLanguage().getName();
                    break;
                case 5:
                    topic = "GDP:";
                    result = String.format("€ %,d", region.getGDP());
                    break;
                case 6:
                    topic = "GDP Per Capita:";
                    result = String.format("€ %,d", region.getGDP() / region.getPopulation().getAmount());
                    break;
                default:
                    topic = "Unknown:";
                    result = "Unknown";
            }
            NodePlacer.initLabel(textTopic, topic, "Lucida Sans", FontWeight.BOLD, ColorConstants.neutralDarkGrey, 16, FontPosture.REGULAR);
            NodePlacer.placeLabelRelativeToScreen(this.container, textTopic, 0.0125, 0.225 + (0.05 * i), this.container.getPrefWidth() / ViewConstants.screenwidth, 0.1, 50, 150);
            textTopic.setAlignment(Pos.CENTER_LEFT);
            textTopic.setTextAlignment(TextAlignment.LEFT);

            NodePlacer.initLabel(textResult, result, "Lucida Sans", FontWeight.BOLD, ColorConstants.neutralDarkGreyBlankedOut, 16, FontPosture.REGULAR);
            NodePlacer.placeLabelRelativeToScreen(this.container, textResult, 0.125, 0.225 + (0.05 * i), this.container.getPrefWidth() / ViewConstants.screenwidth / 2, 0.1, 50, 250);
            textResult.setAlignment(Pos.CENTER_RIGHT);
            textResult.setTextAlignment(TextAlignment.RIGHT);
        }
    }

    private void placeHistory() {

        this.tabTitle = new Label();
        NodePlacer.initLabel(tabTitle, "History", "Bell MT", FontWeight.SEMI_BOLD, ColorConstants.neutralDarkGrey, 30, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.container, tabTitle, 0.01, 0.15, 0, 0, 50, 350);

        this.regionHistoryDescription = new Label();
        NodePlacer.initLabel(this.regionHistoryDescription, this.region.getDesc(), "Lucida Sans", FontWeight.SEMI_BOLD, ColorConstants.neutralDarkGrey, 16, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.container, this.regionHistoryDescription, 0.01, 0.225, this.container.getPrefWidth() / 10 * 9 / ViewConstants.screenwidth, this.container.getHeight() / ViewConstants.screenheight, this.container.getPrefHeight(), 350);
        this.regionHistoryDescription.setWrapText(true);
        this.regionHistoryDescription.setTextAlignment(TextAlignment.JUSTIFY);
        this.regionHistoryDescription.setAlignment(Pos.TOP_LEFT);

    }

    private void placeFlag() {
        Flag flag = new Flag();
        //Border
        List <Coordinate> coordinatesCenterDiamonds1stBorder = new LinkedList <>();
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(1.0, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0.5, 1.0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stBorder.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));

        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(0, 0, 0)), new Stop(0.49, Color.rgb(0, 0, 0)), new Stop(0.5, Color.rgb(51, 51, 51)), new Stop(1, Color.rgb(51, 51, 51))};
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        flag.setBorder(ShapeDrawer.drawShape(container, Coordinate.Type.PIXELBASED, 165, 165, coordinatesCenterDiamonds1stBorder, gradient, Color.BLACK, 3));
        flag.getBorder().setMouseTransparent(true);

        //Flag
        List <Coordinate> coordinatesCenterDiamonds1stFlag = new LinkedList <>();
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stFlag.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));

        flag.setFlag(ShapeDrawer.drawImage(container, Coordinate.Type.PIXELBASED, 145, 145, coordinatesCenterDiamonds1stFlag, "file:" + Constants.REGIONDATAPATH + region.getName() + "_medium.png", Color.BLACK, 0));
        flag.getFlag().setBlendMode(BlendMode.MULTIPLY);

        //Grunge
        List <Coordinate> coordinatesCenterDiamonds1stGrunge = new LinkedList <>();
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 0, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(1, 0.5, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0.5, 1, Coordinate.Type.PERCENTBASED));
        coordinatesCenterDiamonds1stGrunge.add(new Coordinate(0, 0.5, Coordinate.Type.PERCENTBASED));

        flag.setGrunge(ShapeDrawer.drawImage(container, Coordinate.Type.PIXELBASED, 145, 145, coordinatesCenterDiamonds1stGrunge, "file:" + Constants.RESOURCESPATH + "\\data\\gameData\\images\\misc\\paper_flag_medium.png", Color.BLACK, 3));
        flag.getGrunge().setMouseTransparent(true);

        NodePlacer.placeNodeRelativeToScreen(container, flag.getBorder(), 0.2, 0, flag.getBorder().getWidth() / container.getWidth(), flag.getBorder().getHeight() / container.getHeight(), 180, 180);
        NodePlacer.placeNodeRelativeToScreen(container, flag.getGrunge(), 0.2, 0, flag.getGrunge().getWidth() / container.getWidth(), flag.getGrunge().getHeight() / container.getHeight(), 170, 170);
        NodePlacer.placeNodeRelativeToScreen(container, flag.getFlag(), 0.2, 0, flag.getFlag().getWidth() / container.getWidth(), flag.getFlag().getHeight() / container.getHeight(), 170, 170);

        flag.getBorder().setLayoutY(flag.getBorder().getLayoutY() - 10);
        flag.getBorder().setLayoutX(flag.getBorder().getLayoutX() - 10);

        flag.getFlag().toFront();
        flag.getFlag().setMouseTransparent(false);
        flag.getFlag().setScaleZ(20);
        flag.getFlag().setTranslateZ(20);
        flag.getFlag().setOpacity(1);
    }

    @Override
    public void placeTitle() {
        this.title = new Label();
        NodePlacer.initLabel(this.title, this.region.getName(), "Bell MT", FontWeight.BOLD, ColorConstants.neutralDarkGrey, 36, FontPosture.REGULAR);
        NodePlacer.placeLabelRelativeToScreen(this.container, this.title, 0.01, 0.07, 0, 0, 50, 350);
    }

    @Override
    public Pane getContainer() {
        return container;
    }

    @Override
    public List <Label> getTabs() {
        return new LinkedList <Label>((Arrays.asList(this.historyTab, this.generalInfoTab)));
    }

    @Override
    public void clickTab(String clickedTab) {
        this.container.getChildren().clear();

        this.placeBaseElements();

        for (Label tab : this.getTabs()) {
            tab.toFront();
            tab.setMouseTransparent(false);
        }

        switch (clickedTab) {
            case "History":
                this.placeHistory();
                this.historyTab.setTextFill(ColorConstants.neutralDarkGrey);
                break;
            case "General Info":
                this.placeGeneralInfo();
                this.generalInfoTab.setTextFill(ColorConstants.neutralDarkGrey);
                break;
        }
    }


}
