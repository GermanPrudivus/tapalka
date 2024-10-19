package features.start.pages;

import components.Button;
import components.Text;
import constants.*;
import features.start.components.Lighting;
import java.awt.*;
import javax.swing.*;

public class StartPage {
    final Size size = new Size();
    final Theme theme = new Theme();

    final JFrame window;

    public StartPage(JFrame window) {
        this.window = window;
    }

    public void openStartPage() {
        this.window.setSize(size.getWidth(), size.getHeight());
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.initializeContent();
        this.window.setVisible(true);
    }

    private void initializeContent() {
        JLayeredPane background = new JLayeredPane();
        
        background.setBackground(theme.getBackgroundColor());
        
        Lighting lighting = new Lighting();
        lighting.setBounds((size.getWidth()/2) - 375, size.getHeight() - 170, 750, 340);
        background.add(lighting, Integer.valueOf(0));

        JPanel content = this.createContent();
        content.setBounds(0, 0, size.getWidth(), size.getHeight());
        background.add(content, Integer.valueOf(1));

        this.window.add(background);
    }

    private JPanel createContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(Box.createVerticalGlue());
        content.add(new Text("Welcome to the tapping", "Montserrat-SemiBold.ttf", 36f));
        content.add(new Text("game “Tapalka”", "Montserrat-SemiBold.ttf", 36f));
        content.add(Box.createRigidArea(new Dimension(0, 30)));
        content.add(new Button("Start a new game"));
        content.add(Box.createVerticalGlue());

        return content;
    }
}
