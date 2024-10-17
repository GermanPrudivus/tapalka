package features.case_spining.components.skins.pink;

import features.case_spining.components.skins.Card;
import java.awt.*;
import javax.swing.*;

public class awp extends Card{

    private int radius = 60;
    public awp(JPanel skins) {
        super(skins);
        this.SKIN_IMAGE = "/Users/tyoma_cho/Desktop/tapalka/assets/skins/pink/awp.png";
        this.SKIN_NAME = "AWP | Graphite";
        this.color =  new Color(211,44,230);
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
