package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HUD {

    GamePanel gamepanel;
    Font gameFont;
    BufferedImage heartImage;
    public HUD(GamePanel gamepanel ){
        this.gamepanel = gamepanel;
        this.gameFont = new Font("MV Boli", Font.PLAIN, 40);
        try {
            this.heartImage = ImageIO.read(new File("res/HUD/pixelated-heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(gameFont);
        g2.setColor(Color.BLACK);
        g2.drawImage(heartImage , gamepanel.tileSize / 2 , gamepanel.tileSize / 2 - 10, gamepanel.tileSize , gamepanel.tileSize , null );
        g2.drawString("x " + gamepanel.player.getLivesLeft() , 74 ,50);
    }

}
