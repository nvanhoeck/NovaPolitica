package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadRegionPopulationIdeologies implements ReadCountryDataInterface {

    private CountryDataIdeologytypeListener listener;

    public void addListener(CountryDataIdeologytypeListener listener) {
        this.listener = listener;
    }


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        Country c = countries.get(data[4]);
        if (c == null) {
            throw new ReadCountryException("Country " + data[4] + " has not beend found!");
        } else {
            Region r = c.getRegions().get(data[5]);
            if (r == null) {
                throw new ReadRegionException("Region " +data[5] + "has not beend found!" );
            } else {
                r.getPopulation().getPopulationIdeologies().put(Integer.parseInt(data[0]),new PopulationIdeology(Integer.parseInt(data[0]),data[1],listener.updateIdeology(Integer.parseInt(data[2])),Long.parseLong(data[3])));
            }
        }
        return countries;
    }
}
