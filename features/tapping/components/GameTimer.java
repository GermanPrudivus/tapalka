package features.tapping.components;

import common.CustomFontLoader;
import common.Navigator;
import constants.Theme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Game timer (Controls the duration of the game)
public class GameTimer extends JLabel implements ActionListener {
    final Theme theme = new Theme();

    private int secondsLeft = 60;
    private Timer timer = new Timer(1000, this);

    private JFrame window;

    // Sets timer parameters and starts it
    public GameTimer(JFrame window) {
        this.setText(Integer.toString(secondsLeft));
        this.setFont(CustomFontLoader.loadFont("assets/font/Montserrat-Bold.ttf", 26f));

        this.setForeground(theme.getOnBackgroundColor());
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.window = window;
        this.timer.start();
    }

    public void stopTimer() {
        this.timer.stop();
    }

    // Updates the timer's state each second
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            secondsLeft--;
            this.setText(Integer.toString(secondsLeft));
        });

        if (secondsLeft == 0) {
            new Navigator(this.window).navigateToLossPage();
            this.timer.stop();
        }
    }
}