package components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Lighting extends JPanel {
    private Color color;
    private Point2D position;
    private int radiusFactor = 1;

    public Lighting(Color color, Point2D position, int radiusFactor) {
        this.color = color;
        this.position = position;
        this.radiusFactor = radiusFactor;

        setOpaque(false);
    }

    public Lighting(Color color, Point2D position) {
        this.color = color;
        this.position = position;

        setOpaque(false);
    }

    public void setPosition(Point2D position) {
        this.position = position;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        float[] dist = {0.0f, 0.6f, 1.0f};
        Color[] colors = {
            new Color(color.getRed(), color.getGreen(), color.getBlue(), 100),
            new Color(color.getRed(), color.getGreen(), color.getBlue(), 30),
            new Color(0, 0, 0, 0)
        };

        RadialGradientPaint radialGradient = new RadialGradientPaint(position, getHeight() / this.radiusFactor, dist, colors);
        g2d.setPaint(radialGradient);

        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }
}