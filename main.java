import features.start.pages.StartPage;

class Game {

    public void startGame() {
        new StartPage().openStartPage();
    }

    public static void main(String[] args){
        new Game().startGame();
    }
}