package features;
import features.start.pages.StartPage;
import javax.swing.*;

import common.interfaces.Page;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Game {
    private static Game instance;
    
    private JFrame window = new JFrame("Tapalka");
    private int WIDTH = 1280;
    private int HEIGHT = 832;
    
    private Page currentPage;

    private void customizeWindow() {
        this.window.setMinimumSize(new Dimension(3 * WIDTH / 4, 3 * HEIGHT / 4));
        this.window.setSize(WIDTH, HEIGHT);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(true);
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
        this.currentPage.initializeContent();

        this.window.revalidate();
        this.window.repaint();
    }

    public void startGame() {
        this.customizeWindow();
        this.setCurrentPage(new StartPage(this.window));
        
        this.window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = e.getComponent().getWidth();
                int newHeight = e.getComponent().getHeight();

                currentPage.resizeContent(newWidth, newHeight);
            }
        });

        this.window.setVisible(true);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Game.getInstance().startGame();
        });
    }
}