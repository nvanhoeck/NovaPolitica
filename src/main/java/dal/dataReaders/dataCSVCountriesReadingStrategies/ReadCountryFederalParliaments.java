package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Parliament;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.ParliamentConstants;

import java.util.HashMap;

public class ReadCountryFederalParliaments implements ReadCountryDataInterface {


    public void addListener(CountryDataLanguageListener listener) {

    }

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException {
        if(data[ParliamentConstants.type].equals("F")){
            if(countries.containsKey(data[ParliamentConstants.acronym])) {
                countries.get(data[ParliamentConstants.acronym]).setParliament(new Parliament(Integer.parseInt(data[ParliamentConstants.id]), Integer.parseInt(data[ParliamentConstants.totalseats]), data[ParliamentConstants.name]));
            }else {
                throw new ReadCountryException("Country " + data[ParliamentConstants.acronym] + " could not be found for Federal Parliaments!");
            }
        }

        return countries;
    }
}
