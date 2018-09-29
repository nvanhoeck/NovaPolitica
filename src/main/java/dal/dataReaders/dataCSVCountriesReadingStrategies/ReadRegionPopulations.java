package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.regions.Population;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadRegionPopulations implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if(countries.containsKey(data[3])) {
            Country c = countries.get(data[3]);
            if (c.getRegions().containsKey(data[4])) {
                Region r = c.getRegions().get(data[4]);
                r.setPopulation(new Population(Integer.parseInt(data[0]), data[1], Long.parseLong(data[2])));
            } else {
                throw new ReadRegionException("Region "+ data[4] +" could not be found");
            }
        } else {
            throw new ReadCountryException("Country " + data[3] + " could not be found in Populations!");
        }
        return countries;
    }
}
