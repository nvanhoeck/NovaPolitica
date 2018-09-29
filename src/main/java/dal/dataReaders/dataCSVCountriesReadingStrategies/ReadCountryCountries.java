package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;

import java.util.HashMap;

public class ReadCountryCountries implements ReadCountryDataInterface {

    private CountryDataLanguageListener listener;


    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;
    }

    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) {

        boolean hasMonarchy = false;
        if (data[5].toLowerCase().equals("y")) {
            hasMonarchy = true;
        }
        Country country = new Country(data[0], data[1], data[2], data[3], data[4], hasMonarchy);
        countries.put(country.getAcronym(), country);

        return countries;
    }
}
