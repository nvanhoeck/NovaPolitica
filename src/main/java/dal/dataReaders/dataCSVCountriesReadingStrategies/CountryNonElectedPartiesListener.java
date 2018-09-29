package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.politicalParties.PoliticalParty;

public interface CountryNonElectedPartiesListener {
    void setParties();
    PoliticalParty updatePoliticalParty(int id);
}
