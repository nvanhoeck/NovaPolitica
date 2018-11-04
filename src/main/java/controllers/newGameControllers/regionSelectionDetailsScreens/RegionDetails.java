package controllers.newGameControllers.regionSelectionDetailsScreens;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public interface RegionDetails {
    void setupElements(double x, double y, double width, double height);

    void placeTitle();

    Pane getContainer();

    List<Label> getTabs();

    void clickTab(String clickedTab);
}
