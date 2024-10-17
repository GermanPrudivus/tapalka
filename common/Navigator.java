package common;

import features.Game;
import features.tapping.pages.TappingPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Navigator implements ActionListener {
    public JFrame window;

    public Navigator(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Start a new game")) {
            this.navigateToTappingPage();
        }
    }

    private void navigateToTappingPage() {
        this.window.getContentPane().removeAll();
        Game gameInstance = Game.getInstance();
        gameInstance.setCurrentPage(new TappingPage(window));
    }

    public void navigateToLosePage() {
        this.window.getContentPane().removeAll();
        this.window.revalidate();
        this.window.repaint();
    }
}
