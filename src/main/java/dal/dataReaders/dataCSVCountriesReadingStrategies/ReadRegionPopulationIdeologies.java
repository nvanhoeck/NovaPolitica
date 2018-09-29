package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.PopulationIdeologiesConstants;

import java.util.HashMap;

public class ReadRegionPopulationIdeologies implements ReadCountryDataInterface {

    private CountryDataIdeologytypeListener listener;

    public void addListener(CountryDataIdeologytypeListener listener) {
        this.listener = listener;
    }


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        Country c = countries.get(data[PopulationIdeologiesConstants.country]);
        if (c == null) {
            throw new ReadCountryException("Country " + data[PopulationIdeologiesConstants.country] + " has not beend found!");
        } else {
            Region r = c.getRegions().get(data[PopulationIdeologiesConstants.region]);
            if (r == null) {
                throw new ReadRegionException("Region " +data[PopulationIdeologiesConstants.region] + "has not beend found!" );
            } else {
                r.getPopulation().getPopulationIdeologies().put(Integer.parseInt(data[PopulationIdeologiesConstants.id]),new PopulationIdeology(Integer.parseInt(data[PopulationIdeologiesConstants.id]),data[PopulationIdeologiesConstants.name],listener.updateIdeology(Integer.parseInt(data[PopulationIdeologiesConstants.ideologytype])),Long.parseLong(data[PopulationIdeologiesConstants.amount])));
            }
        }
        return countries;
    }
}
