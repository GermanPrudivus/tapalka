package features.awarding.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.SkinCard;
import constants.Constants;
import constants.Theme;

public class InspectionPanel extends JPanel {
    final Theme theme = new Theme();
    final Constants constants = new Constants();

    public InspectionPanel() {
        this.setOpaque(false);
        this.setBackground(theme.getOnBackgroundColor());
        this.setBorder(new EmptyBorder(25, 25, 25, 25));

        this.setPreferredSize(new Dimension(825, 412));
        this.setMinimumSize(new Dimension(412, 206));
        this.setMaximumSize(new Dimension(825, 412));

        this.setLayout(new GridLayout(2, 5, 10, 10));

        this.addSkinCards();
    }

    private void addSkinCards() {
        String[][] skins = constants.getSkins();

        for (String[] skin : skins) {
            this.add(new SkinCard(
                skin[0], 
                skin[1], 
                skin[2], 
                new Dimension(147, 174),
                10
            ));
        }
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }
}
