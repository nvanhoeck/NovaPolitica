package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.StatePhilosophy;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryStatePhilosophies implements ReadCountryDataInterface {


    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[2])) {
            countries.get(data[2]).getGovernmentForm().setStatePhilosophy(new StatePhilosophy(Integer.parseInt(data[0]), data[1]));
        }else throw new ReadCountryException("Country " + data[2] + " could not be found for State Philosophies!");
        return countries;
    }
}
