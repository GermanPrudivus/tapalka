package features.tapping.components;

import constants.Theme;
import java.awt.*;
import javax.swing.*;

// Tapping panel (Main gameplay panel)
public class TappingPanel extends JPanel {
    final Theme theme = new Theme();

    // Sets up the panel's parameters
    public TappingPanel() {
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(theme.getOnBackgroundColor());
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 40, 40);
    }
}