package tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class Flag {
    Canvas border;
    Canvas flag;
    Canvas grunge;
    Label name;

    public Canvas getBorder() {
        return border;
    }

    public void setBorder(Canvas border) {
        this.border = border;
    }

    public Canvas getFlag() {
        return flag;
    }

    public void setFlag(Canvas flag) {
        this.flag = flag;
    }

    public Canvas getGrunge() {
        return grunge;
    }

    public void setGrunge(Canvas grunge) {
        this.grunge = grunge;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }
}
