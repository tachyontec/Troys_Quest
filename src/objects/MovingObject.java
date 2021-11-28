package objects;

import main.Animation;
import main.GamePanel;
import main.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

//A game object that is moving towards the Player
public class MovingObject extends GameObject {
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public String name; //name of the object
    public BufferedImage[] movement; //images with the movement of the object
    Animation animation;
    int speed;

    public MovingObject(double worldX, double worldY, double speedx, double speedy, GamePanel gamePanel, String name, int animationspeed, int speed) {
        super(gamePanel.tileSize * worldX, gamePanel.tileSize * worldY, speedx, speedy, gamePanel.tileSize, gamePanel.tileSize);
        this.name = name;
        movement = Resource.getFilesInDir("res/objects/" + this.name); //get images for the animation
        this.gamePanel = gamePanel;
        //While making the object we also determine its speed
        this.speed = speed;
        animation = new Animation(speed, movement);
        super.direction = "run";
    }

    @Override
    public void render(Graphics2D g) {
        animation.drawAnimation(g, (int) this.getX(), (int) this.getY(), gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void tick() {
        animation.runAnimation();
        this.setX(this.getX() - speed);
    }
}
