package components;

import javax.swing.*;
import java.awt.*;

public class Shadow extends JPanel {
    
    public Shadow() {
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 0, 100));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
