package features.case_spining.pages;

import common.interfaces.Page;
import components.Header;
import components.Lighting;
import components.Shadow;
import constants.*;
import features.case_spining.components.SkinAwardingWindow;
import features.case_spining.components.Roullete;
import features.case_spining.components.VerticalLine;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.Point2D;
import javax.swing.border.EmptyBorder;

public class CaseSpinningPage implements Page {
    final Theme theme = new Theme();

    private JFrame window;
    private JLayeredPane background;
    private Lighting lighting1;
    private Lighting lighting2;
    private JPanel content;

    private Shadow shadow;
    private SkinAwardingWindow awardingWindow;

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

            shadow.setBounds(0, 0, this.window.getWidth(), this.window.getHeight());
            awardingWindow.setBounds(this.window.getWidth() / 8, this.window.getHeight() / 8, 3 * this.window.getWidth() / 4, 3 * this.window.getHeight() / 4);
            awardingWindow.resizeContent(3 * this.window.getWidth() / 4, 3 * this.window.getHeight() / 4);
            
            content.setBounds(0, 0, width, height);
            content.repaint();
        }
    }

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

    private void createSkinAwardingWindow() {
        shadow = new Shadow();
        shadow.setBounds(0, 0, this.window.getWidth(), this.window.getHeight());
        shadow.setVisible(false);
        background.add(shadow, Integer.valueOf(2));

        awardingWindow = new SkinAwardingWindow(this.window);
        awardingWindow.setBounds(this.window.getWidth() / 8, this.window.getHeight() / 8, 3*this.window.getWidth()/4, 3*this.window.getHeight()/4); 
        awardingWindow.setVisible(false);

        background.add(awardingWindow, Integer.valueOf(3));
    }
}