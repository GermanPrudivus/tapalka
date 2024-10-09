package features.start.components;

import java.awt.*;
import javax.swing.*;

import constants.Theme;

public class Lighting extends JPanel {
    final Theme theme = new Theme();

    public Lighting() {
        this.setPreferredSize(new Dimension(750, 340));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradientPaint = new GradientPaint(
            0, 170, theme.getBlue(),
            750, 170, theme.getGreen()
        );

        g2d.setPaint(gradientPaint);
        g2d.fillOval(0, 0, 750, 340);
    }
}