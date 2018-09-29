package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.DurationInWeeks;
import bl.domain.countries.*;
import dal.exceptions.ReadCountryException;
import tools.dataconstants.GovernmentFormsConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ReadCountryGovernmentForms implements ReadCountryDataInterface {
    private CountryDataLanguageListener listener;

    public void addListener(CountryDataLanguageListener listener) {
        this.listener = listener;

    }

    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) throws ReadCountryException {
        if(countries.containsKey(data[GovernmentFormsConstants.country])) {
            Country c = countries.get(data[GovernmentFormsConstants.country]);
            DemocraticValue democraticValue = c.getGovernmentForm().getDemocraticValue();
            HeadOfStateForm headOfStateForm = c.getGovernmentForm().getHeadOfStateForm();
            StatePhilosophy statePhilosophy = c.getGovernmentForm().getStatePhilosophy();
            DurationInWeeks durationInWeeksFederal;
            switch (Integer.parseInt(data[GovernmentFormsConstants.federalruletimeinyears])) {
                case 1:
                    durationInWeeksFederal = DurationInWeeks.ONE_YEAR;
                    break;
                case 2:
                    durationInWeeksFederal = DurationInWeeks.TWO_YEARS;
                    break;
                case 3:
                    durationInWeeksFederal = DurationInWeeks.THREE_YEARS;
                    break;
                case 4:
                    durationInWeeksFederal = DurationInWeeks.FOUR_YEARS;
                    break;
                case 5:
                    durationInWeeksFederal = DurationInWeeks.FIVE_YEARS;
                    break;
                case 10:
                    durationInWeeksFederal = DurationInWeeks.TEN_YEARS;
                    break;
                default:
                    durationInWeeksFederal = DurationInWeeks.FOUR_YEARS;

            }
            DurationInWeeks durationInWeeksRegional;
            switch (Integer.parseInt(data[GovernmentFormsConstants.regionalruletimeinyears])) {
                case 1:
                    durationInWeeksRegional = DurationInWeeks.ONE_YEAR;
                    break;
                case 2:
                    durationInWeeksRegional = DurationInWeeks.TWO_YEARS;
                    break;
                case 3:
                    durationInWeeksRegional = DurationInWeeks.THREE_YEARS;
                    break;
                case 4:
                    durationInWeeksRegional = DurationInWeeks.FOUR_YEARS;
                    break;
                case 5:
                    durationInWeeksRegional = DurationInWeeks.FIVE_YEARS;
                    break;
                case 10:
                    durationInWeeksRegional = DurationInWeeks.TEN_YEARS;
                    break;
                default:
                    durationInWeeksRegional = DurationInWeeks.FOUR_YEARS;

            }
            SimpleDateFormat regionalDate = new SimpleDateFormat("dd/mm/yyyy");
            SimpleDateFormat federalDate = new SimpleDateFormat("dd/mm/yyyy");


            try {
                c.setGovernmentForm(new GovernmentForm(Integer.parseInt(data[GovernmentFormsConstants.id]), data[GovernmentFormsConstants.name], durationInWeeksFederal, durationInWeeksRegional, federalDate.parse(data[GovernmentFormsConstants.datefederalelections]), federalDate.parse(data[GovernmentFormsConstants.dateregionalelections]), democraticValue, headOfStateForm, statePhilosophy));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            throw new ReadCountryException("Country " + data[GovernmentFormsConstants.country] + " could not be found for Government Forms!");
        }
        return countries;
    }
}
