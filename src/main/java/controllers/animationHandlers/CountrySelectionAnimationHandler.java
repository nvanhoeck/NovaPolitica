package controllers.animationHandlers;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;
import tools.Coordinate;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Animation handler voor het selectCountry scherm.
 */
public class CountrySelectionAnimationHandler {
    private List<Node> selectedFlag = new LinkedList<>();
    private ParallelTransition showSelected;

    // TODO wordt deze gebruikt?
    public void pauseShowSelected() {
        this.showSelected.pause();
    }

    // TODO wordt deze gebruikt?
    public void continueShowSelected() {
        this.showSelected.play();
    }

    public ParallelTransition showSelected(List<Node> selectedFlag) {
        this.showSelected = new ParallelTransition();
        this.selectedFlag = selectedFlag;
        for (Node diamond : selectedFlag) {
            ScaleTransition resize = new ScaleTransition(Duration.seconds(0.1), diamond);
            resize.setByX(0.25);
            resize.setByY(0.25);
            resize.setCycleCount(1);
            this.showSelected.getChildren().add(resize);
            TranslateTransition move = new TranslateTransition(Duration.seconds(2), diamond);
            move.setByY(-10);
            move.setAutoReverse(true);
            move.setCycleCount(Timeline.INDEFINITE);
            this.showSelected.getChildren().add(move);
        }
        return this.showSelected;
    }

    public ParallelTransition resetShowSelected() {
        ParallelTransition resetTransition = new ParallelTransition();
        int counterCoordinates = 0;
        for (Node diamond : this.selectedFlag) {
            ScaleTransition resize = new ScaleTransition(Duration.seconds(0.1), diamond);
            resize.setByX(-0.25);
            resize.setByY(-0.25);
            resize.setCycleCount(1);
            resetTransition.getChildren().add(resize);
        }
        return resetTransition;
    }

    public ParallelTransition displayDetails(Set<Node> flags) {
        ParallelTransition moveCollectionTransition = new ParallelTransition();
        for (Node diamond : flags) {
            TranslateTransition move = new TranslateTransition(Duration.seconds(0.1), diamond);
            move.setByY(-33);
            move.setCycleCount(1);
            moveCollectionTransition.getChildren().add(move);
        }
        return moveCollectionTransition;
    }
}
