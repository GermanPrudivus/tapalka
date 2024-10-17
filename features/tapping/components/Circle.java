package features.tapping.components;

import java.util.Random;

import java.awt.*;
import javax.swing.*;

import common.Navigator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import constants.Theme;

public class Circle extends JPanel {
    final Theme theme = new Theme();

    private JFrame window;
    private JPanel panel;
    private Counter counter;
    private GameTimer timer;
    private Random random = new Random();

    private int x;
    private int y;

    public Circle(JFrame window, JPanel panel, Counter counter, GameTimer timer) {
        this.window = window;
        this.panel = panel;
        this.counter = counter;
        
        this.setOpaque(false);
        this.setSize(new Dimension(75, 75));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Circle clicked!");
                onCircleClick();
            }
        });
    }

    public void addCircleToPanel() {
        System.out.println("size x: " + panel.getWidth());
        System.out.println("size y: " + panel.getHeight());

        x = random.nextInt(panel.getWidth() - 2 * getWidth()) + getWidth();
        y = random.nextInt(panel.getHeight() - 2 * getHeight()) + getHeight();

        System.out.println();
        System.out.println("x: " + x);
        System.out.println("y: " + y);

        this.setBounds(x, y, getWidth(), getHeight());
        panel.add(this);
    }

    private void onCircleClick() {
        int coinCount = this.counter.getCount() + 1;
        System.out.println(coinCount);

        if (coinCount == 30) {
            new Navigator(this.window).navigateToLosePage();
        } else {
            counter.setCount(coinCount);
        }
    }

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
