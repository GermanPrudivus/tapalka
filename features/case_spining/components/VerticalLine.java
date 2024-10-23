package features.case_spining.components;

import java.awt.*;
import javax.swing.*;

// Class representing a vertical red line in the case spinning animation
public class VerticalLine extends JPanel {

    // Constructor that sets up the initial parameters of the vertical line
    public VerticalLine() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(4, getHeight() / 3));
    }

    // Method that paints the vertical red line in the center of the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(getWidth() / 2 - 2, 0, 4, getHeight());
    }
}