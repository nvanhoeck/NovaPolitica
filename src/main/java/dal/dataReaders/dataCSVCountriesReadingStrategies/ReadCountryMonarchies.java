package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.Gender;
import bl.domain.countries.Country;
import bl.domain.countries.Monarch;
import bl.domain.countries.NoMonarch;
import dal.resources.Monarchies;

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
        if (data[3].toLowerCase().equals("y")) {
            isMarried = true;
        }
        Gender gender;
        if (data[5].toLowerCase().equals("f")) {
            gender = Gender.FEMALE;
        } else {
            gender = Gender.MALE;
        }
        SimpleDateFormat birthday = new SimpleDateFormat("dd/mm/yyyy");
        try {
            if (monarchs.containsKey(data[7])) {
                monarchs.get(data[7]).getMonarchs().put(Integer.valueOf(data[6]), new Monarch(Integer.parseInt(data[0]), data[1], data[2], isMarried, gender, Integer.parseInt(data[6]), birthday.parse(data[8])));
            } else {
                monarchs.put(data[7], new Monarchies(data[7], new HashMap<>()));
                monarchs.get(data[7]).getMonarchs().put(Integer.valueOf(data[6]), new Monarch(Integer.parseInt(data[0]), data[1], data[2], isMarried, gender, Integer.parseInt(data[6]), birthday.parse(data[8])));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        monarchs.forEach((s, monarchies) -> countries.get(s).setMonarch(monarchies.updateMonarchs()));
        return countries;
    }
}
