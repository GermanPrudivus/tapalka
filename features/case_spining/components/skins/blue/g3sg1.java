package features.case_spining.components.skins.blue;

import features.case_spining.components.skins.Card;
import java.awt.*;
import javax.swing.*;


public class g3sg1 extends Card{
    private int radius = 60;

    public g3sg1(JPanel skins) {
        super(skins);
        this.SKIN_IMAGE = "/Users/tyoma_cho/Desktop/tapalka/assets/skins/blue/g3sg1.png";
        this.SKIN_NAME = "G3SG1 | Demeter";
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
