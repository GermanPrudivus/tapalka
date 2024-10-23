package components;

import constants.Theme;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SkinCard extends JPanel {
    final Theme theme = new Theme();

    private String image;
    private String name;
    private Color color;

    public SkinCard(String name, String image, String color, Dimension size, int padding, Dimension imageSize, float fontSize) {
        this.setOpaque(false);
        this.setBackgorundColor(color);

        this.setPreferredSize(size);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(30, padding, 30, padding));

        this.image = image;
        this.name = name;
        this.color = this.getBackground();

        this.add(new Image(this.image, (int) imageSize.getWidth(), (int) imageSize.getHeight()), BorderLayout.NORTH);
        this.add(new Text(this.name, "Montserrat-SemiBold.ttf", fontSize), BorderLayout.SOUTH);
    }

    private void setBackgorundColor(String color) {
        Color backgroundColor = new Color(0, 0, 0);

        switch (color) {
            case "blue":
                backgroundColor = theme.getBlueSkinColor();
                break;
            case "pink":
                backgroundColor = theme.getPinkSkinColor();
                break;
            case "red":
                backgroundColor = theme.getRedSkinColor();
                break;
            case "gold":
                backgroundColor = theme.getGoldSkinColor();
                break;
            default:
                break;
        }

        this.setBackground(backgroundColor);
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public Color getColor() {
        return this.color;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
    }
}
