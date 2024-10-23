package features.case_spining.components;

import components.Button;
import components.Lighting;
import components.SkinCard;
import components.Text;
import constants.Theme;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import common.Navigator;

public class SkinAwardingWindow extends JPanel {
    final Theme theme = new Theme();
    private JFrame window;

    private JLayeredPane skinInfoBackground;
    private Lighting lighting;
    private JPanel skinInfo;

    public SkinAwardingWindow(JFrame window) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        this.window = window;
    }

    public void createContent(SkinCard skin) {
        // Create a layered pane for the image and lighting effect
        skinInfoBackground = new JLayeredPane();
        skinInfoBackground.setBorder(new EmptyBorder(40, 40, 40, 40));
        skinInfoBackground.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        skinInfoBackground.setOpaque(false);

        // Add the lighting effect
        lighting = new Lighting(skin.getColor(), new Point2D.Float(this.getWidth() / 2, (int) (this.getHeight() / 2.5)), 2);
        lighting.setBounds(0, 0, this.getWidth(), this.getHeight());
        skinInfoBackground.add(lighting, Integer.valueOf(0));

        skinInfo = this.createSkinInfo(skin);
        skinInfo.setBounds(0, 0, this.getWidth(), this.getHeight());
        skinInfoBackground.add(skinInfo, Integer.valueOf(1));

        this.add(skinInfoBackground);

        // Revalidate and repaint to ensure the UI updates with the new content
        this.revalidate();
        this.repaint();
    }

    public void resizeContent(int width, int height) {
        if (lighting != null && skinInfo != null) {
            lighting.setBounds(0, 0, width, height);
            lighting.setPosition(new Point2D.Float(width / 2, height / 3));

            skinInfo.setBounds(0, 0, width, height);

            this.revalidate();
            this.repaint();
        }
    }

    private JPanel createSkinInfo(SkinCard skin) {
        JPanel skinInfo = new JPanel();
        skinInfo.setOpaque(false);
        skinInfo.setLayout(new BoxLayout(skinInfo, BoxLayout.Y_AXIS));

        skinInfo.add(Box.createVerticalGlue());
        skinInfo.add(new Text(skin.getName(), "Montserrat-Bold.ttf", 30f));
        
        components.Image skinImage = new components.Image(skin.getImage(), 400, 260);
        skinImage.setBounds(0, 0, 400, 300);
        skinInfo.add(skinImage);

        skinInfo.add(Box.createRigidArea(new Dimension(0, 10)));

        skinInfo.add(new Text("You are awarded with this skin! Press “Play again” ", "Montserrat-Medium.ttf", 24f));
        skinInfo.add(new Text("to continue getting awards and winning!” ", "Montserrat-Medium.ttf", 24f));
        skinInfo.add(Box.createRigidArea(new Dimension(0, 20)));

        Button button = new Button("Play again");
        button.addActionListener(new Navigator(this.window));
        skinInfo.add(button);

        skinInfo.add(Box.createVerticalGlue());

        return skinInfo;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(theme.getBackgroundColor());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

        g2d.setColor(theme.getOnBackgroundColor());
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 40, 40);
    }
}