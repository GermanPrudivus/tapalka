package features.tapping.components;

import common.CustomFontLoader;
import constants.Theme;
import java.awt.*;
import javax.swing.*;

// Coin counter class (Counts earned coins during the game)
public class Counter extends JLabel {
    final Theme theme = new Theme();

    private int count = 0;

    // Sets counter parameters
    public Counter() {
        this.setText(count + " / 30");
        this.setFont(CustomFontLoader.loadFont("assets/font/Montserrat-Bold.ttf", 26f));

        this.setForeground(theme.getOnBackgroundColor());
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public int getCount() {
        return this.count;
    }

    // Updates the state of the counter
    public void setCount(int count) {
        this.count = count;
        
        SwingUtilities.invokeLater(() -> {
            this.setText(count + " / 30");

            this.revalidate();
            this.repaint();
        });
    }
}