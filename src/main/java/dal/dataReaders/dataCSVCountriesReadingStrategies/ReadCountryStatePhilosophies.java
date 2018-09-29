package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.StatePhilosophy;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.StatePhilosophiesConstants;

import java.util.HashMap;

public class ReadCountryStatePhilosophies implements ReadCountryDataInterface {


    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[StatePhilosophiesConstants.country])) {
            countries.get(data[StatePhilosophiesConstants.country]).getGovernmentForm().setStatePhilosophy(new StatePhilosophy(Integer.parseInt(data[StatePhilosophiesConstants.id]), data[StatePhilosophiesConstants.name]));
        }else throw new ReadCountryException("Country " + data[StatePhilosophiesConstants.country] + " could not be found for State Philosophies!");
        return countries;
    }
}
