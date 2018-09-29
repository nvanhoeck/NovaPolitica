package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.countries.DemocraticValue;
import bl.domain.countries.GovernmentForm;
import dal.exceptions.ReadCountryException;

import java.util.HashMap;

public class ReadCountryDemocraticValues implements ReadCountryDataInterface {
    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;

    }

    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {

        DemocraticValue democraticValue = new DemocraticValue(Integer.parseInt(data[0]), Double.parseDouble(data[1]));
        if(countries.containsKey(data[2])) {
            countries.get(data[2]).setGovernmentForm(new GovernmentForm());
            countries.get(data[2]).getGovernmentForm().setDemocraticValue(democraticValue);
        }else {
            throw new ReadCountryException("Country " + data[2] + "could not be found for Democratic Values!");
        }

        return countries;
    }
}
