package dal;

import bl.domain.countries.Country;
import bl.domain.countries.Monarch;
import bl.domain.countries.MonarchType;
import bl.domain.parliaments.FormationInterface;
import bl.domain.parliaments.Government;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dal.jsonMappers.InterfaceSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitDal {


    public Map<String, Country> initCountries() throws FileNotFoundException {
        Map<String, Country> countries = new HashMap<>();
        String PATH = System.getProperty("user.dir") + "/src/main/resources/data/gameData/static/countries/";
        File directory = new File(PATH);
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            String countryName = file.getName();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(FormationInterface.class, new InterfaceSerializer<>(Government.class));
            builder.registerTypeAdapter(MonarchType.class, new InterfaceSerializer<>(Monarch.class));
            Gson gson = builder.create();
            FileReader reader = new FileReader(PATH + countryName);
            Country c = gson.fromJson(reader, Country.class);
            countries.put(c.getName(), c);
        }
        return countries;
    }
}
