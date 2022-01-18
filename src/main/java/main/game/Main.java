package main.game;

import tiles.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Troy's Quest");
        GamePanel gamePanel = new GamePanel();
        BufferedImage icon = null;
        try {
            icon = ImageIO.read(gamePanel.getClass().getResourceAsStream("Troys_quest_logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setIconImage(icon);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.gameState = GamePanel.MENU_STATE;
        TileManager tileManager = new TileManager(gamePanel);
        gamePanel.setUpGame(tileManager);
        gamePanel.startGameThread();
    }
}
