package tools;

import com.sun.org.apache.xerces.internal.impl.dv.xs.StringDV;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class NodePlacer {
    private static double screenWidth=0;
    private static double screenHeight=0;


    public static void initControl(Pane view, Button node, double percentWidth, double percentHeight, double minHeight, double minWidth) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();

        node.setMinSize(minWidth,minHeight);
        node.setPrefSize(screenWidth*percentWidth,screenHeight*percentHeight);
    }

    public static void initLabel(Label label, String text, String font, FontWeight weight, Color textColor, int fontSize, FontPosture posture) {
        label.setText(text);
        label.setFont(Font.font(font, weight, posture, fontSize));
        label.setTextFill(textColor);
    }
    public static void initTextArea(TextArea textArea, String text, String font, FontWeight weight, Color textColor, int fontSize, FontPosture posture) {
        textArea.setText(text);
        textArea.setFont(Font.font(font, weight, posture, fontSize));
        String textValue = textColor.toString();
        textArea.setStyle("-fx-text-fill: "+ textValue+";");
    }


    public static void initImagePct(Pane view, ImageView node, double percentSizeOnWidth, double minHeightpx, double minWidthpx) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();

        node.minWidth(minWidthpx);
        node.minHeight(minHeightpx);
        node.prefWidth(screenWidth*percentSizeOnWidth);
        node.prefHeight(screenWidth*percentSizeOnWidth);
        node.setFitWidth(screenWidth * percentSizeOnWidth);
        node.setFitHeight(screenHeight * percentSizeOnWidth);
    }

    public static void initImagePixels(Pane view, ImageView node, double width, double height, double minHeightpx, double minWidthpx) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();

        node.minWidth(minWidthpx);
        node.minHeight(minHeightpx);
        node.prefWidth(width);
        node.prefHeight(height);
        node.setFitWidth(width);
        node.setFitHeight(height);
    }

    public static void placeNodeInsideCollection(Pane collection, Node node) {
        collection.getChildren().add(node);
    }


    public static void placeNodeRelativeToScreen(Pane view, Node node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();

        node.setLayoutX(screenWidth*xPercent);
        node.setLayoutY(screenHeight*yPercent);
        node.prefHeight(screenHeight*heightPercent);
        node.prefWidth(screenWidth*widthPercent);
        node.minWidth(minWidth);
        node.minHeight(minHeight);
        view.getChildren().add(node);

    }

    public static void placeControlRelativeToScreen(Pane view, Control node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();

        node.setLayoutX(screenWidth*xPercent);
        node.setLayoutY(screenHeight*yPercent);
        node.setPrefHeight(screenHeight*heightPercent);
        node.setPrefWidth(screenWidth*widthPercent);
        node.setMinWidth(minWidth);
        node.setMinHeight(minHeight);
        view.getChildren().add(node);

    }

    public static void placeLabelRelativeToScreen(Pane view, Label node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();
        node.setLayoutX(screenWidth*xPercent);
        node.setLayoutY(screenHeight*yPercent);
        node.setPrefHeight(screenHeight*heightPercent);
        node.setPrefWidth(screenWidth*widthPercent);
        node.setMinWidth(minWidth);
        node.setMinHeight(minHeight);view.getChildren().add(node);
    }

    public static void placeCollectorRelativeToScreen(Pane view, Pane node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();

        node.setLayoutX(screenWidth*xPercent);
        node.setLayoutY(screenHeight*yPercent);
        node.setPrefHeight(screenHeight*heightPercent);
        node.setPrefWidth(screenWidth*widthPercent);
        node.setMinWidth(minWidth);
        node.setMinHeight(minHeight);
        view.getChildren().add(node);
    }


        public static void placeImageRelativeToScreen(Pane view, ImageView node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        Scene scene = view.getScene();
        screenHeight = scene.getHeight();
        screenWidth = scene.getWidth();
        node.setLayoutX(screenWidth*xPercent);
        node.setLayoutY(screenHeight*yPercent);
        node.setPreserveRatio(true);
        node.setFitWidth(screenWidth*widthPercent);
        node.setFitHeight(screenHeight*heightPercent);
        view.getChildren().add(node);

    }

    public static void placeImageInsideCanvasPercent(Pane view,Canvas object, ImageView image, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        double objectHeight = object.getHeight();
        double objectWidth = object.getWidth();

        image.setLayoutX(objectWidth*xPercent);
        image.setLayoutY(objectHeight*yPercent);
        image.setPreserveRatio(true);
        image.setFitWidth(objectWidth*widthPercent);
        image.setFitHeight(objectHeight*heightPercent);
        view.getChildren().add(image);
    }

    public static void placeImageInsideCanvasPercent(Pane view,Canvas object, ImageView image, double xPercent, double yPercent, double sizeToWidth, double minHeight, double minWidth) {
        double objectHeight = object.getHeight();
        double objectWidth = object.getWidth();

        image.setLayoutX(objectWidth*xPercent);
        image.setLayoutY(objectHeight*yPercent);
        image.setPreserveRatio(true);
        image.setFitWidth(objectWidth*sizeToWidth);
        image.setFitHeight(objectWidth*sizeToWidth);
        view.getChildren().add(image);
    }

    public static void placeNodeInsideCanvasPercent(Pane view,Canvas object, Node node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        double objectHeight = object.getHeight();
        double objectWidth = object.getWidth();

        node.setLayoutX(objectWidth*xPercent);
        node.setLayoutY(objectHeight*yPercent);
        node.prefWidth(objectWidth*widthPercent);
        node.prefHeight(objectHeight*heightPercent);
        node.minWidth(minWidth);
        node.minHeight(minHeight);
        view.getChildren().add(node);
    }

    public static void placeNodeInsideControlPercent(Pane view,Control object, Node node, double xPercent, double yPercent, double widthPercent, double heightPercent, double minHeight, double minWidth) {
        double objectHeight = object.getPrefHeight();
        double objectWidth = object.getPrefWidth();
        node.setLayoutX(objectWidth*xPercent);
        node.setLayoutY(objectHeight*yPercent);
        node.prefWidth(objectWidth*widthPercent);
        node.prefHeight(objectHeight*heightPercent);
        node.minWidth(minWidth);
        node.minHeight(minHeight);
        view.getChildren().add(node);
    }

    public static void placeNodeInsideCanvasPercent(Pane view,Canvas object, Node node, double xPercent, double yPercent, double sizeToWidth, double minHeight, double minWidth) {
        double objectHeight = object.getHeight();
        double objectWidth = object.getWidth();

        node.setLayoutX(objectWidth*xPercent);
        node.setLayoutY(objectHeight*yPercent);
        node.prefWidth(objectWidth*sizeToWidth);
        node.prefHeight(objectWidth*sizeToWidth);
        node.minWidth(minWidth);
        node.minHeight(minHeight);
        view.getChildren().add(node);
    }

    public static void placeNodeInsideCanvasPixels(Pane view,Canvas object, Node node, double xPx, double yPx, double heightPx, double widthPx, double minHeight, double minWidth) {
        double objectHeight = object.getHeight();
        double objectWidth = object.getWidth();

        node.setLayoutX(xPx);
        node.setLayoutY(yPx);
        node.prefWidth(heightPx);
        node.prefHeight(widthPx);
        node.minWidth(minWidth);
        node.minHeight(minHeight);
        view.getChildren().add(node);
    }

    /**
     * Works only from left to right. I am not an Arab, you see.
     */
    public static void placeNodeNextToObjectPct(Pane view, Node object, Node node, double marginLeftPctFromView, double marginTopPctFromView, double heightPct, double widthPct, double minWidth, double minHeight, boolean verticalCenter) {
        node.setLayoutX((object.getBoundsInLocal().getWidth() + object.getLayoutX())+(view.getWidth()*marginLeftPctFromView));
        node.prefWidth(view.getWidth()*widthPct);
        node.prefHeight(view.getHeight()*heightPct);
        node.minHeight(minHeight);
        node.minWidth(minWidth);
        if(verticalCenter) {
            double marginTopAndDown;
            if (object.getBoundsInLocal().getHeight() > node.getBoundsInLocal().getHeight()) {
                marginTopAndDown = ((object.getBoundsInLocal().getHeight() - node.getBoundsInLocal().getHeight())/ 2);
                node.setLayoutY(object.getLayoutY()+marginTopAndDown);

            }else {
                marginTopAndDown = (node.getBoundsInLocal().getHeight() - object.getBoundsInLocal().getHeight()/2);
                node.setLayoutY(object.getLayoutY()-marginTopAndDown);
            }
        }else {
            node.setLayoutY(object.getLayoutY() + (view.getWidth()*marginTopPctFromView));
        }
        view.getChildren().add(node);

    }

    /**
     * Works only from left to right. I am not an Arab, you see.
     */
    public static void placeLabelNextToObjectPct(Pane view, Node object, Label node, double marginLeftPctFromView, double marginTopPctFromView, double heightPct, double widthPct, double minWidth, double minHeight, boolean verticalCenter) {
        node.setLayoutX((object.getBoundsInLocal().getWidth() + object.getLayoutX())+(view.getPrefWidth()*marginLeftPctFromView));
        node.setPrefWidth(view.getPrefWidth()*widthPct);
        node.setPrefHeight(view.getPrefHeight()*heightPct);
        node.setMinHeight(minHeight);
        node.setMinWidth(minWidth);
        if(verticalCenter) {
            double marginTopAndDown;
            if (object.getBoundsInLocal().getHeight() > node.getPrefHeight()) {
                marginTopAndDown = ((object.getBoundsInLocal().getHeight() - node.getPrefHeight())/ 2);
                node.setLayoutY(object.getLayoutY()+marginTopAndDown);

            }else {
                marginTopAndDown = ((node.getPrefHeight() - object.getBoundsInLocal().getHeight())/2);
                node.setLayoutY(object.getLayoutY()-marginTopAndDown);
            }
        }else {
            node.setLayoutY(object.getLayoutY() + (view.getPrefWidth()*marginTopPctFromView));
        }
        view.getChildren().add(node);
    }

    public static void placeNodeBeneathObjectPct(Pane view, Node object, Node node, double marginLeftPctFromView, double marginTopPctFromView, double heightPct, double widthPct, double minWidth, double minHeight, boolean horizontalCenter) {
        //node.setLayoutX((object.getBoundsInLocal().getWidth() +(view.getWidth()*marginLeftPctFromView)));
        System.out.println(object.getLayoutY() + " " + object.getBoundsInLocal().getHeight());
        node.setLayoutY(object.getLayoutY() + object.getBoundsInLocal().getHeight() + (view.getHeight()*marginTopPctFromView));
        node.prefWidth(view.getWidth()*widthPct);
        node.prefHeight(view.getHeight()*heightPct);
        node.minHeight(minHeight);
        node.minWidth(minWidth);
        if(horizontalCenter) {
            double marginLeftAndRight;
            if (object.getBoundsInLocal().getWidth() > node.getBoundsInLocal().getWidth()) {
                marginLeftAndRight = ((object.getBoundsInLocal().getWidth() - node.getBoundsInLocal().getWidth())/ 2);
                node.setLayoutX(object.getLayoutX()+marginLeftAndRight);

            }else {
                marginLeftAndRight = (node.getBoundsInLocal().getWidth() - object.getBoundsInLocal().getWidth()/2);
                node.setLayoutX(object.getLayoutX()-marginLeftAndRight);
            }
        }else {
            node.setLayoutX(object.getLayoutX() + (view.getWidth()*marginLeftPctFromView));
        }
        view.getChildren().add(node);

    }

    public static void placeControlBeneathObjectPct(Pane view, Node object, Control node, double marginLeftPctFromView, double marginTopPctFromView, double heightPct, double widthPct, double minWidth, double minHeight, boolean horizontalCenter) {
        //node.setLayoutX((object.getBoundsInLocal().getWidth() +(view.getWidth()*marginLeftPctFromView)));
        node.setLayoutY(object.getLayoutY() + object.getBoundsInLocal().getHeight() + (view.getHeight()*marginTopPctFromView));
        node.setPrefWidth(view.getWidth()*widthPct);
        node.setPrefHeight(view.getHeight()*heightPct);
        node.setMinHeight(minHeight);
        node.setMinWidth(minWidth);
        if(horizontalCenter) {
            double marginLeftAndRight;
            if (object.getBoundsInLocal().getWidth() > node.getBoundsInLocal().getWidth()) {
                marginLeftAndRight = ((object.getBoundsInLocal().getWidth() - node.getBoundsInLocal().getWidth())/ 2);
                node.setLayoutX(object.getLayoutX()+marginLeftAndRight);

            }else {
                marginLeftAndRight = (node.getBoundsInLocal().getWidth() - object.getBoundsInLocal().getWidth()/2);
                node.setLayoutX(object.getLayoutX()-marginLeftAndRight);
            }
        }else {
            node.setLayoutX(object.getLayoutX() + (view.getWidth()*marginLeftPctFromView));
        }
        view.getChildren().add(node);
    }

    public void alignNodeHorizontalToView(Pane view, Node node) {
        node.setLayoutX((view.getWidth()-node.getBoundsInLocal().getWidth())/2);
    }

    public void alignLabelHorizontalToView(Pane view, Label node) {
        node.setLayoutX((view.getWidth()-node.getBoundsInLocal().getWidth())/2);
    }
}
