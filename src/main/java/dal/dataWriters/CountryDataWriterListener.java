package dal.dataWriters;

import bl.domain.countries.Country;

import java.util.List;

public interface CountryDataWriterListener {
    void writeCountriesToJson(List<Country> countries);
}
