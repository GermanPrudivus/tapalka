package features.case_spining.components;

import common.Navigator;
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

// Skin Awarding Window Class (displayed after the roulette animation)
public class SkinAwardingWindow extends JPanel {
    final Theme theme = new Theme();
    private JFrame window;

    private JLayeredPane skinInfoBackground;
    private Lighting lighting;
    private JPanel skinInfo;

    // Constructor initializes the panel with BoxLayout and stores the window reference
    public SkinAwardingWindow(JFrame window) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        this.window = window;
    }

    // Creates the content of the window, including lighting effect and skin information
    public void createContent(SkinCard skin) {
        // Create a layered pane for the skin information and lighting effect
        skinInfoBackground = new JLayeredPane();
        skinInfoBackground.setBorder(new EmptyBorder(40, 40, 40, 40));
        skinInfoBackground.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        skinInfoBackground.setOpaque(false);

        // Add the lighting effect to the background
        lighting = new Lighting(skin.getColor(), new Point2D.Float(this.getWidth() / 2, (int) (this.getHeight() / 2.5)), 2);
        lighting.setBounds(0, 0, this.getWidth(), this.getHeight());
        skinInfoBackground.add(lighting, Integer.valueOf(0));

        // Add skin information (name, image, and play again button)
        skinInfo = this.createSkinInfo(skin);
        skinInfo.setBounds(0, 0, this.getWidth(), this.getHeight());
        skinInfoBackground.add(skinInfo, Integer.valueOf(1));

        // Add the layered pane to the main panel
        this.add(skinInfoBackground);

        this.revalidate();
        this.repaint();
    }

    // Resizes the content dynamically when the window size changes
    public void resizeContent(int width, int height) {
        if (lighting != null && skinInfo != null) {
            lighting.setBounds(0, 0, width, height);
            lighting.setPosition(new Point2D.Float(width / 2, height / 3));

            skinInfo.setBounds(0, 0, width, height);

            this.revalidate();
            this.repaint();
        }
    }

    // Creates the skin information panel, including the skin name, image, and a button
    private JPanel createSkinInfo(SkinCard skin) {
        JPanel skinInfo = new JPanel();
        skinInfo.setOpaque(false);
        skinInfo.setLayout(new BoxLayout(skinInfo, BoxLayout.Y_AXIS));
        skinInfo.add(Box.createVerticalGlue());

        // Add the skin name
        skinInfo.add(new Text(skin.getName(), "Montserrat-Bold.ttf", 30f));

        // Add the skin image
        components.Image skinImage = new components.Image(skin.getImage(), 400, 260);
        skinImage.setBounds(0, 0, 400, 300);
        skinInfo.add(skinImage);

        skinInfo.add(Box.createRigidArea(new Dimension(0, 10)));  // Add spacing between elements

        // Add a congratulatory message for the awarded skin
        skinInfo.add(new Text("You are awarded with this skin! Press “Play again” ", "Montserrat-Medium.ttf", 24f));
        skinInfo.add(new Text("to continue getting awards and winning!” ", "Montserrat-Medium.ttf", 24f));

        skinInfo.add(Box.createRigidArea(new Dimension(0, 20)));  // Add more spacing

        // Add the "Play again" button, which restarts the game
        Button button = new Button("Play again");
        button.addActionListener(new Navigator(this.window));
        skinInfo.add(button);

        // Add another spacer to vertically center the content
        skinInfo.add(Box.createVerticalGlue());

        return skinInfo;
    }

    // Paints the background of the window with rounded corners
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