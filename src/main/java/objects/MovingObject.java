package objects;

import main.game.Animation;
import main.game.GamePanel;
import main.game.Resource;

import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class MovingObject extends GameObject {
    //MovingObject needs Game Panel to spawn on it
    public BufferedImage[] left = new BufferedImage[10];
    public BufferedImage[] right = new BufferedImage[10];
    double speedx;
    double speedy;

    Animation rightanimation;
    Animation leftanimation;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right etc.

    public MovingObject(double worldX, double worldY, int width, int height, String name,
                        GamePanel gamePanel, double speedx, double speedy)  {
        super(worldX, worldY, width, height, name, gamePanel);
        this.speedx = speedx;
        this.speedy = speedy;
        //While making the object we also determine its speed
        getMovingObjectImage();
    }

    public void getMovingObjectImage()  {
        left = Resource.getAnimationimages(this,"Left",1).toArray(new BufferedImage[0]);
        right = Resource.getAnimationimages(this,"Right",1).toArray(new BufferedImage[0]);
        leftanimation = new Animation(0, left);
        rightanimation = new Animation(0, right);
    }

    public double getSpeedx() {
        return speedx;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }
}
