package features.case_spining.components;

import java.awt.*;
import javax.swing.*;

public class VerticalLine extends JPanel{

    public VerticalLine() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(4, getHeight()/3));
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); 
        g.fillRect(getWidth() / 2 - 2, 0, 4, getHeight());
    }
}
