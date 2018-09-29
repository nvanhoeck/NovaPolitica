package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.ideologies.PoliticalTopic;
import bl.domain.ideologies.PopulationIdeology;

public interface PartyDataPoliticalTopicListener {
    void setPoliticalTopics();
    PoliticalTopic updatePoliticalTopic(int id);
}
