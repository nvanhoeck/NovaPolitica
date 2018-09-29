package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.DemocraticValue;
import bl.domain.countries.GovernmentForm;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.DemocraticValuesConstants;

import java.util.HashMap;

public class ReadCountryDemocraticValues implements ReadCountryDataInterface {
    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;

    }

    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {

        DemocraticValue democraticValue = new DemocraticValue(Integer.parseInt(data[DemocraticValuesConstants.id]), Double.parseDouble(data[DemocraticValuesConstants.score]));
        if(countries.containsKey(data[DemocraticValuesConstants.country])) {
            countries.get(data[DemocraticValuesConstants.country]).setGovernmentForm(new GovernmentForm());
            countries.get(data[DemocraticValuesConstants.country]).getGovernmentForm().setDemocraticValue(democraticValue);
        }else {
            throw new ReadCountryException("Country " + data[DemocraticValuesConstants.country] + "could not be found for Democratic Values!");
        }

        return countries;
    }
}
