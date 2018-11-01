package controllers.regionSelection;

import bl.InitService;
import bl.domain.countries.Country;
import bl.domain.regions.Region;
import controllers.countryselection.CountrySelectionElementsBuilder;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import tools.OverallKeyController;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class RegionSelectionController {

    private Country selectedCountry;
    private Map<String, Region> regions;

    @FXML
    public void initialise() throws FileNotFoundException {
        setup();
        /*initService = new InitService();
        loadingGameData();
        this.elementsBuilder = new CountrySelectionElementsBuilder(this.view);
        elementsBuilder.setupBgImage();
        elementsBuilder.setupScreenTitle();
        elementsBuilder.setupContinentBox();
        elementsBuilder.setupContinentIcon();
        elementsBuilder.setupCountryFlags(this.countries);
        elementsBuilder.setupDetails();
        elementsBuilder.setupContinueButton();
        addEventHandlers();*/
    }

    private void setup() {
        this.regions = new HashMap<>();
    }

    public void passCountry(Country country) {
        this.selectedCountry = country;
        this.regions = (HashMap<String, Region>)this.selectedCountry.getRegions().clone();
        for (String s : regions.keySet()) {
            System.out.println(s);
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
