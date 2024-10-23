package features.awarding.pages;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.Button;
import common.Navigator;
import common.interfaces.Page;
import components.Header;
import components.Image;
import components.Lighting;
import components.Shadow;
import components.Text;
import constants.Theme;
import features.awarding.components.InspectionPanel;
import features.awarding.components.QuestionButton;

public class AwardingPage implements Page {
    final Theme theme = new Theme();

    private JFrame window;
    private JLayeredPane background;
    private Lighting lighting;
    private JPanel content;
    private Shadow shadow;
    private JPanel skinInspection;

    public AwardingPage(JFrame window) {
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

        lighting = new Lighting(theme.getBlue(), new Point2D.Float(width / 2, height));
        lighting.setBounds(0, 0, width, height);
        background.add(lighting, Integer.valueOf(0));

        content = this.createMainContent();
        content.setBounds(0, 0, width, height);
        background.add(content, Integer.valueOf(1));

        shadow = new Shadow();
        shadow.setBounds(0, 0, width, height);
        shadow.setVisible(false);
        background.add(shadow, Integer.valueOf(2));

        skinInspection = this.createSkinInspectionPanel();
        skinInspection.setBounds(0, 0, width, height);
        background.add(skinInspection, Integer.valueOf(3));

        this.window.add(background);
    }

    @Override
    public void resizeContent(int width, int height) {
        if (lighting != null && content != null && skinInspection != null) {
            lighting.setPosition(new Point2D.Float(width / 2, height));
            lighting.setBounds(0, 0, width, height);

            content.setBounds(0, 0, width, height);
            shadow.setBounds(0, 0, width, height);
            
            skinInspection.setBounds(0, 0, width, height);
            for(Component component : skinInspection.getComponents()){
                if (component instanceof InspectionPanel) {
                    component.setBounds(130, this.window.getHeight() - 462, 825, 412);
                } else if (component instanceof QuestionButton) {
                    component.setBounds(70, this.window.getHeight() - 120, 60, 60);
                }
            }
            
            background.revalidate();
            background.repaint();
        }
    }

    private JPanel createMainContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(70, 70, 70, 70));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new Header());
        content.add(Box.createVerticalGlue());

        content.add(new Image("./assets/congrats.png", 80, 80));
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        content.add(new Text("Congrats! You are getting awarded", "Montserrat-Medium.ttf", 30f));
        content.add(new Text("with the free case of CS skins", "Montserrat-Medium.ttf", 30f));
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        content.add(new Image("./assets/bravo-case.png", 300, 230));
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton freeSkinButton = new Button("Free skin");
        freeSkinButton.addActionListener(new Navigator(this.window));

        content.add(freeSkinButton);
        content.add(Box.createVerticalGlue());

        return content;
    }

    private JPanel createSkinInspectionPanel() {
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setOpaque(false);

        InspectionPanel inspectionPanel = new InspectionPanel();
        inspectionPanel.setBounds(130, this.window.getHeight() - 462, 825, 412);
        inspectionPanel.setVisible(false);
        content.add(inspectionPanel);

        QuestionButton questionButton = new QuestionButton("?", inspectionPanel, shadow);
        questionButton.setBounds(70, this.window.getHeight() - 120, 60, 60);
        content.add(questionButton);

        return content;
    }
}
