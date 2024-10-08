package constants;
import java.awt.*;

public class Theme {
    private Color background = new Color(6, 6, 6);
    private Color onBackground = new Color(255, 255, 255);

    private Color green = new Color(124, 213, 174);
    private Color blue = new Color(80, 144, 178);

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
}
