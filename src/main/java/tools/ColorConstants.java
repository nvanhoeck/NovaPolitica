package tools;

import javafx.scene.paint.Color;

public class ColorConstants {
    public static Color primaryBlue = Color.rgb(55,160,226);
    public static Color primaryDarkBlue = Color.rgb(0,85,130);
    public static Color neutralDarkGrey = Color.rgb(51,51,51);
    public static Color highlightOrange = Color.rgb(255,102,0);
    public static Color neutralDarkGreyBlankedOut = Color.rgb(51,51,51, 0.66);

    public static String getPrimaryBlue() {
        return ColorConstants.primaryBlue.toString().replace("0x", "");
    }

    public static String getPrimaryDarkBlue() {
        return ColorConstants.primaryDarkBlue.toString().replace("0x", "");
    }

    public static String getNeutralDarkGrey() {
        return ColorConstants.neutralDarkGrey.toString().replace("0x", "");
    }

    public static String getHighlightOrange() {
        return ColorConstants.highlightOrange.toString().replace("0x", "");
    }
}
