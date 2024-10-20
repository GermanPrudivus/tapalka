package components;

import java.awt.*;
import javax.swing.*;

import common.CustomFontLoader;
import constants.Theme;

public class Text extends JLabel {
    final Theme theme = new Theme();

    public Text(String text, String font, float size) {
        this.setText(text);
        this.setFont(CustomFontLoader.loadFont("assets/font/" + font, size));

        this.setForeground(theme.getOnBackgroundColor());
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
