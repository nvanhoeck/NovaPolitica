package dal;

import dal.dataReaders.CSVReader;
import dal.dataWriters.JsonCountriesWriter;
import dal.dataWriters.JsonDALWriter;

public class DALTransferService {
    private CSVReader csvReader;
    private JsonDALWriter jsonWriter;

    public DALTransferService(CSVReader csvReader, JsonDALWriter jsonWriter) {
        this.csvReader = csvReader;
        this.jsonWriter = jsonWriter;
    }

    public void transferCountries(String PATH) throws Exception {
        csvReader.getCsvCountriesReader().init();
        csvReader.getCsvCountriesReader().addListener(getJsonWriter().getJsonCountriesWriter());
        csvReader.getCsvCountriesReader().readData(PATH);
    }

    public void transferGeneralData(String PATH) {
        csvReader.getCsvGeneralInfoReader().init();
        csvReader.getCsvGeneralInfoReader().addListener(getJsonWriter().getJsonGeneralDataWriter());
        csvReader.getCsvGeneralInfoReader().readData(PATH);
    }

    private CSVReader getCsvReader() {
        return csvReader;
    }

    private void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    private JsonDALWriter getJsonWriter() {
        return jsonWriter;
    }

    private void setJsonWriter(JsonDALWriter jsonWriter) {
        this.jsonWriter = jsonWriter;
    }
}
