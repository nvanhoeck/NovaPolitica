package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryRegions implements ReadCountryDataInterface {

    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;
    }

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException {
        if(countries.containsKey(data[9])) {
            Country c = countries.get(data[9]);
            c.getRegions().put(data[3], new Region(Integer.parseInt(data[0]), data[1], data[3], Long.parseLong(data[4]), Integer.parseInt(data[5]), data[6], listener.updateLanguage(Integer.parseInt(data[7])), listener.updateLanguage(Integer.parseInt(data[8]))));
        }else {
            throw new ReadCountryException("Country "+ data[9]+" could not be found for Regions!");
        }
        return countries;
    }

}
