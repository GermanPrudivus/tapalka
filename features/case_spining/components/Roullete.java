package features.case_spining.components;
import components.SkinCard;
import constants.Constants;
import java.awt.Dimension;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public final class Roullete extends JPanel{
    final Constants constants = new Constants();

    private FinalAnimation finalAnimation;
    
    private JPanel[] skinArray;
    private Timer timer;
    private int currentIndex = 0;
    final int totalSpins = 5; // Number of complete spins
    final int spinDurationPerSkin = 800; 
    private static final int SKIN_WIDTH = 300;
    private static final int SKIN_HEIGHT = 200;
    private static final int SKIN_SPACING = 50; 
    private long startTime;
    public JPanel blurOverlay; 

    public Roullete(FinalAnimation finalAnimation) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 400));

        this.finalAnimation = finalAnimation;

        this.skinCardInitialise();  // Assign it here
        this.start();
    }

    private void setupBlurOverlay() {
        blurOverlay = new JPanel();
        blurOverlay.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black
        blurOverlay.setBounds(0, 0, 1279, 800);
        blurOverlay.setVisible(false); // Initially hidden
        mainPanel.add(blurOverlay, Integer.valueOf(1)); // Add overlay on top of the skins
    }


    public void skinCardInitialise() {
        String[][] skins = constants.getSkins();
        skinArray = new JPanel[skins.length];
        for (int i = 0; i < skins.length; i++) {
            skinArray[i] = new SkinCard(
                skins[i][0],
                skins[i][1], 
                skins[i][2], 
                new Dimension(SKIN_HEIGHT, SKIN_WIDTH),
                30,
                new Dimension(237,134),
                20f
            );
        }
    }

    public void start() {
        startTime = System.currentTimeMillis(); 
        timer = new Timer(16, e -> spin());
        timer.start();
    }

    public void spin() {
        long elapsed = System.currentTimeMillis() - startTime;
        int totalDuration = totalSpins * spinDurationPerSkin;

        int offset = (int) ((elapsed / (double) totalDuration) * (skinArray.length * (SKIN_WIDTH + SKIN_SPACING)));
        for (int i = 0; i < skinArray.length; i++) {
          
            int x = (offset + i * (SKIN_WIDTH + SKIN_SPACING)) % (skinArray.length * (SKIN_WIDTH + SKIN_SPACING));
            int y = this.getHeight() / 2 - SKIN_HEIGHT / 2; 
            
            
            if (x > this.getWidth()) {
                x -= (skinArray.length * (SKIN_WIDTH + SKIN_SPACING));
            }
            
            skinArray[i].setBounds(x, y, SKIN_WIDTH, SKIN_HEIGHT);
            this.add(skinArray[i]);
        }

        if (elapsed >= totalDuration) {
            timer.stop();
            SelectRandomSkin();
        }

        this.repaint();
        this.revalidate();
    }

    private void SelectRandomSkin() {
       Random number = new Random();
       currentIndex = number.nextInt(skinArray.length);  

        for (JPanel skin : skinArray) {
            skin.setVisible(true); 
         }


        skinArray[currentIndex].setBounds(this.getWidth() / 2 - SKIN_WIDTH / 2, this.getHeight() / 2 - SKIN_HEIGHT / 2, SKIN_WIDTH, SKIN_HEIGHT);
        skinArray[currentIndex].setVisible(true);
        showFinalPanel(skinArray[currentIndex]);
  
    }

    private void showFinalPanel(JPanel selectedSkinPanel) {
        blurOverlay.setVisible(true);
        finalAnimation.UpdateSkinPanel(selectedSkinPanel);
        finalAnimation.setVisible(true);  

        this.repaint();
        this.revalidate();
    }


    




}

    
    
    

