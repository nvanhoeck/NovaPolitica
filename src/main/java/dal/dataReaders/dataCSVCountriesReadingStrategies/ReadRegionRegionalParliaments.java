package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Parliament;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.ParliamentConstants;

import java.util.HashMap;

public class ReadRegionRegionalParliaments implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadRegionException {
        boolean regionFound = false;
        if (data[ParliamentConstants.type].equals("R")) {
            for (Country c : countries.values()
                    ) {
                if (c.getRegions().containsKey(data[ParliamentConstants.acronym])) {
                    regionFound = true;
                    c.getRegions().get(data[ParliamentConstants.acronym]).setRegionalParliament(new Parliament(Integer.parseInt(data[ParliamentConstants.id]), Integer.parseInt(data[ParliamentConstants.totalseats]), data[ParliamentConstants.name]));
                }
            }
        }
        if(!regionFound && data[ParliamentConstants.type].equals("R")) {
            throw new ReadRegionException("Region " + data[ParliamentConstants.acronym] + " has not been found for Regional Parliaments!");
        }
        return countries;
    }
}
