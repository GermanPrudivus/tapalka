package features.case_spining.components;
import features.case_spining.components.skins.Card;
import features.case_spining.components.skins.blue.*;
import features.case_spining.components.skins.gold.*;
import features.case_spining.components.skins.pink.*;
import features.case_spining.components.skins.red.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public final class Roullete extends Card{
    private JPanel[] skinArray;
    private final JPanel mainPanel;
    private Timer timer;
    private int currentIndex = 0;
    final int totalSpins = 5; // Number of complete spins
    final int spinDurationPerSkin = 800; 
    private static final int SKIN_WIDTH = 300;
    private static final int SKIN_HEIGHT = 200;
    private static final int SKIN_SPACING = 50; 
    private long startTime;
    public JPanel blurOverlay; 

    public FinalAnimation finalAnimation;

    public Roullete(FinalAnimation finalAnimation) {
        super(new JPanel());
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(null);
        this.finalAnimation = finalAnimation;  // Assign it here
        InitialiseSkins();
        mainPanel.setPreferredSize(new Dimension(800, 400));
        setupBlurOverlay(); 
        start();
    }

    private void setupBlurOverlay() {
        blurOverlay = new JPanel();
        blurOverlay.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black
        blurOverlay.setBounds(0, 0, 1279, 800);
        blurOverlay.setVisible(false); // Initially hidden
        mainPanel.add(blurOverlay, Integer.valueOf(1)); // Add overlay on top of the skins
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }


    public JPanel[] InitialiseSkins() {
        skinArray = new JPanel[9];

        skinArray[0] = new knife(new JPanel());
        skinArray[1] = new p2000(new JPanel());
        skinArray[2] = new usp(new JPanel());
        skinArray[3] = new ak47(new JPanel());
        skinArray[4] = new p90(new JPanel());
        skinArray[5] = new m4a1s(new JPanel());
        skinArray[6] = new awp(new JPanel());
        skinArray[7] = new deagle(new JPanel());
        skinArray[8] = new g3sg1(new JPanel());

        for (int i = 0; i < skinArray.length; i++) {
            skinArray[i].setPreferredSize(new Dimension(SKIN_WIDTH, SKIN_HEIGHT)); 
            skinArray[i].setVisible(false); 
            mainPanel.add(skinArray[i]);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
        return skinArray;
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
            int y = mainPanel.getHeight() / 2 - SKIN_HEIGHT / 2; 
            
            
            if (x > mainPanel.getWidth()) {
                x -= (skinArray.length * (SKIN_WIDTH + SKIN_SPACING));
            }
            
            skinArray[i].setBounds(x, y, SKIN_WIDTH, SKIN_HEIGHT);
            skinArray[i].setVisible(true); 
        }

        if (elapsed >= totalDuration) {
            timer.stop();
            SelectRandomSkin();
        }

        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void SelectRandomSkin() {
       Random number = new Random();
       currentIndex = number.nextInt(skinArray.length);  

        for (JPanel skin : skinArray) {
            skin.setVisible(true); 
         }
        skinArray[currentIndex].setBounds(mainPanel.getWidth() / 2 - SKIN_WIDTH / 2, mainPanel.getHeight() / 2 - SKIN_HEIGHT / 2, SKIN_WIDTH, SKIN_HEIGHT);
        skinArray[currentIndex].setVisible(true);
        showFinalPanel(skinArray[currentIndex]);
  
    }

    private void showFinalPanel(JPanel selectedSkinPanel) {
        blurOverlay.setVisible(true);
        finalAnimation.UpdateSkinPanel(selectedSkinPanel);
        finalAnimation.setVisible(true);  

        mainPanel.repaint();
        mainPanel.revalidate();
    }
    




}

    
    
    

