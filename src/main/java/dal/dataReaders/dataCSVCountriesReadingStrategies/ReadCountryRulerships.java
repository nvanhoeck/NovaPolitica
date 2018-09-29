package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.Rulership;
import bl.domain.countries.StateForm;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryRulerships implements ReadCountryDataInterface {



    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[3])) {
            countries.get(data[3]).setStateForm(new StateForm());
            countries.get(data[3]).getStateForm().setRulership(new Rulership(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2])));
        }else {
            throw new ReadCountryException("Country " + data[3] + " could not be found for Rulerships!");
        }

        return countries;
    }
}
