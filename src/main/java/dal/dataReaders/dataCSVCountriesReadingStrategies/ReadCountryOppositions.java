package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.Opposition;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadCountryOppositions implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadRegionException, ReadCountryException {
        if(countries.containsKey(data[5])){
            Country c = countries.get(data[5]);
            if(data[6].equals("NONE")){
                if(c.getParliament().getParliamentFormation().getId()==Integer.parseInt(data[7])){
                    c.getParliament().getParliamentFormation().getGovernmentAndOpposition().put(ParliamentTypes.OPPOSITION,new Government(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4])));
                }
            }else {
                if (c.getRegions().containsKey(data[6])){
                    Region r = c.getRegions().get(data[6]);
                    if (r.getRegionalParliament().getParliamentFormation().getId()==Integer.parseInt(data[7])){
                        r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().put(ParliamentTypes.OPPOSITION,new Government(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4])));
                    }
                }else throw new ReadRegionException("Region " + data[6] + " has not been found for Oppositions!");
            }
        }else {
            throw new ReadCountryException("Country " + data[5]  + " has not been found for Oppositions!");
        }
        return countries;
    }
}
