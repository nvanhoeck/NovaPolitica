package bl;

import bl.domain.countries.Country;
import dal.InitDal;

import java.io.FileNotFoundException;
import java.util.Map;

public class InitService {
    private InitDal initDal;

    public InitService() {
        this.initDal = new InitDal();
    }

    public Map<String, Country> initCountries() throws FileNotFoundException {
        return initDal.initCountries();
    }
}
