import features.start.pages.StartPage;

import javax.swing.*;

class Game {
    private JFrame window = new JFrame("Tapalka");

    public void startGame() {
        new StartPage(window).openStartPage();
    }

    public static void main(String[] args){
        new Game().startGame();
    }
}