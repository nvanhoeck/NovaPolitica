package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PoliticalSpectrum;
import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadRegionPoliticalSpectrum implements ReadCountryDataInterface {
    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if (countries.containsKey(data[2])) {
            Country country = countries.get(data[2]);
            if (country.getRegions().containsKey(data[3])) {
                Region r = country.getRegions().get(data[3]);
                if (Integer.parseInt(data[4]) != 0) {
                    PopulationIdeology ideology = r.getPopulation().getPopulationIdeologies().get(Integer.parseInt(data[4]));
                    try {
                        ideology.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[0])));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            throw new ReadCountryException("Country " + data[2] + " has not been found for political spectrum!");
        }
        return countries;
    }
}
