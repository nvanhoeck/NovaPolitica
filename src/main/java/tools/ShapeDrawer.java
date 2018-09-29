package tools;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ShapeDrawer {
    private static Canvas canvas;
    private static GraphicsContext gc;

    public static Canvas drawShape(Pane view, Coordinate.Type type, double squareSizeWidth, double squareSizeHeight, List<Coordinate> pctCoordinates, Paint fill, Color stroke, double strokeSizePx) {
        if (type.equals(Coordinate.Type.PERCENTBASED)) {
            return drawShapePct(view, squareSizeWidth, squareSizeHeight, pctCoordinates, fill, stroke, strokeSizePx);
        } else {
            return drawShapePx(view, squareSizeWidth, squareSizeHeight, pctCoordinates, fill, stroke, strokeSizePx);
        }
    }

    public static Canvas drawImage(Pane view, Coordinate.Type type, double squareSizeWidth, double squareSizeHeight, List<Coordinate> pctCoordinates, String imageurl, Color stroke, double strokeSizePx) {
        if (type.equals(Coordinate.Type.PERCENTBASED)) {
            return drawShapePctImg(view, squareSizeWidth, squareSizeHeight, pctCoordinates, imageurl, stroke, strokeSizePx);
        } else {
            return drawShapePxtImg(view, squareSizeWidth, squareSizeHeight, pctCoordinates, imageurl, stroke, strokeSizePx);
        }
    }

    private static Canvas drawShapePx(Pane view, double squareSizeWidthPx, double squareSizeHeightPx, List<Coordinate> pxCoordinates, Paint fill, Color stroke, double strokeSizePx) {
        canvas = new Canvas(squareSizeWidthPx, squareSizeHeightPx);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.setLineWidth(strokeSizePx);
        double[] xs = new double[pxCoordinates.size()];
        double[] ys = new double[pxCoordinates.size()];
        if (pxCoordinates.get(0).getType().equals(Coordinate.Type.PIXELBASED)) {
            for (int i = 0; i < pxCoordinates.size(); i++) {
                xs[i] = pxCoordinates.get(i).getX();
                ys[i] = pxCoordinates.get(i).getY();
            }
        } else {
            for (int i = 0; i < pxCoordinates.size(); i++) {
                xs[i] = pxCoordinates.get(i).getX()*canvas.getWidth();
                ys[i] = pxCoordinates.get(i).getY()*canvas.getHeight();
            }
        }
        gc.fillPolygon(xs, ys, pxCoordinates.size());
        return canvas;
    }

    private static Canvas drawShapePct(Pane view, double squareSizeWidthPct, double squareSizeHeightPct, List<Coordinate> pctCoordinates, Paint fill, Color stroke, double strokeSizePx) {
        canvas = new Canvas(view.getWidth() * squareSizeWidthPct, view.getHeight() * squareSizeHeightPct);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.setLineWidth(strokeSizePx);
        double[] xs = new double[pctCoordinates.size()];
        double[] ys = new double[pctCoordinates.size()];
        for (int i = 0; i < pctCoordinates.size(); i++) {
            xs[i] = pctCoordinates.get(i).getX() * view.getWidth();
            ys[i] = pctCoordinates.get(i).getY() * view.getHeight();
        }
        gc.fillPolygon(xs, ys, pctCoordinates.size());
        return canvas;
    }

    private static Canvas drawShapePctImg(Pane view, double squareSizeWidthPct, double squareSizeHeightPct, List<Coordinate> pctCoordinates, String imgUrl, Color stroke, double strokeSizePx) {
        canvas = new Canvas(view.getWidth() * squareSizeWidthPct, view.getHeight() * squareSizeHeightPct);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(new ImagePattern(new Image(imgUrl)));
        gc.setStroke(stroke);
        gc.setLineWidth(strokeSizePx);
        double[] xs = new double[pctCoordinates.size()];
        double[] ys = new double[pctCoordinates.size()];
        for (int i = 0; i < pctCoordinates.size(); i++) {
            xs[i] = pctCoordinates.get(i).getX() * view.getWidth();
            ys[i] = pctCoordinates.get(i).getY() * view.getHeight();
        }
        gc.fillPolygon(xs, ys, pctCoordinates.size());
        return canvas;
    }

    private static Canvas drawShapePxtImg(Pane view, double squareSizeWidthPx, double squareSizeHeightPx, List<Coordinate> pxCoordinates, String imgUrl, Color stroke, double strokeSizePx) {
        canvas = new Canvas(squareSizeWidthPx, squareSizeHeightPx);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(new ImagePattern(new Image(imgUrl)));
        gc.setStroke(stroke);
        gc.setLineWidth(strokeSizePx);
        double[] xs = new double[pxCoordinates.size()];
        double[] ys = new double[pxCoordinates.size()];
        if (pxCoordinates.get(0).getType().equals(Coordinate.Type.PIXELBASED)) {
            for (int i = 0; i < pxCoordinates.size(); i++) {
                xs[i] = pxCoordinates.get(i).getX();
                ys[i] = pxCoordinates.get(i).getY();
            }
        } else {
            for (int i = 0; i < pxCoordinates.size(); i++) {
                xs[i] = pxCoordinates.get(i).getX()*canvas.getWidth();
                ys[i] = pxCoordinates.get(i).getY()*canvas.getHeight();
            }
        }
        gc.fillPolygon(xs, ys, pxCoordinates.size());
        return canvas;
    }

    public static Canvas drawBorder(Pane view, Canvas baseImg, List<Coordinate> pctCoordinatesBase, Color borderColor, double borderSizePx, BorderType borderType) {
        List<Coordinate> coordinatesPx = pctCoordinatesBase.stream().map((c) -> c = new Coordinate(c.getX() * view.getWidth(), c.getY() * view.getHeight(), Coordinate.Type.PIXELBASED)).collect(Collectors.toList());
        switch (borderType) {
            case BOTTOMRIGHT:
                return getBottomRightBorder(baseImg, coordinatesPx, borderColor, borderSizePx);
            case SURROUND:
                return getSurroundBorder(baseImg, pctCoordinatesBase, borderColor, borderSizePx);
            default:
                return getSurroundBorder(baseImg, coordinatesPx, borderColor, borderSizePx);
        }

    }

    private static Canvas getSurroundBorder(Canvas baseImg, List<Coordinate> pctCoordinatesBase, Color borderColor, double borderSizePx) {
        canvas = new Canvas(baseImg.getWidth() + borderSizePx, baseImg.getHeight() + borderSizePx);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(borderColor);
        double[] xs = new double[pctCoordinatesBase.size()];
        double[] ys = new double[pctCoordinatesBase.size()];
        for (int i = 0; i < pctCoordinatesBase.size(); i++) {
            xs[i] = canvas.getWidth() * pctCoordinatesBase.get(i).getX();
            ys[i] = canvas.getHeight() * pctCoordinatesBase.get(i).getY();
        }
        gc.fillPolygon(xs, ys, pctCoordinatesBase.size());
        return canvas;
    }

    private static Canvas getBottomRightBorder(Canvas baseImg, List<Coordinate> pxCoordinatesBase, Color borderColor, double borderSizePx) {
        canvas = new Canvas(baseImg.getWidth() + borderSizePx, baseImg.getHeight() + borderSizePx);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(borderColor);

        double xs[] = new double[pxCoordinatesBase.size()];
        double ys[] = new double[pxCoordinatesBase.size()];
        double prevX = 0.0;
        double prevY = 0.0;

        for (int i = 0; i < pxCoordinatesBase.size(); i++) {
            if (i == 0) {
                prevX = pxCoordinatesBase.get(i).getX();
                prevY = pxCoordinatesBase.get(i).getY();
                xs[i] = prevX;
                ys[i] = prevY;
            } else {
                double x = pxCoordinatesBase.get(i).getX();
                double y = pxCoordinatesBase.get(i).getY();
                if (prevX < x && prevY == y) {
                    xs[i] = x + borderSizePx;
                    ys[i] = y;
                } else if (prevX < x && prevY < y) {
                    xs[i] = x + borderSizePx;
                    ys[i] = y;
                } else if (prevX == x && prevY < y) {
                    xs[i] = x + borderSizePx;
                    ys[i] = y + borderSizePx;
                } else if (prevX > x && prevY < y) {
                    xs[i] = x + (borderSizePx / 2);
                    ys[i] = y + borderSizePx;
                } else if (prevX > x && prevY == y) {
                    xs[i] = x;
                    ys[i] = y + borderSizePx;
                } else {
                    xs[i] = x;
                    ys[i] = y;
                }
                prevX = x;
                prevY = y;
            }
        }
        gc.fillPolygon(xs, ys, pxCoordinatesBase.size());
        return canvas;
    }

    public enum BorderType {
        SURROUND, LEFT, TOP, RIGHT, BOTTOM, TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT
    }

}
