package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Centralisation;
import bl.domain.countries.Country;
import bl.domain.countries.Rulership;
import bl.domain.countries.StateForm;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.StateFormsConstants;

import java.util.HashMap;

public class ReadCountryStateForms implements ReadCountryDataInterface {

    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[StateFormsConstants.country])) {
            Country c = countries.get(data[StateFormsConstants.country]);
            Rulership rulership = c.getStateForm().getRulership();
            Centralisation centralisation = c.getStateForm().getCentralisation();
            c.setStateForm(new StateForm(Integer.parseInt(data[StateFormsConstants.id]), data[StateFormsConstants.country], centralisation, rulership));
        }else {
         throw new ReadCountryException("Country " + data[StateFormsConstants.country] + " could not be found for State Forms!");
        }
        return countries;
    }
}
