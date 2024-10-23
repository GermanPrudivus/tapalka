package features.loss.pages;

import common.Navigator;
import common.interfaces.Page;
import components.Button;
import components.Header;
import components.Lighting;
import components.Text;
import constants.Theme;
import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Loss page (Shiwn after user loses the game)
public class LossPage implements Page {
    final Theme theme = new Theme();

    private JFrame window;
    private JLayeredPane background;
    private Lighting lighting;
    private JPanel content;

    // Sets the background of the page
    public LossPage(JFrame window) {
        background = new JLayeredPane();
        background.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        background.setBackground(theme.getBackgroundColor());
        background.setOpaque(true);
        this.window = window;
    }
    
    @Override
    public void initializeContent() {
        int width = this.window.getWidth();
        int height = this.window.getHeight();

        lighting = new Lighting(theme.getRed(), new Point2D.Float(width / 2, height));
        lighting.setBounds(0, 0, width, height);
        background.add(lighting, Integer.valueOf(0));

        content = this.createMainContent();
        content.setBounds(0, 0, width, height);
        background.add(content, Integer.valueOf(1));

        this.window.add(background);
    }

    @Override
    public void resizeContent(int width, int height) {
        if (lighting != null && content != null) {
            lighting.setPosition(new Point2D.Float(width / 2, height));
            lighting.setBounds(0, 0, width, height);

            content.setBounds(0, 0, width, height);
            
            background.revalidate();
            background.repaint();
        }
    }

    // Adds main content to the previously created background
    private JPanel createMainContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(70, 70, 70, 70));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new Header());
        content.add(Box.createVerticalGlue());
        content.add(new Text("You lost the game!", "Montserrat-SemiBold.ttf", 32f));
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton tryAgainButton = new Button("Try again");
        tryAgainButton.addActionListener(new Navigator(this.window));

        content.add(tryAgainButton);
        content.add(Box.createVerticalGlue());

        return content;
    }
}
