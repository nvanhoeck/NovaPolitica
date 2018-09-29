package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Parliament;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryFederalParliaments implements ReadCountryDataInterface {


    public void addListener(CountryDataLanguageListener listener) {

    }

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException {
        if(data[3].equals("F")){
            if(countries.containsKey(data[4])) {
                countries.get(data[4]).setParliament(new Parliament(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2]));
            }else {
                throw new ReadCountryException("Country " + data[4] + " could not be found for Federal Parliaments!");
            }
        }

        return countries;
    }
}
