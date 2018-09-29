package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.regions.Language;

public interface CountryDataLanguageListener {
    void setLanguages();
    Language updateLanguage(int id);
}
