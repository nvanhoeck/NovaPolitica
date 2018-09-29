package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;

import java.util.HashMap;

public interface ReadCountryDataInterface {
    HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws Exception;
}
