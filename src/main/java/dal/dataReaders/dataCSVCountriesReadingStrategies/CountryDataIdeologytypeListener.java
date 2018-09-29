package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.ideologies.PopulationIdeology;
import bl.domain.regions.Language;

public interface CountryDataIdeologytypeListener {
    void setIdeologyTypes();
    PopulationIdeology.IdeologyType updateIdeology(int id);
}
