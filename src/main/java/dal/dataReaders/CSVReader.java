package dal.dataReaders;

public class CSVReader {
    private CSVCountriesReader csvCountriesReader;
    private CSVGeneralInfoReader csvGeneralInfoReader;

    public CSVReader(CSVCountriesReader csvCountriesReader, CSVGeneralInfoReader csvGeneralInfoReader) {
        this.csvCountriesReader = csvCountriesReader;
        this.csvGeneralInfoReader = csvGeneralInfoReader;
    }

    public CSVCountriesReader getCsvCountriesReader() {
        return csvCountriesReader;
    }

    public void setCsvCountriesReader(CSVCountriesReader csvCountriesReader) {
        this.csvCountriesReader = csvCountriesReader;
    }

    public CSVGeneralInfoReader getCsvGeneralInfoReader() {
        return csvGeneralInfoReader;
    }

    public void setCsvGeneralInfoReader(CSVGeneralInfoReader csvGeneralInfoReader) {
        this.csvGeneralInfoReader = csvGeneralInfoReader;
    }
}
