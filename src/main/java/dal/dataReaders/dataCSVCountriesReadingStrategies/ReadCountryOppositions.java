package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.Opposition;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.OppositionConstants;

import java.util.HashMap;

public class ReadCountryOppositions implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadRegionException, ReadCountryException {
        if(countries.containsKey(data[OppositionConstants.country])){
            Country c = countries.get(data[OppositionConstants.country]);
            if(data[OppositionConstants.region].equals("NONE")){
                if(c.getParliament().getParliamentFormation().getId()==Integer.parseInt(data[OppositionConstants.parliamentformation])){
                    c.getParliament().getParliamentFormation().getGovernmentAndOpposition().put(ParliamentTypes.OPPOSITION,new Government(Integer.parseInt(data[OppositionConstants.id]),data[OppositionConstants.name],Integer.parseInt(data[OppositionConstants.popularity]),Integer.parseInt(data[OppositionConstants.trust]),Integer.parseInt(data[OppositionConstants.amountofseats])));
                }
            }else {
                if (c.getRegions().containsKey(data[OppositionConstants.region])){
                    Region r = c.getRegions().get(data[OppositionConstants.region]);
                    if (r.getRegionalParliament().getParliamentFormation().getId()==Integer.parseInt(data[OppositionConstants.parliamentformation])) {
                        r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().put(ParliamentTypes.OPPOSITION, new Government(Integer.parseInt(data[OppositionConstants.id]), data[OppositionConstants.name], Integer.parseInt(data[OppositionConstants.popularity]), Integer.parseInt(data[OppositionConstants.trust]), Integer.parseInt(data[OppositionConstants.amountofseats])));
                    }
                    }else throw new ReadRegionException("Region " + data[OppositionConstants.region] + " has not been found for Oppositions!");
            }
        }else {
            throw new ReadCountryException("Country " + data[OppositionConstants.country]  + " has not been found for Oppositions!");
        }
        return countries;
    }
}
