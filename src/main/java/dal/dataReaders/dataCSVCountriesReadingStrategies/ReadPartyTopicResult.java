package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PoliticalTopic;
import bl.domain.ideologies.TopicResult;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.politicalParties.PoliticalParty;
import dal.exceptions.ReadPartyException;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.TopicResultsConstants;

import java.util.HashMap;

public class ReadPartyTopicResult implements ReadCountryDataInterface {

    private PartyDataPoliticalTopicListener listener;

    public void addListener(PartyDataPoliticalTopicListener listener) {
        this.listener = listener;
    }

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {

        if(countries.containsKey(data[TopicResultsConstants.country])){
           Country country = countries.get(data[TopicResultsConstants.country]);
            if (data[TopicResultsConstants.region].equals("NONE")){
                Government government = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                PoliticalParty party = government.getParties().get(data[TopicResultsConstants.party]);
                for (int i = 4; i < data.length; i++) {
                    PoliticalTopic politicalTopic;
                    if(Integer.parseInt(data[i])>2) {
                        politicalTopic = listener.updatePoliticalTopic(i-3);
                    }else if (Integer.parseInt(data[i])<-2) {
                        politicalTopic = listener.updatePoliticalTopic((i+1)-3);
                    } else {
                        politicalTopic = listener.updatePoliticalTopic(0);
                    }
                    party.getPoliticalSpectrum().getPoliticalResult().put(i-3,new TopicResult(i-3,politicalTopic.getName(),Integer.parseInt(data[i]),politicalTopic));
                }
            }
           else if(country.getRegions().containsKey(data[TopicResultsConstants.region])){
               Government government= (Government) country.getRegions().get(data[TopicResultsConstants.region]).getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
               if(government.getParties().containsKey(data[TopicResultsConstants.party])){
                   PoliticalParty party = government.getParties().get(data[TopicResultsConstants.party]);
                   for (int i = 4; i < data.length; i++) {
                       PoliticalTopic politicalTopic;
                       if(Integer.parseInt(data[i])>2) {
                           politicalTopic = listener.updatePoliticalTopic(i- 3);
                       }else if (Integer.parseInt(data[i])<-2) {
                           politicalTopic = listener.updatePoliticalTopic(i+1-3);
                       } else {
                           politicalTopic = listener.updatePoliticalTopic(0);
                       }
                       party.getPoliticalSpectrum().getPoliticalResult().put(i-3,new TopicResult(i-3,politicalTopic.getName(),Integer.parseInt(data[i]),politicalTopic));
                   }
               }else {
                   throw new ReadPartyException("The party " + data[TopicResultsConstants.party] + " has not been found in Party Topic Result!");
               }
           }else {
               throw new ReadRegionException("Region " + data[TopicResultsConstants.region] + " has not been found in Party Topic Result!");
           }
        }else {
            throw new ReadCountryException("Country " + data[TopicResultsConstants.country] + "has not been found in Party Topic Result!");
        }
        return countries;
    }
}
