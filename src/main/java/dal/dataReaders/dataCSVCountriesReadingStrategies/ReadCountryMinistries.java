package dal.dataReaders.dataCSVCountriesReadingStrategies;

import bl.domain.countries.Country;
import bl.domain.parliaments.Government;
import bl.domain.parliaments.Ministry;
import bl.domain.parliaments.ParliamentTypes;
import bl.domain.regions.Region;

import java.util.HashMap;

public class ReadCountryMinistries implements ReadCountryDataInterface {


    @Override
    public HashMap<String, Country> readCSV(HashMap<String, Country> countries, String[] data) {
        if(data[3].equals("Y")){
            for (Country country: countries.values()
                 ) {
                if(Integer.parseInt(data[4])==country.getParliament().getParliamentFormation().getId()) {
                    Government g = (Government) country.getParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                    if(Integer.parseInt(data[2])==0) {
                        g.getMinistries().put(Integer.parseInt(data[0]), new Ministry(Integer.parseInt(data[0]),data[1]));
                    } else {
                        //TODO verder uitwerken
                    }
                }
            }
        }else {
            for(Country country:countries.values()) {
                for (Region r: country.getRegions().values()){
                    if(Integer.parseInt(data[4])==r.getRegionalParliament().getParliamentFormation().getId()){
                        Government g = (Government) r.getRegionalParliament().getParliamentFormation().getGovernmentAndOpposition().get(ParliamentTypes.GOVERNMENT);
                        if(Integer.parseInt(data[2])==0){
                            g.getMinistries().put(Integer.parseInt(data[0]), new Ministry(Integer.parseInt(data[0]),data[1]));
                        }
                    }
                }
            }
        }
        return countries;
    }
}
