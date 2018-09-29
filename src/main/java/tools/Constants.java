package tools;

import controllers.MainMenu.MainMenuController;

import java.io.File;
import java.net.URISyntaxException;

public class Constants {
    public static String RESOURCESPATH;

    static {
        try {
            RESOURCESPATH = String.valueOf(new File(MainMenuController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
