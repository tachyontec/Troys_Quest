package main.game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Troy's Quest");
        ImageIcon icon = new ImageIcon("res/Backgrounds/icon.png");
        window.setIconImage(icon.getImage());
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.gameState = GamePanel.MENU_STATE;
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
