package features.tapping.pages;

import common.interfaces.Page;
import components.Header;
import components.Lighting;
import components.Text;
import constants.Theme;
import features.tapping.components.Circle;
import features.tapping.components.Counter;
import features.tapping.components.GameTimer;
import features.tapping.components.TappingPanel;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Tapping game page (Main gameplay page)
public class TappingPage implements Page {
    final Theme theme = new Theme();

    private JFrame window;

    private JLayeredPane background;
    private Lighting lighting1;
    private Lighting lighting2;
    private JPanel content;

    private GameTimer timer;
    private Counter counter;

    private TappingPanel tappingPanel;
    private Circle circle;

    // Sets the background for the page
    public TappingPage(JFrame window) {
        background = new JLayeredPane();
        background.setSize(new Dimension(window.getWidth(), window.getHeight()));
        background.setBackground(theme.getBackgroundColor());
        background.setOpaque(true);
        this.window = window;
    }

    @Override
    public void initializeContent() {
        int width = background.getWidth();
        int height = background.getHeight();

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

            content.setBounds(0, 0, width, height);
            content.repaint();
        }
    }

    // Adds main content to the previously created background
    private JPanel createMainContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(70, 70, 70, 70));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Creates the header for the page
        Header header = new Header();
        header.setHorizontalAlignment(SwingConstants.CENTER);

        header.setPreferredSize(new Dimension(this.window.getWidth(), 50));
        header.setMinimumSize(new Dimension(this.window.getWidth(), 50));
        header.setMaximumSize(new Dimension(this.window.getWidth(), 50));

        content.add(header);
        
        // Adds a panel with the timer and coin counter
        content.add(Box.createRigidArea(new Dimension(0, 40)));
        content.add(this.indicatorsPanel());
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adds the interactive game panel
        tappingPanel = new TappingPanel();
        circle = new Circle(this.window, tappingPanel, this.counter, this.timer);

        SwingUtilities.invokeLater(() -> {
            circle.addCircleToPanel();
        });

        content.add(tappingPanel);
        return content;
    }

    // Creates the panel with indicators: timer and coin counter
    private JPanel indicatorsPanel() {
        JPanel indicators = new JPanel();
        indicators.setOpaque(false);
        indicators.setLayout(new BoxLayout(indicators, BoxLayout.X_AXIS));

        timer = new GameTimer(this.window);
        indicators.add(timer);
        indicators.add(new Text(" seconds left", "Montserrat-SemiBold.ttf", 26f));

        indicators.add(Box.createHorizontalGlue());

        counter = new Counter();
        indicators.add(counter);
        indicators.add(new Text(" coins", "Montserrat-SemiBold.ttf", 26f));

        return indicators;
    }
}