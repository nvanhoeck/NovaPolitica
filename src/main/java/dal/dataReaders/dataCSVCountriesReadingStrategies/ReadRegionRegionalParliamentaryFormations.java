package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.ParliamentaryFormation;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadParliamentException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.ParliamentFormationsConstants;

import java.util.HashMap;

public class ReadRegionRegionalParliamentaryFormations implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException, ReadRegionException, ReadParliamentException {
        if(countries.containsKey(data[ParliamentFormationsConstants.country])){
            Country c = countries.get(data[ParliamentFormationsConstants.country]);
            if(!data[ParliamentFormationsConstants.region].equals("NONE")){
                if(c.getRegions().containsKey(data[ParliamentFormationsConstants.region])){
                    Region r = c.getRegions().get(data[ParliamentFormationsConstants.region]);
                    if(r.getRegionalParliament().getId()==Integer.parseInt(data[ParliamentFormationsConstants.parliament])) {
                        r.getRegionalParliament().setParliamentFormation(new ParliamentaryFormation(Integer.parseInt(data[ParliamentFormationsConstants.id]), data[ParliamentFormationsConstants.name], Integer.parseInt(data[ParliamentFormationsConstants.amountofseats])));
                    }else {
                        throw new ReadParliamentException("Parliament with id " + data[ParliamentFormationsConstants.parliament] + " could not be found in region " + data[ParliamentFormationsConstants.region] + " for Regional Parliament Formations!");
                    }
                }else {
                    throw new ReadRegionException("Region " + data[ParliamentFormationsConstants.region] + " could not be found for Regional Parliament Formations");
                }
            }
        }else {
            throw new ReadCountryException("Country " + data[ParliamentFormationsConstants.country] + " could not be found for Regional Parliament Formations!");
        }
        return countries;
    }
}
