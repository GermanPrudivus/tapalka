package features.case_spining.components;

import components.Button;
import components.Text;
import constants.Theme;
import features.case_spining.components.skins.Card;
import java.awt.*;
import javax.swing.*;



public class FinalAnimation extends JPanel {
    public JPanel animPanel;
    private int radius = 60;
    private Button button;
    final Theme theme = new Theme();

    public FinalAnimation() {
        this.animPanel = new JPanel();  
        this.animPanel.setLayout(new BoxLayout(this.animPanel, BoxLayout.Y_AXIS));  
        this.animPanel.setOpaque(false);  
        this.animPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  
        setOpaque(false);  

       // add(Box.createVerticalGlue());  
        add(animPanel);  
        add(Box.createVerticalGlue());


    }

    public void UpdateSkinPanel(JPanel selectRandomSkin) {
        animPanel.removeAll();
        
        if (selectRandomSkin instanceof Card) {
            Card skinCard = (Card) selectRandomSkin;
            
            
            JPanel skinDisplayPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    
                    // Draw a rounded rectangle with the skin's background color below the image
                    g.setColor(skinCard.color);
                    g.fillRoundRect(getWidth() / 2, 150, 100, 100, 30, 30);
                    
    
                }
            };
    
            // Set layout and size for the skinDisplayPanel
            skinDisplayPanel.setLayout(new BoxLayout(skinDisplayPanel, BoxLayout.Y_AXIS));
            skinDisplayPanel.setPreferredSize(new Dimension(200, 300));
            skinDisplayPanel.setOpaque(false);

            skinDisplayPanel.setBackground(Color.blue);;

            Text titleLabel = new Text(skinCard.SKIN_NAME, "Montserrat-Bold.ttf", 30f);
            //titleLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            titleLabel.setForeground(Color.WHITE); 

            skinDisplayPanel.add(titleLabel);  // Add the title above
    
          
            
            skinDisplayPanel.add(Box.createVerticalStrut(20));

            ImageIcon originalIcon = new ImageIcon(skinCard.SKIN_IMAGE);
            Image originalImage = originalIcon.getImage();
            int newWidth = 300;  // Desired width
            int newHeight = 200; // Desired height
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

         
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the image
            skinDisplayPanel.add(imageLabel);
    
            skinDisplayPanel.add(Box.createVerticalStrut(0));
    
            
            animPanel.add(skinDisplayPanel);
        } else {
            System.out.println("Error: Selected skin is not an instance of Card.");
        }
    
      
        textAward();
    
        playAgain();
    

        repaint();
        revalidate();
    }


    @Override
     protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g.setColor(Color.WHITE);
    
        int borderThickness = 2; 

        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness, getHeight() - borderThickness, radius, radius);
     }



    private void playAgain() {
        button = new Button("Play Again");

        animPanel.add(Box.createVerticalStrut(20));

        animPanel.add(button);
    }

    public void textAward() {
        animPanel.add(Box.createVerticalStrut(30));

        Text textAward = new Text("You are awarded with this skin! Press “Play again” ", "Montserrat-SemiBold.ttf", 24f);
        Text textAward1 = new Text("to continue getting awards and winning!” ", "Montserrat-SemiBold.ttf", 24f);

        animPanel.add(textAward);
        animPanel.add(textAward1);

    }


    
}
