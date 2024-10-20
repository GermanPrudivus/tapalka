package common;

import features.Game;
import features.awarding.pages.AwardingPage;
import features.loss.pages.LossPage;
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
        switch (e.getActionCommand()) {
            case "Start a new game":
                this.navigateToTappingPage();
                break;
            case "Try again":
                this.navigateToTappingPage();
                break;
            case "FreeSkin":
                this.navigateToTappingPage();
                break;
            default:
                break;
        }
    }

    private void navigateToTappingPage() {
        this.window.getContentPane().removeAll();
        Game gameInstance = Game.getInstance();
        gameInstance.setCurrentPage(new TappingPage(window));
    }

    public void navigateToLossPage() {
        this.window.getContentPane().removeAll();
        Game gameInstance = Game.getInstance();
        gameInstance.setCurrentPage(new LossPage(window));
    }

    public void navigateToAwardingPage() {
        this.window.getContentPane().removeAll();
        Game gameInstance = Game.getInstance();
        gameInstance.setCurrentPage(new AwardingPage(window));
    }

    public void navigateToRulletePage() {
        this.window.getContentPane().removeAll();
        this.window.revalidate();
        this.window.repaint();
    }
}
