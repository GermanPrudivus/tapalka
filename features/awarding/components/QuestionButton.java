package features.awarding.components;

import common.CustomFontLoader;
import components.Button;
import components.Shadow;
import constants.Theme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Question button class (Opens the inspection panel with skins)
public class QuestionButton extends Button implements ActionListener {
    final Theme theme = new Theme();
    private boolean isActive = false;

    private JPanel content;
    private Shadow shadow;
    
    // Sets the button parameters and stores necessary references
    public QuestionButton(String text, JPanel content, Shadow shadow) {
        super(text);
        this.setFont(CustomFontLoader.loadFont("assets/font/Montserrat-Bold.ttf", 26f));
        this.addActionListener(this);

        this.content = content;
        this.shadow = shadow;
    }

    public boolean getButtonStatus() {
        return this.isActive;
    }

    // Responds to the button being pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        this.isActive = !this.isActive;

        if (this.isActive) {
            this.shadow.setVisible(true);
            this.content.setVisible(true);
        } else {
            this.shadow.setVisible(false);
            this.content.setVisible(false);
        }

        this.revalidate();
        this.repaint();
    }

    // Paints the button with rounded corners when active
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isActive) {
            int arcSize = 30;
            int width = getWidth();
            int height = getHeight();

            Polygon polygon = new Polygon();
            polygon.addPoint(arcSize, 0);
            polygon.addPoint(width, 0);
            polygon.addPoint(width, height);
            polygon.addPoint(arcSize, height);
            
            g2d.setColor(theme.getOnBackgroundColor());
            g2d.fillArc(0, 0, arcSize * 2, arcSize * 2, 90, 90);
            g2d.fillArc(0, height - arcSize * 2, arcSize * 2, arcSize * 2, 180, 90);
            
            g2d.fillPolygon(polygon);
        }

        super.paintComponent(g2d);
    }
}