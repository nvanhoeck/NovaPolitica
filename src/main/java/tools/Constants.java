package tools;

import controllers.mainmenu.MainMenuController;

import java.io.File;
import java.net.URISyntaxException;

public class Constants {
    public static String RESOURCESPATH;
    public static String COUNTRYDATAPATH = System.getProperty("user.dir") + "/src/main/resources/data/gameData/static/countries/";
    public static String GENERALDATAPATH = System.getProperty("user.dir") + "\\src\\main\\resources\\data\\inputData\\static\\generaldata\\";
    public static String COUNTRYINPUTDATAPATH = System.getProperty("user.dir") + "\\src\\main\\resources\\data\\inputData\\static\\countries\\";
    public static String VERSION = "1.0.0";

    static {
        try {
            RESOURCESPATH = String.valueOf(new File(MainMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
