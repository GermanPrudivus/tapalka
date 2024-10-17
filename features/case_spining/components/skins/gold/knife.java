package features.case_spining.components.skins.gold;

import features.case_spining.components.skins.Card;
import java.awt.*;
import javax.swing.*;

public class knife extends Card{
    private int radius = 60;

    public knife(JPanel skins) {
        super(skins);
        this.SKIN_IMAGE = "/Users/tyoma_cho/Desktop/tapalka/assets/skins/gold/knife.png";
        this.SKIN_NAME = "Bayonet | Case Hardened";
        this.color =  new Color(228,174,51);
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