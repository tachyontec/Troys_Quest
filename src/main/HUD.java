package main;

import objects.Coin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class HUD {

    GamePanel gamepanel;
    Font gameFont;
    BufferedImage heartImage;
    BufferedImage coinImage;//image that will represent how many hearts our player will have
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    double levelTimer;
    double deathTime;//the time the player died


    public HUD(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        this.gameFont = new Font("MV Boli", Font.PLAIN, 35);
        this.heartImage = Resource.getResourceImage("HUD","pixelated-heart");
        this.coinImage = Resource.getResourceImage("objects/Coin","tile000");
    }

    public void draw(Graphics2D g2) {
        g2.setFont(gameFont);
        g2.setColor(Color.BLACK);
        g2.drawImage(heartImage, gamepanel.tileSize / 2, gamepanel.tileSize / 2 - 10, gamepanel.tileSize, gamepanel.tileSize, null);
        g2.drawString("x " + gamepanel.player.getLivesLeft(), 74, 50);
        g2.drawImage(coinImage,(gamepanel.tileSize / 2) + 3 * gamepanel.tileSize, gamepanel.tileSize / 2 - 10, gamepanel.tileSize, gamepanel.tileSize,null);
        g2.drawString("x " + Coin.coinCollectionCounter, (int) ((gamepanel.tileSize / 2) + 4.5 * gamepanel.tileSize), 50);
        levelTimer += (double) 1 / 60;
        if (gamepanel.player.getLivesLeft() == 0) {
            g2.drawString("Time:" + decFormat.format(deathTime), gamepanel.tileSize * 10, 50);
        } else {
            g2.drawString("Time:" + decFormat.format(levelTimer), gamepanel.tileSize * 10, 50);
        }
    }

}
