package objects;

import main.*;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
    public BufferedImage[] left;
    public BufferedImage[] right;
    public enum State {RIGHT, LEFT}
    //state stores the current state of the movingobstacle
    public State state;
    Animation leftAnimation;
    Animation rightAnimation;
    public String name;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right etc.

    public MovingObstacle(double worldX, double worldY, double speedx,
                          double speedy, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, speedx, speedy, width, height);
        this.gamePanel = gamePanel;
        this.name = name;
        state = State.LEFT;
        //While making the object we also determine its speed
        getMovingObstacleImage();
    }

    @Override
    //paint Bird left animation if flys left else paints right animation
    public void render(Graphics2D g) {
        super.render(g);
        //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference
        double screenY = this.getY() - gamePanel.player.getY() + gamePanel.player.screenY;
        if (state == State.LEFT) {
            g.drawImage(left[0], (int) screenX, (int) screenY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        } else if (state == State.RIGHT){
            //This code is used to draw an Image rotated
            double rotationRequired = Math.toRadians (180); //We want to switch direction from left to right
            double locationX = left[0].getWidth() / 2;
            double locationY = left[0].getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            //final draw:
            g.drawImage(op.filter(left[0],null), (int) screenX, (int) screenY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }

    }

    public void getMovingObstacleImage() {
        left = Resource.getFilesInDir("res/objects/Arrow/Left");
        leftAnimation = new Animation(0, left);
        right = Resource.getFilesInDir("res/objects/Arrow/Right");
        rightAnimation = new Animation(0, right);
    }

    @Override
    public void tick() {
        if(state == State.LEFT) {
            setX(getX() - getSpeedx());
        } else if (state == State.RIGHT) {
            setX(getX() + getSpeedx());
        }

        checkcollision();

    }

    //in this method we check the collision of the obstacle with the bounds,if the collide the bird changes direction,animation
    public void checkcollision() {
        if (Bound.start.intersects(this)) {
            state = State.RIGHT;
        } else if (Bound.end.intersects(this)) {
            state = State.LEFT;
        }
    }
}



