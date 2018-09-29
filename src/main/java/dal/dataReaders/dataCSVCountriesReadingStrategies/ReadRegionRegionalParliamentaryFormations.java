package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.ParliamentaryFormation;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadParliamentException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadRegionRegionalParliamentaryFormations implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException, ReadRegionException, ReadParliamentException {
        if(countries.containsKey(data[4])){
            Country c = countries.get(data[4]);
            if(!data[5].equals("NONE")){
                if(c.getRegions().containsKey(data[5])){
                    Region r = c.getRegions().get(data[5]);
                    if(r.getRegionalParliament().getId()==Integer.parseInt(data[3])) {
                        r.getRegionalParliament().setParliamentFormation(new ParliamentaryFormation(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2])));
                    }else {
                        throw new ReadParliamentException("Parliament with id " + data[3] + " could not be found in region " + data[5] + " for Regional Parliament Formations!");
                    }
                }else {
                    throw new ReadRegionException("Region " + data[5] + " could not be found for Regional Parliament Formations");
                }
            }
        }else {
            throw new ReadCountryException("Country " + data[4] + " could not be found for Regional Parliament Formations!");
        }
        return countries;
    }
}
