package dal.dataWriters;

import java.util.List;

public interface GeneralDataListener {
    void init();
    void writeData(List<String[]> data, String filename);
    /*void writeCentralisations(String [] data);
    void writeHeadOfStateForms(String [] data);
    void writeRulerships(String [] data);
    void writeStatePhilosophies(String [] data);*/
}
