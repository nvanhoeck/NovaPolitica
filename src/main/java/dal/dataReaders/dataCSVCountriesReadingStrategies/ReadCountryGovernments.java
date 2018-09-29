package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.GovernmentsConstants;

import java.util.HashMap;

public class ReadCountryGovernments implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException, ReadRegionException {
        if(countries.containsKey(data[GovernmentsConstants.country])){
            Country c = countries.get(data[GovernmentsConstants.country]);
            if(data[GovernmentsConstants.region].equals("NONE")){
                if(c.getParliament().getParliamentFormation().getId()==Integer.parseInt(data[GovernmentsConstants.parliamentformation])){
                    c.getParliament().getParliamentFormation().getGovernmentAndOpposition().put(ParliamentTypes.GOVERNMENT,new Government(Integer.parseInt(data[GovernmentsConstants.id]),data[GovernmentsConstants.name],Integer.parseInt(data[GovernmentsConstants.popularity]),Integer.parseInt(data[GovernmentsConstants.trust]),Integer.parseInt(data[GovernmentsConstants.amountofseats])));
                }
            }else {
                if (c.getRegions().containsKey(data[GovernmentsConstants.region])){
                    Region r = c.getRegions().get(data[GovernmentsConstants.region]);
                    if (r.getRegionalParliament().getParliamentFormation().getId()==Integer.parseInt(data[GovernmentsConstants.parliamentformation])) {
                        r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().put(ParliamentTypes.GOVERNMENT, new Government(Integer.parseInt(data[GovernmentsConstants.id]), data[GovernmentsConstants.name], Integer.parseInt(data[GovernmentsConstants.popularity]), Integer.parseInt(data[GovernmentsConstants.trust]), Integer.parseInt(data[GovernmentsConstants.amountofseats])));
                    }
                }else throw new ReadRegionException("Region " + data[GovernmentsConstants.region] + " has not been found for governments!");
            }
        }else {
            throw new ReadCountryException("Country " + data[GovernmentsConstants.country]  + " has not been found for Governments!");
        }
        return countries;
    }
}
