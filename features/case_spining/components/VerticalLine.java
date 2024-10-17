package features.case_spining.components;

import constants.Size;
import java.awt.*;
import javax.swing.*;

public class VerticalLine extends JPanel{
    Size size = new Size();

    public VerticalLine() {
        this.setOpaque(false);
        this.setBackground(Color.green);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); 
        g.fillRect(getWidth() / 2 - 3, 0, 4, getHeight()); // Draw a vertical line
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(6, size.getHeight()/3); // Width of the line, height can be adjusted
    }
}
