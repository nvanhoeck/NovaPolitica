package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.ParliamentaryFormation;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryParliamentalFormations implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws ReadCountryException {
        if(countries.containsKey(data[4])) {
            if(data[5].equals("NONE")) {
                Country c = countries.get(data[4]);
                if (c.getParliament().getId() == Integer.parseInt(data[3])) {
                    c.getParliament().setParliamentFormation(new ParliamentaryFormation(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2])));
                }
            }
        }else {
            throw new ReadCountryException("Country " + data[4]  + " could not be found for Federal ParliamentalFormations!");
        }
        return countries;
    }
}
