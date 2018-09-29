package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.regions.Language;
import tools.Constants;
import tools.dataconstants.LanguageConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadLanguages implements CountryDataLanguageListener {
    private HashMap<Integer,Language> languages;

    public ReadLanguages() {
        languages = new HashMap<>();
    }

    @Override
    public void setLanguages() {
        try {
            File file = new File(Constants.COUNTRYINPUTDATAPATH + "languages.csv");
            System.out.println("Loading languages from " + file.getAbsolutePath());
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int counter = 0;
            String line;
            ReadLanguages languageReader = new ReadLanguages();
            line = bufferedReader.readLine();
            while (line !=null) {
                if (counter!=0) {
                    String data[] = line.split(";");
                    languages.put(Integer.parseInt(data[LanguageConstants.ID]),new Language(Integer.parseInt(data[LanguageConstants.ID]),data[LanguageConstants.NAME],data[LanguageConstants.ADJECTIF]));
                }
                line = bufferedReader.readLine();
                counter++;
            }
            System.out.println("Languages initialised");
            System.out.println("---------------------");
            languages.values().forEach(language -> System.out.println(language.getName()));

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Language updateLanguage(int id) {
        return languages.get(id);
    }
}
