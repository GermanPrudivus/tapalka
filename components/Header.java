package components;

import java.awt.*;
import javax.swing.*;

import common.CustomFontLoader;
import constants.Theme;

public class Header extends JLabel{
    final Theme theme = new Theme();

    public Header() {
        this.setText("Tapalka");
        this.setFont(CustomFontLoader.loadFont("assets/font/Montserrat-Bold.ttf", 36f));

        this.setForeground(theme.getOnBackgroundColor());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
