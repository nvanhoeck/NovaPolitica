package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Language;
import tools.Constants;
import tools.dataconstants.IdeologyTypesConstants;

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
            File file = new File(Constants.COUNTRYINPUTDATAPATH + "populationideologiestypes.csv");
            System.out.println("Loading ideologytypes from " + file.getAbsolutePath());
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int counter = 0;
            String line;
            line = bufferedReader.readLine();
            while (line !=null) {
                if (counter!=0) {
                    String data[] = line.split(";");
                    switch (Integer.parseInt(data[IdeologyTypesConstants.ID])){
                        case 1:ideologyTypes.put(Integer.parseInt((data[IdeologyTypesConstants.ID])), PopulationIdeology.IdeologyType.PRIMARY);break;
                        case 2:ideologyTypes.put(Integer.parseInt((data[IdeologyTypesConstants.ID])), PopulationIdeology.IdeologyType.SECUNDARY);break;
                        case 3:ideologyTypes.put(Integer.parseInt((data[IdeologyTypesConstants.ID])), PopulationIdeology.IdeologyType.ADDITIONAL);break;
                        default:ideologyTypes.put(Integer.parseInt(data[IdeologyTypesConstants.ID]), PopulationIdeology.IdeologyType.ADDITIONAL);
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
