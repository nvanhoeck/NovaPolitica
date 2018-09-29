package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.HeadOfStateForm;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.HeadOfStateFormsConstants;

import java.util.HashMap;

public class ReadCountryHeadOfStateForms implements ReadCountryDataInterface {


    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[HeadOfStateFormsConstants.country])) {
            countries.get(data[HeadOfStateFormsConstants.country]).getGovernmentForm().setHeadOfStateForm(new HeadOfStateForm(Integer.parseInt(data[HeadOfStateFormsConstants.id]), data[HeadOfStateFormsConstants.name]));
        }else {
            throw new ReadCountryException("Country " + data[HeadOfStateFormsConstants.country] + " could not be found for Head of State Forms!");
        }
        return countries;
    }
}
