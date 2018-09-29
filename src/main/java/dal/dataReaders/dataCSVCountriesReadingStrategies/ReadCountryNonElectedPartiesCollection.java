package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.politicalParties.PoliticalParty;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.NonElectedPartiesConstants;

import java.util.HashMap;
import java.util.LinkedList;

public class ReadCountryNonElectedPartiesCollection implements ReadCountryDataInterface{

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException, ReadRegionException {
        if(countries.containsKey(data[NonElectedPartiesConstants.country])){
            Country c = countries.get(data[NonElectedPartiesConstants.country]);
            if ("NONE".equals(data[NonElectedPartiesConstants.region])) {
                if(c.getParliament().getParliamentFormation().getElectedPaties().containsKey(data[NonElectedPartiesConstants.party])){
                   c.getParliament().getParliamentFormation().transferToNonElectedParties(data[NonElectedPartiesConstants.party]);
                }
            }else {
                if(c.getRegions().containsKey(data[NonElectedPartiesConstants.region])){
                    Region region = c.getRegions().get(data[NonElectedPartiesConstants.region]);
                    if(region.getRegionalParliament().getParliamentFormation().getElectedPaties().containsKey(data[NonElectedPartiesConstants.party])){
                        region.getRegionalParliament().getParliamentFormation().transferToNonElectedParties(data[NonElectedPartiesConstants.party]);
                    }
                }else {
                    throw new ReadRegionException("Region " + data[NonElectedPartiesConstants.region] + " has not been found in Non Elected Parties!");
                }
            }
        }else{
            throw new ReadCountryException("Country " + data[NonElectedPartiesConstants.country] + " has not been found in Non Elected Parties!");
        }
        return countries;
    }
}
