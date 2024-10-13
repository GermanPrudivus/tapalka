package features.tapping.pages;

import java.awt.geom.Point2D;
import javax.swing.*;

import components.Lighting;
import constants.*;

public class TappingPage {
    final Size size = new Size();
    final Theme theme = new Theme();

    private JFrame window;

    public TappingPage(JFrame window) {
        this.window = window;
    }

    public void initializeContent() {
        JLayeredPane background = new JLayeredPane();
        
        background.setBackground(theme.getBackgroundColor());
        background.setOpaque(true);

        Lighting lighting1 = new Lighting(theme.getGreen(), new Point2D.Float(0, size.getHeight()));
        lighting1.setBounds(0, 0, size.getWidth(), size.getHeight());
        background.add(lighting1, Integer.valueOf(0));

        Lighting lighting2 = new Lighting(theme.getBlue(), new Point2D.Float(size.getWidth(), size.getHeight()));
        lighting2.setBounds(0, 0, size.getWidth(), size.getHeight());
        background.add(lighting2, Integer.valueOf(0));
    
        // Optional: Add the game content
        // JPanel content = this.createMainContent();
        // content.setBounds(0, 0, size.getWidth(), size.getHeight());
        // background.add(content, Integer.valueOf(1));
    
        this.window.add(background);
    }    
}
