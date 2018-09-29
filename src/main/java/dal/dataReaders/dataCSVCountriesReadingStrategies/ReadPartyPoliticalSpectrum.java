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
import tools.dataconstants.PoliticalSpectrumConstants;

import java.util.HashMap;

public class ReadPartyPoliticalSpectrum implements ReadCountryDataInterface {

    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        if(countries.containsKey(data[PoliticalSpectrumConstants.country])){
            Country country = countries.get(data[PoliticalSpectrumConstants.country]);
            if(!data[PoliticalSpectrumConstants.region].equals("NONE")) {
                if(Integer.parseInt(data[PoliticalSpectrumConstants.populationideology])==0) {
                    Government g = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(g.getParties().containsKey(data[PoliticalSpectrumConstants.partyorideology])) {
                        PoliticalParty party = g.getParties().get(data[PoliticalSpectrumConstants.partyorideology]);
                        party.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[PoliticalSpectrumConstants.id])));
                    }
                }
                if(country.getRegions().containsKey(data[PoliticalSpectrumConstants.region])){
                    Region r = country.getRegions().get(data[PoliticalSpectrumConstants.region]);
                    Government gr = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if (gr.getParties().containsKey(data[PoliticalSpectrumConstants.partyorideology])) {
                        PoliticalParty party = gr.getParties().get(data[PoliticalSpectrumConstants.partyorideology]);
                            party.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[PoliticalSpectrumConstants.id])));
                    }
                }else
                    throw new ReadRegionException("Region " + data[PoliticalSpectrumConstants.region] + " has not been found for political spectrum!");
            }
            else {
                if(Integer.parseInt(data[PoliticalSpectrumConstants.populationideology])==0) {
                    Government g = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(g.getParties().containsKey(data[PoliticalSpectrumConstants.partyorideology])) {
                        PoliticalParty party = g.getParties().get(data[PoliticalSpectrumConstants.partyorideology]);
                        party.setPoliticalSpectrum(new PoliticalSpectrum(Integer.parseInt(data[PoliticalSpectrumConstants.id])));
                    }else {
                        throw new ReadPoliticalPartyException("Party " + data[PoliticalSpectrumConstants.partyorideology] + " has not been found for political spectrum!");
                    }
                }
            }
        }else {
            throw new ReadCountryException("Country " + data[PoliticalSpectrumConstants.country] + " has not been found for political spectrum!");
        }
        return countries;
    }
}
