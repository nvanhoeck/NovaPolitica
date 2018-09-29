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

import java.util.HashMap;

public class ReadPartyTopicResult implements ReadCountryDataInterface {

    private PartyDataPoliticalTopicListener listener;

    public void addListener(PartyDataPoliticalTopicListener listener) {
        this.listener = listener;
    }

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {

        if(countries.containsKey(data[2])){
           Country country = countries.get(data[2]);
            if (data[3].equals("NONE")){
                Government government = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                PoliticalParty party = government.getParties().get(data[1]);
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
           else if(country.getRegions().containsKey(data[3])){
               Government government= (Government) country.getRegions().get(data[3]).getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
               if(government.getParties().containsKey(data[1])){
                   PoliticalParty party = government.getParties().get(data[1]);
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
                   throw new ReadPartyException("The party " + data[1] + " has not been found in Party Topic Result!");
               }
           }else {
               throw new ReadRegionException("Region " + data[3] + " has not been found in Party Topic Result!");
           }
        }else {
            throw new ReadCountryException("Country " + data[2] + "has not been found in Party Topic Result!");
        }
        return countries;
    }
}
