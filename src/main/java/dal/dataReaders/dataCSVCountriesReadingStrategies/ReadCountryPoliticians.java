package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.Gender;
import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.politicalParties.PoliticalParty;
import bl.domain.politicalParties.Politician;
import bl.domain.regions.Population;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadPoliticalPartyException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.PoliticiansConstants;

import java.util.HashMap;

public class ReadCountryPoliticians implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if(data.length > 1 && countries.containsKey(data[PoliticiansConstants.country])) {
                Country c = countries.get(data[PoliticiansConstants.country]);
                if (data[PoliticiansConstants.region].equals("NONE")) {
                    Government g = (Government) c.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(g.getParties().containsKey(data[PoliticiansConstants.party])) {
                        PoliticalParty politicalParty = g.getParties().get(data[PoliticiansConstants.party]);
                        Gender gender;
                        if (data[PoliticiansConstants.gender].equals("F")) {
                            gender = Gender.FEMALE;
                        } else {
                            gender = Gender.MALE;
                        }
                        politicalParty.getPartyMembers().put(Integer.parseInt(data[PoliticiansConstants.id]), new Politician(Integer.parseInt(data[PoliticiansConstants.id]), Long.parseLong(data[PoliticiansConstants.salary]), gender, data[PoliticiansConstants.name], Double.parseDouble(data[PoliticiansConstants.popularity]), Double.parseDouble(data[PoliticiansConstants.trust]), Double.parseDouble(data[PoliticiansConstants.happiness])));
                    }else {
                        throw new ReadPoliticalPartyException("Party " + data[PoliticiansConstants.party] + " could not be found in politicians!");
                    }
                } else {
                    if(c.getRegions().containsKey(data[PoliticiansConstants.region])){
                        Region r = c.getRegions().get(data[PoliticiansConstants.region]);
                        Government g = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                            if (g.getParties().containsKey(data[PoliticiansConstants.party])) {
                                PoliticalParty politicalParty = g.getParties().get(data[PoliticiansConstants.party]);
                                Gender gender;
                                if (data[PoliticiansConstants.gender].equals("F")) {
                                    gender = Gender.FEMALE;
                                } else {
                                    gender = Gender.MALE;
                                }
                                politicalParty.getPartyMembers().put(Integer.parseInt(data[PoliticiansConstants.id]), new Politician(Integer.parseInt(data[PoliticiansConstants.id]), Long.parseLong(data[PoliticiansConstants.salary]), gender, data[PoliticiansConstants.name], Double.parseDouble(data[PoliticiansConstants.popularity]), Double.parseDouble(data[PoliticiansConstants.trust]), Double.parseDouble(data[PoliticiansConstants.happiness])));
                            } else {
                                throw new ReadPoliticalPartyException("Party " + data[PoliticiansConstants.party] + " could not be found in politicians!");
                            }
                    } else {
                        throw new ReadRegionException("Region " + data[PoliticiansConstants.region] + " could not be found in politicians!");
                    }
            }
        }else if (data.length > 1){
                throw new ReadCountryException("Country " + data[PoliticiansConstants.country] + " could not be found in politicians!");
        }
        return countries;
    }
}
