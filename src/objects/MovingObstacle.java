package objects;

import main.*;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * public class MovingObstacle
 * creates obstacles that move and can kill our player
 * setAnimation() creates the animation instances and gets the images for the animation
 * render() sets the movingobstacle at a fixed location constantly and renders the animations
 * tick() dictates what movingobstacle does at any given moment
 */

//A game object that is moving towards the Player
public class MovingObstacle extends GameObject {
    //MovingObstacle needs Game Panel to spawn on it
    public GamePanel gamePanel;
    public BufferedImage[] movement;

    Animation animation;
    public String name;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right etc.

    public MovingObstacle(double worldX, double worldY, double speedx,
                          double speedy, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, speedx, speedy, width, height);
        this.gamePanel = gamePanel;
        this.name = name;
        //While making the object we also determine its speed
        getMovingObstacleImage();
    }

    @Override
    //paint Bird left animation if flys left else paints right animation
    public void render(Graphics2D g) {
        super.render(g);
        /*centers the player in relation to the screen in x axis,
        gp.player.screenX is used to offset the difference*/
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        /*centers the player in relation to the screen in y axis,
        gp.player.screenY is used to offset the difference*/
        double screenY = this.getY();
        animation.drawAnimation(g , (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize);
    }

    public void getMovingObstacleImage() {
        movement = Resource.getFilesInDir("res/objects/Arrow/Left");
        animation = new Animation(0, movement);
    }

    @Override
    public void update() {
        animation.runAnimation();
        setX(getX() - getSpeedx());
    }
}