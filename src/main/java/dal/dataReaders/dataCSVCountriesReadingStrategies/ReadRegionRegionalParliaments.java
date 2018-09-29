package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Parliament;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadRegionRegionalParliaments implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadRegionException {
        boolean regionFound = false;
        if (data[3].equals("R")) {
            for (Country c : countries.values()
                    ) {
                if (c.getRegions().containsKey(data[4])) {
                    regionFound = true;
                    c.getRegions().get(data[4]).setRegionalParliament(new Parliament(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2]));
                }
            }
        }
        if(!regionFound && data[3].equals("R")) {
            throw new ReadRegionException("Region " + data[4] + " has not been found for Regional Parliaments!");
        }
        return countries;
    }
}
