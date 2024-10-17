package features.tapping.components;

import java.awt.*;
import javax.swing.*;

import common.CustomFontLoader;
import constants.Theme;

public class Counter extends JLabel {
    final Theme theme = new Theme();

    private int count = 0;

    public Counter() {
        this.setText(count + " / 30");
        this.setFont(CustomFontLoader.loadFont("assets/font/Montserrat-Bold.ttf", 26f));

        this.setForeground(theme.getOnBackgroundColor());
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
        this.setText(count + " / 30");

        this.revalidate();
        this.repaint();
    }
}
