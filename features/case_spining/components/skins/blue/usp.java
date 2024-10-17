package features.case_spining.components.skins.blue;

import features.case_spining.components.skins.Card;
import java.awt.*;
import javax.swing.*;


public class usp extends Card{
    private int radius = 60;
  
    public usp(JPanel skins) {
        super(skins);
        this.SKIN_IMAGE = "/Users/tyoma_cho/Desktop/tapalka/assets/skins/blue/usps.png";
        this.SKIN_NAME = "USP-S | Overgrowth";
        this.color =  new Color(75,105,255);
        skins.setBackground(this.color);
        Image(this.SKIN_IMAGE);
        DescriptionWeapon(this.SKIN_NAME);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color); 
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

    }

}
