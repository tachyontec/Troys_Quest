package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class HUD {

    GamePanel gamepanel;
    Font gameFont;
    BufferedImage heartImage;
    BufferedImage coinImage;//image that will represent how many hearts our player will have
    BufferedImage buttons;
    BufferedImage spacebar;
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    public double levelTimer;
    public int counter = 0;
    double deathTime;//the time the player died


    public HUD(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        this.gameFont = new Font("MV Boli", Font.PLAIN, 35);
        this.heartImage = Resource.getResourceImage("HUD","pixelated-heart");
        this.coinImage = Resource.getResourceImage("objects/Coin","tile000");
        this.buttons = Resource.getResourceImage("HUD" , "arrowKeys Transparet");
        this.spacebar = Resource.getResourceImage("HUD" , "SpaceInstructions ");
    }

    public void update(){
        levelTimer += (double) 1 / 60;
        counter++;
    }


    public void render(Graphics2D g2) {
        g2.setFont(gameFont);
        g2.setColor(Color.WHITE);
        g2.drawImage(heartImage, gamepanel.tileSize / 2, gamepanel.tileSize / 2 - 10, gamepanel.tileSize, gamepanel.tileSize, null);
        g2.drawString("x " + gamepanel.player.getLivesLeft(), 74, 50);
        g2.drawImage(coinImage,(gamepanel.tileSize / 2) + 3 * gamepanel.tileSize, gamepanel.tileSize / 2 - 10, gamepanel.tileSize, gamepanel.tileSize,null);
        g2.drawString("x " + gamepanel.player.getCoinsCollected(), (int) ((gamepanel.tileSize / 2) + 4.5 * gamepanel.tileSize), 50);
        if(counter < 200 && GamePanel.currentLevel == 1){
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            g2.drawString("MOVE" , 630 , 90);
            g2.drawImage(buttons , 545 , 60 , 120 , 80 , null );
            g2.drawString("ATTACK" , 680 , 160);
            g2.drawImage(spacebar , 490 , 85 , 5 * gamepanel.tileSize , 3 * gamepanel.tileSize , null );
        }
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35));
        if (gamepanel.player.getLivesLeft() == 0) {
            g2.drawString("Time:" + decFormat.format(deathTime), gamepanel.tileSize * 10, 50);
        } else {
            g2.drawString("Time:" + decFormat.format(levelTimer), gamepanel.tileSize * 10, 50);
        }
    }

}
