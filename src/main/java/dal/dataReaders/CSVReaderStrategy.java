package dal.dataReaders;

import java.io.IOException;

public interface CSVReaderStrategy {
    void init();
    void readData(String BASE_PATH) throws Exception;
}
