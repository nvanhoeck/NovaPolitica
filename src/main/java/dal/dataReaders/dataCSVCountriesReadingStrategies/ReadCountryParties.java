package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.politicalParties.PoliticalParty;
import bl.domain.regions.Region;
import dal.exceptions.ReadPartyException;
import dal.exceptions.ReadCountryException;
import dal.exceptions.ReadRegionException;

import java.util.HashMap;

public class ReadCountryParties implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) throws Exception {
        Country c = countries.get(data[10]);
        if (c == null) {
            throw new ReadCountryException("Country could not be found");
        }else {
            if(data[11].equals("NONE")) {
             Government g = (Government) c.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
             if(!g.getParties().containsKey(data[0])){
                 g.getParties().put(data[2],new PoliticalParty(Integer.parseInt(data[0]),data[1],data[2],data[3],data[4],Long.parseLong(data[5]),Double.parseDouble(data[6]),Double.parseDouble(data[7]),Integer.parseInt(data[8]),Integer.parseInt(data[9])));
             }
            }else {
                Region r = c.getRegions().get(data[11]);
                if (r == null) {
                    throw new ReadRegionException("Region could not be found");
                } else {
                    Government g = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    try {
                        g.getParties().put(data[2], new PoliticalParty(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], Long.parseLong(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9])));
                    }catch (NullPointerException e) {
                        throw new ReadPartyException("Party " + data[2] + " could not be found!");
                    }
                }
            }
        }
        return countries;
    }
}
