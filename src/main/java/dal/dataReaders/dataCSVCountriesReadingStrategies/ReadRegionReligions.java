package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.regions.Region;
import bl.domain.regions.Religion;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadRegionReligions implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if (countries.containsKey(data[3])) {
            Country c = countries.get(data[3]);
            if (c.getRegions().containsKey(data[4])) {
                Region r = c.getRegions().get(data[4]);
                r.getPopulation().getReligions().put(Integer.parseInt(data[0]), new Religion(Integer.parseInt(data[0]), data[1]));
                r.getPopulation().getReligions().get(Integer.parseInt(data[0])).setReligionAmountByPopulation(Long.parseLong(data[2]));
            } else {
                throw new ReadRegionException("Region " + data[4] + " could not be found in Religions!");
            }
        } else {
            throw new ReadCountryException("Country " + data[3] + " could not be found in Religions");
        }
        return countries;
    }
}
