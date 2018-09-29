package dal.dataWriters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonGeneralDataWriter implements GeneralDataListener {

    private final String PATH = System.getProperty("user.dir")+"/src/main/resources/data/gameData/static/generaldata/";
    private File file;
    private Gson gson;
    private FileWriter fileWriter;

    public JsonGeneralDataWriter() {
        gson = new GsonBuilder().create();
    }

    @Override
    public void init() {
    }

    @Override
    public void writeData(List<String[]> data, String filename) {
        try {
            fileWriter = new FileWriter(PATH+filename+".json");
            String json = gson.toJson(data);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/*
    @Override
    public void writeCentralisations(String[] data) {
        try {
            fileWriter = new FileWriter(PATH+"centralisations.json");
            String json =
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeHeadOfStateForms(String[] data) {
        try {
            fileWriter = new FileWriter(PATH+"headofstateforms.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeRulerships(String[] data) {
        try {
            fileWriter = new FileWriter(PATH+"rulerships.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeStatePhilosophies(String[] data) {
        try {
            fileWriter = new FileWriter(PATH+"statephilosophies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
