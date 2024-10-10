package features.start.pages;

import javax.swing.*;
import java.awt.*;

import components.Button;
import components.Text;
import constants.*;
import features.start.components.Lighting;

public class StartPage {
    final Size size = new Size();
    final Theme theme = new Theme();

    private JFrame window = new JFrame("Tapalka");

    public void openStartPage() {
        this.window.setSize(size.getWidth(), size.getHeight());
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.initializeContent();
        this.window.setVisible(true);
    }

    private void initializeContent() {
        JLayeredPane background = new JLayeredPane();
        
        background.setBackground(theme.getBackgroundColor());
        background.setOpaque(true);
        
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
