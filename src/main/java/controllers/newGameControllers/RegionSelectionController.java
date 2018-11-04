package controllers.newGameControllers;

import bl.domain.countries.Country;
import bl.domain.regions.Region;
import controllers.Controllers;
import controllers.animationHandlers.RegionSelectionAnimationHandler;
import controllers.newGameControllers.constants.DetailsScreens;
import controllers.newGameControllers.regionSelectionDetailsScreens.RegionDetails;
import controllers.newGameControllers.regionSelectionDetailsScreens.RegionDetailsGeneralInfo;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tools.ColorConstants;
import tools.OverallKeyController;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static controllers.newGameControllers.constants.DetailsScreens.GENERAL_INFO;

public class RegionSelectionController implements Controllers {

    private Country selectedCountry;
    private Region selectedRegion;

    private String currentDetailScreen = GENERAL_INFO;
    private RegionDetails regionDetailsScreen;

    @FXML
    Pane view;

    private RegionSelectionElementsBuilder elementsBuilder;
    private RegionSelectionAnimationHandler animationHandler;
    private String currentTab;


    @FXML
    @Override
    public void initialise() throws FileNotFoundException {
        this.elementsBuilder = new RegionSelectionElementsBuilder(this.view);
        this.animationHandler = new RegionSelectionAnimationHandler();
        elementsBuilder.setupBgImage();
        elementsBuilder.setupScreenTitle();
        elementsBuilder.setupRegionFlags(selectedCountry.getRegions());
        elementsBuilder.setupSplitLine();
        elementsBuilder.setupDiamonds();
        elementsBuilder.setupDetailsBox();
        elementsBuilder.setupContinueButton();
        Region firstRegion = (Region) this.selectedCountry.getRegions().values().toArray()[0];
        this.selectRegion(firstRegion.getAcronym(), this.selectedCountry.getRegions());
        addEventHandlers();
    }

    private void addEventHandlers() {
        for (Region region : this.selectedCountry.getRegions().values()) {
            this.handleFlags(this.elementsBuilder.getFlag(region.getAcronym()).getFlag(), region.getAcronym());
        }

        this.elementsBuilder.getContinueBtn().setOnMouseClicked(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO Next Screen
            }
        });

        this.elementsBuilder.getContinueIcon().setOnMouseClicked(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO Next Screen
            }
        });
    }

    private void addTabHandling(){
        for (Label tab : this.regionDetailsScreen.getTabs()) {
            tab.setMouseTransparent(false);
            tab.toFront();
            if ( !tab.getText().equals(currentTab) ) {
                tab.setOnMouseEntered(new EventHandler <MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        tab.setTextFill(ColorConstants.primaryBlue);
                        view.getScene().getRoot().setCursor(Cursor.HAND);
                    }
                });
                tab.setOnMouseExited(new EventHandler <MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        tab.setTextFill(ColorConstants.neutralDarkGreyBlankedOut);
                        view.getScene().getRoot().setCursor(Cursor.DEFAULT);
                    }
                });
                tab.setOnMouseClicked(new EventHandler <MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        currentTab = tab.getText();
                        showDetails();
                    }
                });
            }
        }
    }

    private void handleFlags(Node flagElement, String name) {
        flagElement.setOnMouseEntered(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getScene().getRoot().setCursor(Cursor.HAND);
            }
        });
        flagElement.setOnMouseExited(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                view.getScene().getRoot().setCursor(Cursor.DEFAULT);
            }
        });
        flagElement.setOnMouseClicked(new EventHandler <MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleFlagClicked(name);
            }
        });
    }

    private void handleFlagClicked(String name) {
        this.selectRegion(name, this.selectedCountry.getRegions());
    }

    public void selectRegion(String regionName, HashMap <String, Region> regions) {
        if ( null != this.selectedRegion && !this.selectedRegion.getAcronym().equals(regionName) ) {
            this.animationHandler.resetSelectedFlag();
        }
        if ( null == this.selectedRegion || !this.selectedRegion.getAcronym().equals(regionName) ) {
            this.selectedRegion = regions.get(regionName);
            this.animationHandler.highlightRegionFlag(this.elementsBuilder.getFlag(this.selectedRegion.getAcronym()));
            this.showDetails();
        }
    }

    private void showDetails() {
        if ( null != this.regionDetailsScreen ) {
            this.view.getChildren().remove(this.regionDetailsScreen.getContainer());
            if ( this.currentDetailScreen.equals(DetailsScreens.GENERAL_INFO) ) {
                this.showGeneralInfo();
            }
        } else {
            this.showGeneralInfo();
        }

        if ( null == this.currentTab ) {
            this.regionDetailsScreen.clickTab("History");
            this.currentTab = "History";
        }else {
            this.regionDetailsScreen.clickTab(this.currentTab);
        }
        this.addTabHandling();
        this.elementsBuilder.getContinueBtn().toFront();
        this.elementsBuilder.getContinueIcon().toFront();
    }

    private void showGeneralInfo() {
        this.regionDetailsScreen = new RegionDetailsGeneralInfo(this.view, this.selectedRegion);
        this.regionDetailsScreen.setupElements(0.625, 0.125, 0.35, 0.75);
    }

    @Override
    public void loadData(Object singleData) {
        this.selectedCountry = (Country) singleData;
    }

    /**
     * Kan nodig zijn om key combo's te capturen (zoals ctrl+shift+alt + E)
     *
     * @param keyEvent
     */
    @SuppressWarnings("JavaDoc")
    @FXML
    void keyPressed(KeyEvent keyEvent) {
        OverallKeyController.keyPressed(keyEvent);
    }


}
