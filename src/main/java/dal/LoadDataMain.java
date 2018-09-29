package dal;


import dal.dataReaders.CSVCountriesReader;
import dal.dataReaders.CSVGeneralInfoReader;
import dal.dataReaders.CSVReader;
import dal.dataWriters.JsonCountriesWriter;
import dal.dataWriters.JsonDALWriter;
import dal.dataWriters.JsonGeneralDataWriter;
import org.apache.log4j.Logger;

public class LoadDataMain {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(LoadDataMain.class);

        JsonGeneralDataWriter jsonGeneralDataWriter = new JsonGeneralDataWriter();
        JsonCountriesWriter jsonCountriesWriter = new JsonCountriesWriter();
        CSVGeneralInfoReader csvGeneralInfoReader = new CSVGeneralInfoReader();
        CSVCountriesReader csvCountriesReader = new CSVCountriesReader();
        JsonDALWriter jsonWriter = new JsonDALWriter(jsonCountriesWriter,jsonGeneralDataWriter);
        CSVReader csvReader = new CSVReader(csvCountriesReader,csvGeneralInfoReader);
        DALTransferService dalTransferService = new DALTransferService(csvReader,jsonWriter);

        try {
            dalTransferService.transferCountries(System.getProperty("user.dir") + "\\src\\main\\resources\\data\\inputData\\static\\countries\\");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
          logger.error(e.getMessage());
        }
        dalTransferService.transferGeneralData(System.getProperty("user.dir") + "\\src\\main\\resources\\data\\inputData\\static\\generaldata\\");

    }
}
