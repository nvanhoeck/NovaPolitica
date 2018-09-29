package dal.dataWriters;

import bl.domain.countries.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonCountriesWriter implements CountryDataWriterListener {
    private String PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\data\\gameData\\static\\countries\\";

    public JsonCountriesWriter() {
        System.out.println(PATH);
    }

    @Override
    public void writeCountriesToJson(List<Country> countries) {
        for (Country c :
                countries) {
            try {
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(countries.get(0));
                FileWriter fileWriter = new FileWriter(PATH + c.getName()+".json");
                fileWriter.write(json);
                fileWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
