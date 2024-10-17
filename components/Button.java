package components;

import java.awt.*;
import javax.swing.*;

import common.CustomFontLoader;
import constants.Theme;

public class Button extends JButton {
    final Theme theme = new Theme();
    
    public Button(String text) {
        super(text);

        this.setFont(CustomFontLoader.loadFont("assets/font/Montserrat-Bold.ttf", 20f));
        this.setForeground(theme.getBackgroundColor());

        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setPreferredSize(new Dimension(400, 80));
        this.setMinimumSize(new Dimension(200, 40));
        this.setMaximumSize(new Dimension(400, 80));

        this.setBackground(theme.getOnBackgroundColor());
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(theme.getOnBackgroundColor());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
    }
}