package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.Rulership;
import bl.domain.countries.StateForm;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.RulershipsConstants;

import java.util.HashMap;

public class ReadCountryRulerships implements ReadCountryDataInterface {



    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[RulershipsConstants.country])) {
            countries.get(data[RulershipsConstants.country]).setStateForm(new StateForm());
            countries.get(data[RulershipsConstants.country]).getStateForm().setRulership(new Rulership(Integer.parseInt(data[RulershipsConstants.id]), data[RulershipsConstants.name], Double.parseDouble(data[RulershipsConstants.score])));
        }else {
            throw new ReadCountryException("Country " + data[RulershipsConstants.country] + " could not be found for Rulerships!");
        }

        return countries;
    }
}
