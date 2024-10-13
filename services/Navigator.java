package services;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

import features.tapping.pages.TappingPage;

public class Navigator implements ActionListener{
    public JFrame window;

    public Navigator(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("Start a new game")) {
            this.navigateToTappingPage();
        }
    }

    private void navigateToTappingPage() {
        this.window.getContentPane().removeAll();
        new TappingPage(window).initializeContent();
        this.window.revalidate();
        this.window.repaint();
    }
}
