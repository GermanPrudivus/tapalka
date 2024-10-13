import features.case_spining.pages.CaseSpinning;
import javax.swing.*;

import constants.Size;

class Game {
    private JFrame window = new JFrame("Tapalka");
    final Size size = new Size();

    private void customizeWindow() {
        this.window.setSize(size.getWidth(), size.getHeight());
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startGame() {
        this.customizeWindow();
        new StartPage(window).initializeContent();
        this.window.setVisible(true);
    }



    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new Game().startGame();
        });
    }
}