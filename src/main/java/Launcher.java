import controllers.mainmenu.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import tools.ViewConstants;

import java.awt.*;

/**
 * De hoofd launcher van het spel. Hier wordt het spel gestart.
 */
public class Launcher extends Application {
    private String version;
    private String author;

    @Override
    public void start(Stage primaryStage) throws Exception {
        version = "1.0.0";
        author = "Niko Van Hoeck";
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ViewConstants.screenwidth = screenSize.getWidth();
        ViewConstants.screenheight = screenSize.getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/mainmenu.fxml"));
        Parent root = fxmlLoader.load();
        MainMenuController controller = fxmlLoader.getController();

        Scene scene  = new Scene(root,ViewConstants.screenwidth, ViewConstants.screenheight);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("Nova Politica - " + version);
        primaryStage.show();

        controller.initialise();
        scene.getRoot().requestFocus();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
