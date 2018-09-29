package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Centralisation;
import bl.domain.countries.Country;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.CentralisationConstants;

import java.util.HashMap;

public class ReadCountryCentralisations implements ReadCountryDataInterface {
    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener =listener;
    }

    @Override
    public HashMap<String,Country> readCSV( HashMap<String,Country> countries,String [] data) throws ReadCountryException {
        if(countries.containsKey(data[CentralisationConstants.country])) {
            countries.get(data[CentralisationConstants.country]).getStateForm().setCentralisation(new Centralisation(Integer.parseInt(data[CentralisationConstants.id]), data[CentralisationConstants.name], Double.parseDouble(data[CentralisationConstants.score])));
        }else {
            throw new ReadCountryException("Country " +data[CentralisationConstants.country] + " could not be found for Centralisations!");
        }
        return countries;
    }
}
