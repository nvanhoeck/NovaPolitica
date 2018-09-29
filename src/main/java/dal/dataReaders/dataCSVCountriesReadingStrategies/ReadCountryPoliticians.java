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

import java.util.HashMap;

public class ReadCountryPoliticians implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if(countries.containsKey(data[13])) {
                Country c = countries.get(data[13]);
                if (data[14].equals("NONE")) {
                    Government g = (Government) c.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(g.getParties().containsKey(data[2])) {
                        PoliticalParty politicalParty = g.getParties().get(data[2]);
                        Gender gender;
                        if (data[4].equals("F")) {
                            gender = Gender.FEMALE;
                        } else {
                            gender = Gender.MALE;
                        }
                        politicalParty.getPartyMembers().put(Integer.parseInt(data[0]), new Politician(Integer.parseInt(data[0]), Long.parseLong(data[3]), gender, data[1], Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7])));
                    }else {
                        throw new ReadPoliticalPartyException("Party " + data[2] + " could not be found in politicians!");
                    }
                } else {
                    if(c.getRegions().containsKey(data[14])){
                        Region r = c.getRegions().get(data[14]);
                        Government g = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                            if (g.getParties().containsKey(data[2])) {
                                PoliticalParty politicalParty = g.getParties().get(data[2]);
                                Gender gender;
                                if (data[4].equals("F")) {
                                    gender = Gender.FEMALE;
                                } else {
                                    gender = Gender.MALE;
                                }
                                politicalParty.getPartyMembers().put(Integer.parseInt(data[0]), new Politician(Integer.parseInt(data[0]), Long.parseLong(data[3]), gender, data[1], Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7])));
                            } else {
                                throw new ReadPoliticalPartyException("Party " + data[2] + " could not be found in politicians!");
                            }
                    } else {
                        throw new ReadRegionException("Region " + data[14] + " could not be found in politicians!");
                    }
            }
        }else {
                throw new ReadCountryException("Country " + data[13] + " could not be found in politicians!");
        }
        return countries;
    }
}
