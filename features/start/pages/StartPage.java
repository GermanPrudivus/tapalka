package features.start.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

import components.Button;
import components.Lighting;
import components.Text;

import constants.*;
import services.Navigator;

public class StartPage {
    final Size size = new Size();
    final Theme theme = new Theme();

    private JFrame window;

    public StartPage(JFrame window) {
        this.window = window;
    }

    public void initializeContent() {
        JLayeredPane background = new JLayeredPane();
        
        background.setBackground(theme.getBackgroundColor());
        background.setOpaque(true);
        
        Lighting lighting = new Lighting(theme.getBlue(), new Point2D.Float(size.getWidth()/2, size.getHeight()));
        lighting.setBounds(0, 0, size.getWidth(), size.getHeight());
        background.add(lighting, Integer.valueOf(0));

        JPanel content = this.createMainContent();
        content.setBounds(0, 0, size.getWidth(), size.getHeight());
        background.add(content, Integer.valueOf(1));

        this.window.add(background);
    }

    private JPanel createMainContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(Box.createVerticalGlue());
        content.add(new Text("Welcome to the tapping", "Montserrat-SemiBold.ttf", 36f));
        content.add(new Text("game “Tapalka”", "Montserrat-SemiBold.ttf", 36f));
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton startButton = new Button("Start a new game");
        startButton.addActionListener(new Navigator(window));

        content.add(startButton);
        content.add(Box.createVerticalGlue());

        return content;
    }
}
