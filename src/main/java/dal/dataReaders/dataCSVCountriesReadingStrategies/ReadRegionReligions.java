package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.regions.Region;
import bl.domain.regions.Religion;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.ReligionsConstants;

import java.util.HashMap;

public class ReadRegionReligions implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if (countries.containsKey(data[ReligionsConstants.country])) {
            Country c = countries.get(data[ReligionsConstants.country]);
            if (c.getRegions().containsKey(data[ReligionsConstants.region])) {
                Region r = c.getRegions().get(data[ReligionsConstants.region]);
                r.getPopulation().getReligions().put(Integer.parseInt(data[ReligionsConstants.id]), new Religion(Integer.parseInt(data[ReligionsConstants.id]), data[ReligionsConstants.name]));
                r.getPopulation().getReligions().get(Integer.parseInt(data[ReligionsConstants.id])).setReligionAmountByPopulation(Long.parseLong(data[ReligionsConstants.amountofpeople]));
            } else {
                throw new ReadRegionException("Region " + data[ReligionsConstants.region] + " could not be found in Religions!");
            }
        } else {
            throw new ReadCountryException("Country " + data[ReligionsConstants.country] + " could not be found in Religions");
        }
        return countries;
    }
}
