package controllers.countryselection;

import bl.InitService;
import bl.domain.countries.Country;
import controllers.animationHandlers.CountrySelectionAnimationHandler;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import tools.Coordinate;
import tools.OverallKeyController;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Hierin gaat de speler zijn land kiezen om een politieke partij op te richten.
 * Hij kan hiervoor filteren op verschillende continenten.
 */
public class CountrySelectionController {

    //DATA
    private InitService initService;
    private HashMap<String, Country> countries;
    private CountrySelectionElementsBuilder elementsBuilder;

    @FXML
    Pane view;

   

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
        initService = new InitService();
        loadingGameData();
        this.elementsBuilder = new CountrySelectionElementsBuilder(this.view);
        elementsBuilder.setupBgImage();
        elementsBuilder.setupScreenTitle();
        elementsBuilder.setupContinentBox();
        elementsBuilder.setupContinentIcon();
        elementsBuilder.setupCountryFlags(this.countries);
        elementsBuilder.setupDetails();
        addEventHandlers();
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
        this.elementsBuilder.getFlags().keySet().forEach(flag -> {
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

        for (Map.Entry<Node, String> flagEntry : this.elementsBuilder.getFlags().entrySet()) {
            Node flag = flagEntry.getKey();
            flag.setOnMouseClicked(event -> {
                if (isEnlarged && !selectedFlagName.equals(flagEntry.getValue())) {
                    showSelected.stop();
                    ParallelTransition reset = animationHandler.resetShowSelected();
                    reset.play();
                    //Todo checken of er nog iets moet gebeuren
                }else if (!isEnlarged) {
                    //Verplaatst alle vlaggen naar omhoog zodat de details te voorschijn komen
                    ParallelTransition showDetails = animationHandler.displayDetails(this.elementsBuilder.getFlags().keySet());
                    showDetails.play();
                    showDetails.setOnFinished(event1 -> {
                        isEnlarged = true;
                        int flagCounter = 0;
                        for (Node flag1 : selectedFlag) {
                            flag1.setLayoutX(originalCoordinates.get(flagCounter).getX());
                            flag1.setLayoutY(originalCoordinates.get(flagCounter++).getY());
                        }
                        selectedFlag = new LinkedList<>();
                        for (Map.Entry<Node, String> entry : this.elementsBuilder.getFlags().entrySet()) {
                            if (Objects.equals(flagEntry.getValue(), entry.getValue())) {
                                selectedFlag.add(entry.getKey());
                            }
                        }
                        selectedFlagName = flagEntry.getValue();
                        this.elementsBuilder.getCountryName().setText(countries.get(selectedFlagName).getName().toUpperCase());
                        this.elementsBuilder.getCountryName().setVisible(true);
                        this.elementsBuilder.getCountryName().setLayoutX((view.getWidth()/2)-((this.elementsBuilder.getCountryName().getText().length()*this.elementsBuilder.getCountryName().getFont().getSize()))/3);
                        this.elementsBuilder.getCountryDesc().setText(countries.get(selectedFlagName).getDesc());
                        this.elementsBuilder.getCountryDesc().setVisible(true);
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
