package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import tools.dataconstants.CountryConstants;

import java.util.HashMap;

public class ReadCountryCountries implements ReadCountryDataInterface {

    private CountryDataLanguageListener listener;


    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;
    }

    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) {

        boolean hasMonarchy = false;
        if (data[CountryConstants.hasMonarchy].toLowerCase().equals("y")) {
            hasMonarchy = true;
        }
        Country country = new Country(data[CountryConstants.name], data[CountryConstants.desc], data[CountryConstants.acronym], data[CountryConstants.denonym], data[CountryConstants.capital], hasMonarchy);
        countries.put(country.getAcronym(), country);

        return countries;
    }
}
