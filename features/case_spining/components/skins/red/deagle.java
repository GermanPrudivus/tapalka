package features.case_spining.components.skins.red;

import features.case_spining.components.skins.Card;
import java.awt.*;
import javax.swing.*;

public class deagle extends Card{

    private int radius = 60;

    public deagle(JPanel skins) {
        super(skins);
        this.SKIN_IMAGE = "/Users/tyoma_cho/Desktop/tapalka/assets/skins/red/deagle.png";
        this.SKIN_NAME = "Desert Eagle | Golden Koi";
        this.color =  new Color(235,75,75);
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
