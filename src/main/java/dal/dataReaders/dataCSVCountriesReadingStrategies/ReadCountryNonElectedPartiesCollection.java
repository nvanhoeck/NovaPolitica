package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.politicalParties.PoliticalParty;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;
import java.util.LinkedList;

public class ReadCountryNonElectedPartiesCollection implements ReadCountryDataInterface{

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException, ReadRegionException {
        if(countries.containsKey(data[1])){
            Country c = countries.get(data[1]);
            if ("NONE".equals(data[2])) {
                if(c.getParliament().getParliamentFormation().getElectedPaties().containsKey(data[3])){
                   c.getParliament().getParliamentFormation().transferToNonElectedParties(data[3]);
                }
            }else {
                if(c.getRegions().containsKey(data[2])){
                    Region region = c.getRegions().get(data[2]);
                    if(region.getRegionalParliament().getParliamentFormation().getElectedPaties().containsKey(data[3])){
                        region.getRegionalParliament().getParliamentFormation().transferToNonElectedParties(data[3]);
                    }
                }else {
                    throw new ReadRegionException("Region " + data[2] + " has not been found in Non Elected Parties!");
                }
            }
        }else{
            throw new ReadCountryException("Country " + data[1] + " has not been found in Non Elected Parties!");
        }
        return countries;
    }
}
