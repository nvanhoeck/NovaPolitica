package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PoliticalSpectrum;
import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.PoliticalSpectrumConstants;

import java.util.HashMap;

public class ReadRegionPoliticalSpectrum implements ReadCountryDataInterface {
    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if (countries.containsKey(data[PoliticalSpectrumConstants.country])) {
            Country country = countries.get(data[PoliticalSpectrumConstants.country]);
            if (country.getRegions().containsKey(data[PoliticalSpectrumConstants.region])) {
                Region r = country.getRegions().get(data[PoliticalSpectrumConstants.region]);
                if (Integer.parseInt(data[PoliticalSpectrumConstants.populationideology]) != 0) {
                    PopulationIdeology ideology = r.getPopulation().getPopulationIdeologies().get(Integer.parseInt(data[PoliticalSpectrumConstants.populationideology]));
                    try {
                        ideology.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[0])));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            throw new ReadCountryException("Country " + data[PoliticalSpectrumConstants.country] + " has not been found for political spectrum!");
        }
        return countries;
    }
}
