package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.politicalParties.PoliticalParty;
import bl.domain.regions.Region;
import dal.exceptions.ReadPartyException;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;
import tools.dataconstants.PartiesConstants;

import java.util.HashMap;

public class ReadCountryParties implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        Country c = countries.get(data[PartiesConstants.country]);
        if (c == null) {
            throw new ReadCountryException("Country could not be found");
        }else {
            if(data[PartiesConstants.region].equals("NONE")) {
             Government g = (Government) c.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
             if(!g.getParties().containsKey(data[PartiesConstants.id])){
                 g.getParties().put(data[PartiesConstants.shortname],new PoliticalParty(Integer.parseInt(data[PartiesConstants.id]),data[PartiesConstants.name],data[PartiesConstants.shortname],data[PartiesConstants.primaryColor],data[PartiesConstants.secundaryColor],Long.parseLong(data[PartiesConstants.budget]),Double.parseDouble(data[PartiesConstants.popularity]),Double.parseDouble(data[PartiesConstants.trust]),Integer.parseInt(data[PartiesConstants.amountOfFederalSeats]),Integer.parseInt(data[PartiesConstants.amountOfRegionalSeats])));
             }
            }else {
                Region r = c.getRegions().get(data[PartiesConstants.region]);
                if (r == null) {
                    throw new ReadRegionException("Region could not be found");
                } else {
                    Government g = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    try {
                        g.getParties().put(data[PartiesConstants.shortname], new PoliticalParty(Integer.parseInt(data[PartiesConstants.id]),data[PartiesConstants.name],data[PartiesConstants.shortname],data[PartiesConstants.primaryColor],data[PartiesConstants.secundaryColor],Long.parseLong(data[PartiesConstants.budget]),Double.parseDouble(data[PartiesConstants.popularity]),Double.parseDouble(data[PartiesConstants.trust]),Integer.parseInt(data[PartiesConstants.amountOfFederalSeats]),Integer.parseInt(data[PartiesConstants.amountOfRegionalSeats])));
                    }catch (NullPointerException e) {
                        throw new ReadPartyException("Party " + data[PartiesConstants.shortname] + " could not be found!");
                    }
                }
            }
        }
        return countries;
    }
}
