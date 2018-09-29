package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.ParliamentaryFormation;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.ParliamentFormationsConstants;

import java.util.HashMap;

public class ReadCountryParliamentalFormations implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException {
        if(countries.containsKey(data[ParliamentFormationsConstants.country])) {
            if(data[ParliamentFormationsConstants.region].equals("NONE")) {
                Country c = countries.get(data[ParliamentFormationsConstants.country]);
                if (c.getParliament().getId() == Integer.parseInt(data[ParliamentFormationsConstants.parliament])) {
                    c.getParliament().setParliamentFormation(new ParliamentaryFormation(Integer.parseInt(data[ParliamentFormationsConstants.id]), data[ParliamentFormationsConstants.name], Integer.parseInt(data[ParliamentFormationsConstants.amountofseats])));
                }
            }
        }else {
            throw new ReadCountryException("Country " + data[ParliamentFormationsConstants.country]  + " could not be found for Federal ParliamentalFormations!");
        }
        return countries;
    }
}
