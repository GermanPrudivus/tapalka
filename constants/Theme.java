package constants;
import java.awt.*;

public class Theme {
    private Color background = new Color(6, 6, 6);
    private Color onBackground = new Color(255, 255, 255);

    private Color green = new Color(124, 213, 174);
    private Color blue = new Color(80, 144, 178);

    private Color red = new Color(179, 38, 30);

    private Color blueSkin = new Color(75, 105, 255);
    private Color pinkSkin = new Color(211, 44, 230);
    private Color redSkin = new Color(235, 75, 75);
    private Color goldSkin = new Color(228, 174, 51);

    public Color getBackgroundColor() {
        return this.background;
    }

    public Color getOnBackgroundColor() {
        return this.onBackground;
    }

    public Color getGreen() {
        return this.green;
    }

    public Color getBlue() {
        return this.blue;
    }

    public Color getRed() {
        return this.red;
    }

    public Color getBlueSkinColor() {
        return this.blueSkin;
    }

    public Color getPinkSkinColor() {
        return this.pinkSkin;
    }

    public Color getRedSkinColor() {
        return this.redSkin;
    }

    public Color getGoldSkinColor() {
        return this.goldSkin;
    }
}
