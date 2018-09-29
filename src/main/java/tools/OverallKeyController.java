package tools;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class OverallKeyController {
    private Pane view;

    private static KeyCodeCombination exitCombo = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN,KeyCombination.ALT_DOWN,KeyCombination.SHIFT_DOWN);


    public OverallKeyController(Pane view) {
        this.view = view;
    }

    public static void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ESCAPE)){
            keyEvent.consume();
        }

        if(exitCombo.match(keyEvent)) {
            System.exit(0);
        }
    }
}
