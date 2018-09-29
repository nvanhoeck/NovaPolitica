package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Centralisation;
import bl.domain.countries.Country;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryCentralisations implements ReadCountryDataInterface {
    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener =listener;
    }

    @Override
    public HashMap<String,Country> readCSV( HashMap<String,Country> countries,String [] data) throws ReadCountryException {
        if(countries.containsKey(data[data.length-1])) {
            countries.get(data[data.length - 1]).getStateForm().setCentralisation(new Centralisation(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2])));
        }else {
            throw new ReadCountryException("Country " +data[data.length-1] + " could not be found for Centralisations!");
        }
        return countries;
    }
}
