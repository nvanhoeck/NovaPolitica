package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.RegionsConstants;

import java.util.HashMap;

public class ReadCountryRegions implements ReadCountryDataInterface {

    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;
    }

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException {
        if(countries.containsKey(data[RegionsConstants.country])) {
            Country c = countries.get(data[RegionsConstants.country]);
            c.getRegions().put(data[RegionsConstants.acronym], new Region(Integer.parseInt(data[RegionsConstants.id]), data[RegionsConstants.name], data[RegionsConstants.acronym], Long.parseLong(data[RegionsConstants.gdp]), Integer.parseInt(data[RegionsConstants.surface]), data[RegionsConstants.capital], listener.updateLanguage(Integer.parseInt(data[RegionsConstants.primaryLanguageid])), listener.updateLanguage(Integer.parseInt(data[RegionsConstants.secundaryLanguageid]))));
        }else {
            throw new ReadCountryException("Country "+ data[RegionsConstants.country]+" could not be found for Regions!");
        }
        return countries;
    }

}
