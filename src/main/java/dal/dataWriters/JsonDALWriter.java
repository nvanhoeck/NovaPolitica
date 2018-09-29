package dal.dataWriters;

public class JsonDALWriter{
    private JsonCountriesWriter jsonCountriesWriter;
    private JsonGeneralDataWriter jsonGeneralDataWriter;

    public JsonDALWriter(JsonCountriesWriter jsonCountriesWriter, JsonGeneralDataWriter jsonGeneralDataWriter) {
        this.jsonCountriesWriter = jsonCountriesWriter;
        this.jsonGeneralDataWriter = jsonGeneralDataWriter;
    }

    public JsonCountriesWriter getJsonCountriesWriter() {
        return jsonCountriesWriter;
    }

    public void setJsonCountriesWriter(JsonCountriesWriter jsonCountriesWriter) {
        this.jsonCountriesWriter = jsonCountriesWriter;
    }

    public JsonGeneralDataWriter getJsonGeneralDataWriter() {
        return jsonGeneralDataWriter;
    }

    public void setJsonGeneralDataWriter(JsonGeneralDataWriter jsonGeneralDataWriter) {
        this.jsonGeneralDataWriter = jsonGeneralDataWriter;
    }
}
