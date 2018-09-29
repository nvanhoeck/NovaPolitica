package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.ideologies.PoliticalSpectrum;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.politicalParties.PoliticalParty;
import bl.domain.regions.Region;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadPoliticalPartyException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadPartyPoliticalSpectrum implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if(countries.containsKey(data[2])){
            Country country = countries.get(data[2]);
            if(!data[3].equals("NONE")) {
                if(Integer.parseInt(data[4])==0) {
                    Government g = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(g.getParties().containsKey(data[1])) {
                        PoliticalParty party = g.getParties().get(data[1]);
                        party.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[0])));
                    }
                }
                if(country.getRegions().containsKey(data[3])){
                    Region r = country.getRegions().get(data[3]);
                    Government gr = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if (gr.getParties().containsKey(data[1])) {
                        PoliticalParty party = gr.getParties().get(data[1]);
                            party.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[0])));
                    }
                }else
                    throw new ReadRegionException("Region " + data[3] + " has not been found for political spectrum!");
            }
            else {
                if(Integer.parseInt(data[4])==0) {
                    Government g = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(g.getParties().containsKey(data[1])) {
                        PoliticalParty party = g.getParties().get(data[1]);
                        party.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[0])));
                    }else {
                        throw new ReadPoliticalPartyException("Party " + data[1] + " has not been found for political spectrum!");
                    }
                }
            }
        }else {
            throw new ReadCountryException("Country " + data[2] + " has not been found for political spectrum!");
        }
        return countries;
    }
}
