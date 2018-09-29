package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadIdeologyTypes implements CountryDataIdeologytypeListener {
    private HashMap<Integer,PopulationIdeology.IdeologyType> ideologyTypes;

    public ReadIdeologyTypes() {
        ideologyTypes = new HashMap<>();
    }

    @Override
    public void setIdeologyTypes() {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/data/inputData/static/countries/populationideologiestypes.csv");
            System.out.println("Loading ideologytypes from " + file.getAbsolutePath());
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int counter = 0;
            String line;
            line = bufferedReader.readLine();
            while (line !=null) {
                if (counter!=0) {
                    String data[] = line.split(";");
                    switch (Integer.parseInt(data[0])){
                        case 1:ideologyTypes.put(Integer.parseInt((data[0])), PopulationIdeology.IdeologyType.PRIMARY);break;
                        case 2:ideologyTypes.put(Integer.parseInt((data[0])), PopulationIdeology.IdeologyType.SECUNDARY);break;
                        case 3:ideologyTypes.put(Integer.parseInt((data[0])), PopulationIdeology.IdeologyType.ADDITIONAL);break;
                        default:ideologyTypes.put(Integer.parseInt(data[0]), PopulationIdeology.IdeologyType.ADDITIONAL);
                    }
                }
                line = bufferedReader.readLine();
                counter++;
            }
            System.out.println("Ideologytypes initialised");
            System.out.println("---------------------");
            ideologyTypes.values().forEach(type -> System.out.println(type.getName()));

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PopulationIdeology.IdeologyType updateIdeology(int id) {
        return ideologyTypes.get(id);
    }
}
