package dal.dataReaders;

import dal.dataReaders.dataCSVGeneralInfoReadingStrategies.*;
import dal.dataWriters.GeneralDataListener;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CSVGeneralInfoReader implements CSVReaderStrategy {
    private List<ReadGeneralInfoDataInterface> generalInfoReaders;
    private List<String> dataFiles;
    private List<GeneralDataListener> listeners;
    private List<String []> dataCollection;

    public CSVGeneralInfoReader() {
        generalInfoReaders = new LinkedList<>();
        dataFiles = new LinkedList<>();
        listeners = new LinkedList<>();
    }

    public void addListener(GeneralDataListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void init() {
        generalInfoReaders.add(new ReadGeneralInfoCentralizations());
        dataFiles.add("centralisations.csv");
        generalInfoReaders.add(new ReadGeneralInfoHeadOfStateForsm());
        dataFiles.add("headofstateforms.csv");
        generalInfoReaders.add(new ReadGeneralInfoRulerships());
        dataFiles.add("rulerships.csv");
        generalInfoReaders.add(new ReadGeneralInfoStatePhilosiphies());
        dataFiles.add("statephilosophies.csv");
        generalInfoReaders.add(new ReadGeneralInfoReligions());
        dataFiles.add("religions.csv");
        generalInfoReaders.add(new ReadGeneralInfoLanguages());
        dataFiles.add("languages.csv");
        generalInfoReaders.add(new ReadGeneralInfoPoliticalSkills());
        dataFiles.add("politicalskills.csv");
        generalInfoReaders.add(new ReadGeneralInfoPopulationIdeologies());
        dataFiles.add("populationideologies.csv");
        generalInfoReaders.add(new ReadGeneralInfoPopulationIdeologiesTypes());
        dataFiles.add("populationideologiestypes.csv");
        generalInfoReaders.add(new ReadGeneralInfoPoliticalTopics());
        dataFiles.add("politicaltopics.csv");
    }

    @Override
    public void readData(String BASE_PATH) {
        for (int i = 0; i < generalInfoReaders.size(); i++) {
            dataCollection = new LinkedList<>();
            try{
             File file = new File(BASE_PATH + dataFiles.get(i));
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                int counter = 0;
                String line;
                line =bufferedReader.readLine();
                System.out.println(generalInfoReaders.get(i).getClass().getSimpleName());
                System.out.println("--------------------------------");
                while (line !=null) {
                    if (counter!=0) {
                        String data[] = line.split(";");
                        for (String datablock : data) {
                            System.out.print(String.format("%-15s\t",datablock));
                        }
                        System.out.println();
                        dataCollection.add(data);

                    }
                    line = bufferedReader.readLine();
                    counter++;
                }
                switch (i) {
                    case 0 : listeners.forEach(listener -> listener.writeData(dataCollection,"centralisations"));break;
                    case 1 : listeners.forEach(listener -> listener.writeData(dataCollection,"headofstates"));break;
                    case 2 : listeners.forEach(listener -> listener.writeData(dataCollection,"rulerships"));break;
                    case 3 : listeners.forEach(listener -> listener.writeData(dataCollection,"statephilosophies"));break;
                    case 4: listeners.forEach(listener -> listener.writeData(dataCollection,"religions"));break;
                    case 5: listeners.forEach(listener -> listener.writeData(dataCollection,"languages"));break;
                    case 6: listeners.forEach(listener -> listener.writeData(dataCollection,"politicalskills"));break;
                    case 7: listeners.forEach(listener -> listener.writeData(dataCollection,"populationideologies"));break;
                    case 8: listeners.forEach(listener -> listener.writeData(dataCollection,"populationideologytypes"));break;
                    case 9: listeners.forEach(listener -> listener.writeData(dataCollection,"politicaltopics"));break;


                    default:throw new Exception("Datafile for reading general info not found");
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
