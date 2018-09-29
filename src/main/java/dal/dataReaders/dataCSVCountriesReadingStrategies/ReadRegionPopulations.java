package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.regions.Population;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.RegionPopulationsConstants;

import java.util.HashMap;

public class ReadRegionPopulations implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if(countries.containsKey(data[RegionPopulationsConstants.country])) {
            Country c = countries.get(data[RegionPopulationsConstants.country]);
            if (c.getRegions().containsKey(data[RegionPopulationsConstants.region])) {
                Region r = c.getRegions().get(data[RegionPopulationsConstants.region]);
                r.setPopulation(new Population(Integer.parseInt(data[RegionPopulationsConstants.id]), data[RegionPopulationsConstants.name], Long.parseLong(data[RegionPopulationsConstants.amount])));
            } else {
                throw new ReadRegionException("Region "+ data[RegionPopulationsConstants.region] +" could not be found");
            }
        } else {
            throw new ReadCountryException("Country " + data[RegionPopulationsConstants.country] + " could not be found in Populations!");
        }
        return countries;
    }
}
