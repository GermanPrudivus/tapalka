package features.case_spining.pages;

import common.interfaces.Page;
import components.Header;
import components.Lighting;
import components.Shadow;
import constants.Theme;
import features.case_spining.components.Roullete;
import features.case_spining.components.SkinAwardingWindow;
import features.case_spining.components.VerticalLine;
import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Case Spinning Page (Case spinning animation and Skin Awarding widget)
public class CaseSpinningPage implements Page {
    final Theme theme = new Theme();

    private JFrame window;
    private JLayeredPane background;
    private Lighting lighting1;
    private Lighting lighting2;
    private JPanel content;

    private Shadow shadow;
    private SkinAwardingWindow awardingWindow;

    // Sets the background for the page
    public CaseSpinningPage(JFrame window) {
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
        this.createSkinAwardingWindow();

        lighting1 = new Lighting(theme.getGreen(), new Point2D.Float(0, height));
        lighting1.setBounds(0, 0, width, height);
        background.add(lighting1, Integer.valueOf(0));

        lighting2 = new Lighting(theme.getBlue(), new Point2D.Float(width, height));
        lighting2.setBounds(0, 0, width, height);
        background.add(lighting2, Integer.valueOf(0));

        content = this.createMainContent();
        content.setBounds(0, 0, width, height);
        background.add(content, Integer.valueOf(1));

        this.window.add(background);
    }

    @Override
    public void resizeContent(int width, int height) {
        if (lighting1 != null && lighting2 != null && content != null) {
            lighting1.setBounds(0, 0, width, height);
            lighting1.setPosition(new Point2D.Float(0, height));

            lighting2.setBounds(0, 0, width, height);
            lighting2.setPosition(new Point2D.Float(width, height));

            this.resizeSkinAwardingWindow(width, height);
            
            content.setBounds(0, 0, width, height);
            content.repaint();
        }
    }

    // Adds main content to the previously created background
    private JPanel createMainContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(70, 0, 70, 0));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new Header());
        content.add(Box.createVerticalGlue());
        content.add(this.createRoulletPane());

        return content;
    }

    // Creates Roulette with the skin
    private JLayeredPane createRoulletPane() {
        JLayeredPane roulletePane = new JLayeredPane();
        roulletePane.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        roulletePane.setOpaque(false);
        
        Roullete roullete = new Roullete(awardingWindow, shadow);
        SwingUtilities.invokeLater(() -> {
            int roulleteY = (roulletePane.getHeight() / 2 - 100);
            roullete.setBounds(0, roulleteY, roulletePane.getWidth(), 200);
            roulletePane.revalidate();
            roulletePane.repaint();
        });
        roulletePane.add(roullete, Integer.valueOf(0));
    
        JPanel verticalLine = new VerticalLine();
        SwingUtilities.invokeLater(() -> {
            int lineHeight = roulletePane.getHeight() / 2;
            int lineX = (roulletePane.getWidth() / 2) - 2;
            int lineY = (roulletePane.getHeight() / 2) - (lineHeight / 2);
            verticalLine.setBounds(lineX, lineY, 4, lineHeight);
            roulletePane.revalidate();
            roulletePane.repaint();
        });
        roulletePane.add(verticalLine, Integer.valueOf(1));
    
        return roulletePane;
    }

    // Creates Skin Awarding window which appears after Roulette animation
    private void createSkinAwardingWindow() {
        int width = this.window.getWidth();
        int height = this.window.getHeight();

        shadow = new Shadow();
        shadow.setBounds(0, 0, width, height);
        shadow.setVisible(false);
        background.add(shadow, Integer.valueOf(2));

        awardingWindow = new SkinAwardingWindow(this.window);
        awardingWindow.setBounds(width / 8, height / 8, 3 * width / 4, 3 * height / 4); 
        awardingWindow.setVisible(false);

        background.add(awardingWindow, Integer.valueOf(3));
    }

    private void resizeSkinAwardingWindow(int width, int height) {
        for (Component component : content.getComponents()) {
            if (component instanceof JLayeredPane) {
                Component roullete = ((JLayeredPane) component).getComponentsInLayer(0)[0];
                int roulleteY = (component.getHeight() / 2 - 100);
                roullete.setBounds(0, roulleteY, component.getWidth(), 200);

                Component verticalLine = ((JLayeredPane) component).getComponentsInLayer(1)[0];
                int lineHeight = component.getHeight() / 2;
                int lineX = (component.getWidth() / 2) - 2;
                int lineY = (component.getHeight() / 2) - (lineHeight / 2);
                verticalLine.setBounds(lineX, lineY, 4, lineHeight);

                break;
            }
        }

        shadow.setBounds(0, 0, width, height);
        awardingWindow.setBounds(width / 8, height / 8, 3 * width / 4, 3 * height / 4);
        awardingWindow.resizeContent(3 * width / 4, 3 * height / 4);
    }
}