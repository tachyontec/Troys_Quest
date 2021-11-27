package objects;

import main.Animation;
import main.GamePanel;
import main.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends GameObject {
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public BufferedImage[] fly;
    Animation flyinganimation;

    public Bird(double worldX, double worldY, double speedx, double speedy, GamePanel gamePanel) {
        super(gamePanel.tileSize * worldX, gamePanel.tileSize * worldY, speedx, speedy, gamePanel.tileSize, gamePanel.tileSize);
        this.gamePanel = gamePanel;
        getBirdImage();
        flyinganimation = new Animation(2, fly);
        super.direction = "run";
    }

    public void getBirdImage() {
        fly = Resource.getFilesInDir("res/objects/Bird/Flying");
    }

    @Override
    public void render(Graphics2D g) {
        flyinganimation.drawAnimation(g, (int) this.getX(), (int) this.getY(), gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void tick() {
        flyinganimation.runAnimation();
        this.setX(this.getX() - 3);
    }
}
