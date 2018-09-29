package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PoliticalTopic;
import tools.Constants;
import tools.dataconstants.PoliticalTopicsConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadPoliticalTopics implements PartyDataPoliticalTopicListener {
    private HashMap<Integer,PoliticalTopic> politicalTopics;

    public ReadPoliticalTopics() {
        this.politicalTopics = new HashMap<>();
    }


    @Override
    public void setPoliticalTopics() {
        try {
            File file = new File(Constants.COUNTRYINPUTDATAPATH + "politicaltopics.csv");
            System.out.println("Loading political topics from " + file.getAbsolutePath());
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int counter = 0;
            String line;
            ReadLanguages languageReader = new ReadLanguages();
            line = bufferedReader.readLine();
            while (line !=null) {
                if (counter!=0) {
                    String data[] = line.split(";");
                    if(politicalTopics.containsKey(Integer.parseInt(data[PoliticalTopicsConstants.ID]))) {
                        politicalTopics.put(Integer.parseInt(data[PoliticalTopicsConstants.ID]), new PoliticalTopic(Integer.parseInt(data[PoliticalTopicsConstants.ID]), data[PoliticalTopicsConstants.NAME]/*TODO Genereert overflow error! ,politicalTopics.get(Integer.parseInt(data[2]))*/));
                        //TODO check Andere methode?Geeft overflow error! politicalTopics.get(Integer.parseInt(data[2])).setOpposition(politicalTopics.get(Integer.parseInt(data[0])));
                    }else {
                        politicalTopics.put(Integer.parseInt(data[PoliticalTopicsConstants.ID]), new PoliticalTopic(Integer.parseInt(data[PoliticalTopicsConstants.ID]), data[PoliticalTopicsConstants.NAME]));
                    }
                }
                line = bufferedReader.readLine();
                counter++;
            }
            System.out.println("Political Topics initialised");
            System.out.println("---------------------");
            politicalTopics.values().forEach(topic -> System.out.println(topic.getName()));

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PoliticalTopic updatePoliticalTopic(int id) {
        return this.politicalTopics.get(id);
    }
}
