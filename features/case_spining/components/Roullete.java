package features.case_spining.components;

import components.Shadow;
import components.SkinCard;
import constants.Constants;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

// Roulette animation (Roulette spinning animation)
public final class Roullete extends JPanel {
    final Constants constants = new Constants();

    final int SKIN_WIDTH = 300;
    final int SKIN_HEIGHT = 200;
    final int SKIN_SPACING = 50;
    final int SPIN_DURATION = 5000;
    private int totalSpins = 5;

    private SkinAwardingWindow awardingWindow;
    private Shadow shadow;
    
    private JPanel[] skinArray;
    private Timer timer;
    private long startTime;
    private int offset;

    // Sets the Roulette parameters and stores necessary references
    public Roullete(SkinAwardingWindow awardingWindow, Shadow shadow) {
        this.setLayout(null);
        this.setOpaque(false);

        this.skinCardInitialise();
        this.startAnimation();

        this.awardingWindow = awardingWindow;
        this.shadow = shadow;
    }

    // Adds all Skins to the Roulette and shuffles them
    private void skinCardInitialise() {
        String[][] skins = constants.getSkins();
        List<JPanel> skinList = new ArrayList<>();
    
        for (int i = 0; i < skins.length; i++) {
            SkinCard skinCard = new SkinCard(
                skins[i][0],
                skins[i][1], 
                skins[i][2], 
                new Dimension(SKIN_HEIGHT, SKIN_WIDTH),
                30,
                new Dimension(237, 134),
                20f
            );
            skinList.add(skinCard);
        }
    
        Collections.shuffle(skinList);
        skinArray = skinList.toArray(new JPanel[0]);
    }

    // Starts the spin animation
    private void startAnimation() {
        startTime = System.currentTimeMillis(); 
        timer = new Timer(16, e -> spinAnimation());
        timer.start();
    }

    // Spin animation logic
    private void spinAnimation() {
        long elapsed = System.currentTimeMillis() - startTime;

        int totalDistance = calculateTotalDistance();

        // Progress of the animation (0 to 1 based on duration)
        double progress = Math.min(1.0, (double) elapsed / SPIN_DURATION);
        offset = (int) ((1 - Math.pow(1 - progress, 3)) * totalDistance); // Use easing

        for (int i = 0; i < skinArray.length; i++) {
            int x = (offset + i * (SKIN_WIDTH + SKIN_SPACING)) % (skinArray.length * (SKIN_WIDTH + SKIN_SPACING));
            int y = this.getHeight() / 2 - SKIN_HEIGHT / 2;

            if (x > this.getWidth()) {
                x -= (skinArray.length * (SKIN_WIDTH + SKIN_SPACING));
            }

            skinArray[i].setBounds(x, y, SKIN_WIDTH, SKIN_HEIGHT);
            this.add(skinArray[i]);
        }

        // Stops the animation when progress reaches 1.0
        if (progress >= 1.0) {
            timer.stop();
            selectSkinInCenter();
        }

        this.repaint();
        this.revalidate();
    }

    // This method is used to calculate the total distance for the spin
    private int calculateTotalDistance() {
        int fullSpinDistance = skinArray.length * (SKIN_WIDTH + SKIN_SPACING);
        return totalSpins * fullSpinDistance + SKIN_WIDTH / 2;
    }

    // Identifies which skin is in the center of the screen
    private void selectSkinInCenter() {
        int closestIndex = -1;
        int minDistanceToCenter = Integer.MAX_VALUE;

        // Loops through all skins to find the one closest to the center
        for (int i = 0; i < skinArray.length; i++) {
            Rectangle bounds = skinArray[i].getBounds();
            int skinCenterX = bounds.x + bounds.width / 2;

            // Calculates the distance from the skin's center to the center of the screen
            int distanceToCenter = Math.abs(skinCenterX - (getWidth() / 2));

            // Tracks the skin closest to the center
            if (distanceToCenter < minDistanceToCenter) {
                minDistanceToCenter = distanceToCenter;
                closestIndex = i;
            }
        }

        // If a skin is found in the center, selects it and passes it to the awarding window
        if (closestIndex != -1) {
            SkinCard selectedSkinPanel = (SkinCard) skinArray[closestIndex];
            System.out.println("Skin in center: " + selectedSkinPanel.getName());

            showFinalPanel(selectedSkinPanel);
        }
    }

    // Updates the awarding window with the selected skin
    private void showFinalPanel(SkinCard selectedSkinPanel) {
        shadow.setVisible(true);
        awardingWindow.setVisible(true);

        awardingWindow.createContent(selectedSkinPanel);

        this.revalidate();
        this.repaint();
    }
}