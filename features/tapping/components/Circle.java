package features.tapping.components;

import common.Navigator;
import components.Text;
import constants.Theme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

// Target class (Creates a circle that should be tapped)
public class Circle extends JPanel {
    final Theme theme = new Theme();

    private JFrame window;
    private JPanel panel;

    private Counter counter;
    private GameTimer timer;
    private Random random = new Random();

    private int x;
    private int y;

    private JLabel plusOneLabel;
    private Timer plusOneTimer;

    // Sets the Circle parameters and stores necessary references
    public Circle(JFrame window, JPanel panel, Counter counter, GameTimer timer) {
        this.window = window;
        this.panel = panel;

        this.counter = counter;
        this.timer = timer;
        
        this.setOpaque(false);
        this.setSize(new Dimension(75, 75));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onCircleClick();
            }
        });
    }

    // Adds the circle to the tapping panel
    public void addCircleToPanel() {
        this.setCirclePosition();
        this.panel.add(this);
    }

    // Sets random coordinates for the circle
    private void setCirclePosition() {
        x = random.nextInt(panel.getWidth() - 2 * getWidth()) + getWidth();
        y = random.nextInt(panel.getHeight() - 2 * getHeight()) + getHeight();
    
        this.setBounds(x, y, getWidth(), getHeight());
    }

    // Updates the state of the page when the circle is clicked
    private void onCircleClick() {
        int coinCount = this.counter.getCount() + 1;

        if (coinCount == 30) {
            this.timer.stopTimer();
            new Navigator(this.window).navigateToAwardingPage();
        } else {
            counter.setCount(coinCount);

            SwingUtilities.invokeLater(() -> {
                this.showPlusOneText(x, y);
                this.setCirclePosition();
            });
        }
    }

    // Adds a visual hit indicator for the user
    private void showPlusOneText(int x, int y) {
        if (plusOneLabel != null && plusOneTimer != null) {
            plusOneTimer.stop();
            panel.remove(plusOneLabel);
        }

        // Creates a hit indicator for the circle
        plusOneLabel = new Text("+1", "Montserrat-SemiBold.ttf", 50f);
        plusOneLabel.setBounds(x, y, 100, 100);
        panel.add(plusOneLabel);

        // Animation for the indicator
        plusOneTimer = new Timer(40, new ActionListener() {
            int opacity = 255;
            int yPosition = y;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 10;
                yPosition -= 2;

                if (opacity <= 0) {
                    plusOneTimer.stop();
                    panel.remove(plusOneLabel);
                } else {
                    plusOneLabel.setForeground(new Color(255, 255, 255, opacity));
                    plusOneLabel.setLocation(x, yPosition);
                }

                SwingUtilities.invokeLater(() -> {
                    panel.repaint();
                });
            }
        });

        plusOneTimer.start();
    }

    // Paints the circle
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(theme.getOnBackgroundColor());

        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2d.fillOval(x, y, diameter, diameter);
        g2d.dispose();
    }
}