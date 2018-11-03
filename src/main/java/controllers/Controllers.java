package controllers;

import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface Controllers {
    @FXML void initialise() throws FileNotFoundException;
    void loadData(Object singleData);

}
