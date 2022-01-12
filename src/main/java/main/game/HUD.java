package main.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * This class Implements the in-game Heads-Up Display Graphics (HUD for short)
 * It presents data without requiring users to look away from their usual viewpoints.
 * Displays Player's lives left , level coins collected , time passed since level start in seconds
 * and basic control instructions in lv1
 */

public class HUD {

    GamePanel gamepanel;
    Font gameFont;
    BufferedImage heartImage;
    BufferedImage coinImage;//image that will represent how many hearts our player will have
    BufferedImage buttons;
    BufferedImage spaceBar;
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    public double levelTimer;
    public int counter = 0;
    double deathTime;//the time the player died

    /**
     * HUD constructor
     * Initializes text font and carious images used
     *
     * @param gamepanel our main GamePanel on witch the hud is drawn
     */
    public HUD(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        this.gameFont = new Font("MV Boli", Font.PLAIN, 35);
        this.heartImage = Resource.getResourceImage("HUD", "pixelated-heart.png");
        this.coinImage = Resource.getResourceImage("objects/Coin", "tile000.png");
        this.buttons = Resource.getResourceImage("HUD", "arrowKeysTransparent.png");
        this.spaceBar = Resource.getResourceImage("HUD", "SpaceInstructions .png");
    }

    /**
     * Keeps track of time and seconds passed
     * Since game fps are stable and set to 60 , our game updates at 60 times per sec
     */
    public void update() {
        levelTimer += (double) 1 / 60;
        counter++;
    }

    /**
     * Draws the images and data necessary to display a full heads-up display
     *
     * @param g2 main game graphics passed from paintComponent method
     */
    public void render(Graphics2D g2) {
        g2.setFont(gameFont);
        g2.setColor(Color.WHITE);
        g2.drawImage(heartImage, GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE / 2 - 10,
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null); //pixelated heart drawing
        g2.drawString("x " + gamepanel.player.getLivesLeft(), 74, 50); //player's lives left
        g2.drawImage(coinImage, (GamePanel.TILE_SIZE / 2) + 3 * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE / 2 - 10,
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null); //coin image drawing
        g2.drawString("x " + gamepanel.player.getCoinsCollected(), (int) ((GamePanel.TILE_SIZE / 2) + 4.5 * GamePanel.TILE_SIZE), 50); //level coins collected
        if (counter < 600 && GamePanel.currentLevelNumber == 1) { //Only at lv1 and for around 3 seconds(200/60FPS) the controls are displayed
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            g2.drawString("MOVE", 630, 90);
            g2.drawImage(buttons, 545, 60, 120, 80, null);
            g2.drawString("ATTACK", 680, 160);
            g2.drawImage(spaceBar, 490, 85, 5 * GamePanel.TILE_SIZE, 3 * GamePanel.TILE_SIZE, null);
        }
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35));
        //Time Display based on player state
        if (gamepanel.player.getLivesLeft() == 0) {
            g2.drawString("Time:" + decFormat.format(deathTime), GamePanel.TILE_SIZE * 10, 50);
        } else {
            g2.drawString("Time:" + decFormat.format(levelTimer), GamePanel.TILE_SIZE * 10, 50);
        }
    }

}
