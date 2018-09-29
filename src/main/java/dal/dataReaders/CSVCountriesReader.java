package dal.dataReaders;

import bl.domain.countries.Country;
import dal.dataReaders.dataCSVCountriesReadingStrategies.*;
import dal.dataWriters.CountryDataWriterListener;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CSVCountriesReader implements CSVReaderStrategy {
    private List<ReadCountryDataInterface> dataReaders;
    private List<String> dataFiles;
    private HashMap<String,Country> countries;
    private CountryDataWriterListener listener;
    private ReadLanguages languagesReader;
    private ReadIdeologyTypes ideologyTypesReader;
    private ReadPoliticalTopics politicalTopicsReader;

    public CSVCountriesReader() {
        this.dataReaders = new LinkedList<>();
        this.countries = new HashMap<>();
        dataFiles = new LinkedList<>();
        languagesReader = new ReadLanguages();
        ideologyTypesReader = new ReadIdeologyTypes();
        politicalTopicsReader = new ReadPoliticalTopics();
    }

    public void addListener(CountryDataWriterListener listener) {
        this.listener = listener;
    }

    @Override
    public void init() {
        this.languagesReader.setLanguages();
        this.ideologyTypesReader.setIdeologyTypes();
        this.politicalTopicsReader.setPoliticalTopics();
        this.dataReaders.add(new ReadCountryCountries());
        dataFiles.add("countries.csv");
        this.dataReaders.add(new ReadCountryMonarchies());
        dataFiles.add("monarchies.csv");
        this.dataReaders.add(new ReadCountryDemocraticValues());
        dataFiles.add("democraticvalues.csv");
        this.dataReaders.add(new ReadCountryHeadOfStateForms());
        dataFiles.add("headofstateforms.csv");
        this.dataReaders.add(new ReadCountryStatePhilosophies());
        dataFiles.add("statephilosophies.csv");
        this.dataReaders.add(new ReadCountryGovernmentForms());
        dataFiles.add("governmentform.csv");
        this.dataReaders.add(new ReadCountryRulerships());
        dataFiles.add("rulerships.csv");
        this.dataReaders.add(new ReadCountryCentralisations());
        dataFiles.add("centralisations.csv");
        this.dataReaders.add(new ReadCountryStateForms());
        dataFiles.add("stateforms.csv");
        dataReaders.add(new ReadCountryRegions());
        ReadCountryRegions countryRegionsReader = (ReadCountryRegions) dataReaders.get(dataReaders.size()-1);
        countryRegionsReader.addListener(languagesReader);
        dataFiles.add("regions.csv");
        this.dataReaders.add(new ReadCountryFederalParliaments());
        dataFiles.add("parliaments.csv");
        this.dataReaders.add(new ReadRegionRegionalParliaments());
        dataFiles.add("parliaments.csv");
        this.dataReaders.add(new ReadCountryParliamentalFormations());
        this.dataFiles.add("parliamentaryformations.csv");
        dataReaders.add(new ReadRegionRegionalParliamentaryFormations());
        this.dataFiles.add("parliamentaryformations.csv");
        dataReaders.add(new ReadRegionPopulations());
        dataFiles.add("populations.csv");
        dataReaders.add(new ReadRegionReligions());
        dataFiles.add("religions.csv");
        dataReaders.add(new ReadCountryOppositions());
        dataFiles.add("oppositions.csv");
        dataReaders.add(new ReadCountryGovernments());
        dataFiles.add("governments.csv");
        dataReaders.add(new ReadCountryParties());
        dataFiles.add("parties.csv");
        dataReaders.add(new ReadCountryPoliticians());
        dataFiles.add("politicians.csv");
        dataReaders.add(new ReadPartyPartyGoalTypes());
        dataFiles.add("partygoaltypes.csv");
        dataReaders.add(new ReadPartyPartyGoal());
        dataFiles.add("partygoals.csv");
        dataReaders.add(new ReadPartyPoliticalSkills());
        dataFiles.add("politicalskills.csv");
        dataReaders.add(new ReadPartyPoliticianPoliticalSkills());
        dataFiles.add("politicianpoliticalskills.csv");
        dataReaders.add(new ReadRegionPopulationIdeologies());
        ReadRegionPopulationIdeologies populationIdeologiesReader = (ReadRegionPopulationIdeologies) dataReaders.get(dataReaders.size()-1);
        populationIdeologiesReader.addListener(ideologyTypesReader);
        dataFiles.add("populationideologies.csv");
        dataReaders.add(new ReadPartyPoliticalSpectrum());
        dataFiles.add("politicalspectrums.csv");
        dataReaders.add(new ReadRegionPoliticalSpectrum());
        dataFiles.add("politicalspectrums.csv");
        dataReaders.add(new ReadPartyTopicResult());
        ReadPartyTopicResult partyTopicResult = (ReadPartyTopicResult) dataReaders.get(dataReaders.size()-1);
        partyTopicResult.addListener(politicalTopicsReader);
        dataFiles.add("topicresults.csv");
        dataReaders.add(new ReadCountryNonElectedPartiesCollection());
        dataFiles.add("nonelectedparties.csv");
        //TODO Fractions
        //TODO Ministeries worden pas op het einde gedaan
        dataReaders.add(new ReadCountryMinistries());
        dataFiles.add("ministries.csv");


    }


    @Override
    public void readData(String BASE_PATH) throws Exception {
        for (int i = 0; i < dataReaders.size(); i++) {
            try {
                File file = new File(BASE_PATH + dataFiles.get(i));
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                int counter = 0;
                String line;
                line = bufferedReader.readLine();
                System.out.println(dataReaders.get(i).getClass().getSimpleName());
                System.out.println("--------------------------------");
                while (line !=null) {
                    if (counter!=0) {
                        String data[] = line.split(";");
                        for (String datablock : data) {
                            System.out.print(String.format("%-15s\t",datablock));
                        }
                        System.out.println();
                        //dataReaders.get(i).addListener(this);
                        try {
                            countries = dataReaders.get(i).readCSV(countries, data);
                        }catch (Exception e) {
                            //throw new Exception(e);
                            //System.out.println("ERROR! " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    line = bufferedReader.readLine();
                    counter++;
                }
                System.out.println();
            } catch (Exception e) {
              throw new Exception(e);
            }
        }
        listener.writeCountriesToJson(new ArrayList<>(countries.values()));
    }
}
