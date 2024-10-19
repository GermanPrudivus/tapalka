import features.case_spining.pages.CaseSpinning;
import javax.swing.*;

class Game {
    final JFrame window = new JFrame("Tapalka");

    public void startGame() {
        //
        new CaseSpinning(window);
    }



    public static void main(String[] args){
        new Game().startGame();
        
    }
}