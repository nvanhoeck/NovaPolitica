package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.Gender;
import bl.domain.countries.Country;
import bl.domain.countries.Monarch;
import bl.domain.countries.NoMonarch;
import dal.resources.Monarchies;
import tools.dataconstants.MonarchyConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ReadCountryMonarchies implements ReadCountryDataInterface {
    private HashMap<String,Monarchies>monarchs;

    public ReadCountryMonarchies() {
        monarchs = new HashMap<>();
    }


    @Override
    public HashMap<String,Country> readCSV(HashMap<String,Country> countries, String [] data) {
        countries.values().stream().filter(country -> !country.isHasMonarchy()).forEach(c -> c.setMonarch(new NoMonarch()));
        boolean isMarried = false;
        if (data[MonarchyConstants.married].toLowerCase().equals("y")) {
            isMarried = true;
        }
        Gender gender;
        if (data[MonarchyConstants.gender].toLowerCase().equals("f")) {
            gender = Gender.FEMALE;
        } else {
            gender = Gender.MALE;
        }
        SimpleDateFormat birthday = new SimpleDateFormat("dd/mm/yyyy");
        try {
            if (monarchs.containsKey(data[MonarchyConstants.countryAcronym])) {
                monarchs.get(data[MonarchyConstants.countryAcronym]).getMonarchs().put(Integer.valueOf(data[MonarchyConstants.priority]), new Monarch(Integer.parseInt(data[MonarchyConstants.id]), data[MonarchyConstants.name], data[MonarchyConstants.title], isMarried, gender, Integer.parseInt(data[MonarchyConstants.priority]), birthday.parse(data[MonarchyConstants.birthday])));
            } else {
                monarchs.put(data[MonarchyConstants.countryAcronym], new Monarchies(data[MonarchyConstants.countryAcronym], new HashMap<>()));
                monarchs.get(data[MonarchyConstants.countryAcronym]).getMonarchs().put(Integer.valueOf(data[MonarchyConstants.priority]), new Monarch(Integer.parseInt(data[MonarchyConstants.id]), data[MonarchyConstants.name], data[MonarchyConstants.title], isMarried, gender, Integer.parseInt(data[MonarchyConstants.priority]), birthday.parse(data[MonarchyConstants.birthday])));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        monarchs.forEach((s, monarchies) -> countries.get(s).setMonarch(monarchies.updateMonarchs()));
        return countries;
    }
}
