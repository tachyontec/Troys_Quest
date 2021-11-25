package objects;

import main.Animation;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird extends GameObject {
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public BufferedImage[] fly;
    Animation flyinganimation;

    public Bird(double worldX, double worldY, double speedx, double speedy, GamePanel gamePanel) {
        super(gamePanel.tileSize * worldX , gamePanel.tileSize * worldY , speedx, speedy);
        this.gamePanel = gamePanel;
        getBirdImage();
        flyinganimation = new Animation(2,fly);
        super.direction="run";
    }

    public void getBirdImage() {
        try {
            File path1 = new File("res/objects/Bird/Flying");
            File [] allfiles1 = path1.listFiles();
            fly = new BufferedImage[allfiles1.length];
            for(int i=0 ; i<fly.length;i++) {
                fly[i] = ImageIO.read(allfiles1[i]);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics2D g) {
        flyinganimation.drawAnimation(g,(int) this.getX(),(int) this.getY(),gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void tick() {
        if(true){
            flyinganimation.runAnimation();
            this.setX(this.getX()-3);
        }
    }
}
