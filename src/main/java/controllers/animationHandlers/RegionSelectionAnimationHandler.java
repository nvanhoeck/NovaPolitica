package controllers.animationHandlers;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import tools.Flag;

public class RegionSelectionAnimationHandler {
    private ParallelTransition showSelected = new ParallelTransition();
    private ParallelTransition resetSelected = new ParallelTransition();
    Flag selectedFlag = new Flag();

    public void highlightRegionFlag(Flag flag) {
        this.selectedFlag = flag;
        this.showSelected = new ParallelTransition();

        enlargeFlag(this.selectedFlag.getFlag());

        enlargeFlag(this.selectedFlag.getBorder());

        enlargeFlag(this.selectedFlag.getGrunge());

        this.showSelected.play();
    }

    private void enlargeFlag(Node flagElement) {
        ScaleTransition resize = new ScaleTransition(Duration.seconds(0.1), flagElement);
        resize.setByX(0.15);
        resize.setByY(0.15);
        resize.setCycleCount(1);
        this.showSelected.getChildren().add(resize);
    }

    public void resetSelectedFlag() {
        this.resetSelected = new ParallelTransition();
        setFlagBackToNormal(this.selectedFlag.getFlag());
        setFlagBackToNormal(this.selectedFlag.getGrunge());
        setFlagBackToNormal(this.selectedFlag.getBorder());
        this.resetSelected.play();
    }

    private void setFlagBackToNormal(Node flagElement) {
        ScaleTransition resize = new ScaleTransition(Duration.seconds(0.1), flagElement);
        resize.setByX(-0.15);
        resize.setByY(-0.15);
        resize.setCycleCount(1);
        this.resetSelected.getChildren().add(resize);
    }

    public Flag getSelectedFlag() {
        return selectedFlag;
    }
}
