
package features.case_spining.components.skins;

import components.Text;
import constants.*;
import java.awt.*;
import javax.swing.*;


public class Card extends JPanel{

    final Size size = new Size();
    final JPanel skins;
    public Color color ;
    public String SKIN_IMAGE;
    public String SKIN_NAME;

    public  Card(JPanel skins) {
        this.skins = skins;
        this.skins.setLayout(new BoxLayout(this.skins, BoxLayout.Y_AXIS));
        this.add(skins, BorderLayout.CENTER);
        setOpaque(false); 
        this.skins.setOpaque(false);
    }
    
        

    public void SkinCard() {
        this.skins.setOpaque(true);
        this.skins.setLayout(new javax.swing.BoxLayout(skins, javax.swing.BoxLayout.Y_AXIS));
        this.skins.setSize((size.getWidth()/5), (size.getWidth()/5));

    }

    public void Image(String path) {

        ImageIcon imageIcon = new ImageIcon(path);
        JLabel imageLabel = new JLabel(imageIcon);
        if (imageIcon.getIconWidth() == -1) {
            System.err.println("Image not found at: " + path);
            return; 
        }

        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(size.getWidth()/6, size.getHeight()/6, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        imageLabel.setIcon(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.skins.add(imageLabel);

        this.revalidate();
        this.repaint();



    }

    public void DescriptionWeapon(String text) {
        Text label = new Text(text, "Montserrat-SemiBold.ttf", 16f);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        this.skins.add(label);
        this.revalidate();
        this.repaint();
    }

    public String getSkinImage() {
        return SKIN_IMAGE;
    }

    public String getSkinName() {
        return SKIN_NAME;
    }

    public Color getSkinColor() {
        return color;
    }

    
}
