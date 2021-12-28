package objects;

import main.*;


import java.awt.*;
import java.awt.image.BufferedImage;

//A game object that is moving towards the Player
public class Bird extends GameObject {
    //MovingObstacle needs Game Panel to spawn on it
    public GamePanel gamePanel;
    public BufferedImage[] left;
    public BufferedImage[] right;
    Animation leftAnimation;
    Animation rightanimation;
    public enum Direction {LEFT, RIGHT}
    public Direction direction;
    public String name;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.

    public Bird(double worldX, double worldY, double speedx, double speedy, int width, int height, GamePanel gamePanel, String name) {
        super(worldX, worldY, speedx, speedy, width, height);
        this.gamePanel = gamePanel;
        this.direction = Direction.LEFT;
        this.name = name;
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
        switch (direction) {
            case LEFT -> leftAnimation.drawAnimation(g, (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize);
            case RIGHT -> rightanimation.drawAnimation(g, (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    public void getMovingObstacleImage() {
        left = Resource.getFilesInDir("res/objects/"+ name +"/Left" );
        right = Resource.getFilesInDir("res/objects/"+ name +"/Right" );
        rightanimation = new Animation(0,right);
        leftAnimation = new Animation(0,left);
    }

    @Override
    public void tick() {
        checkcollision();
        switch (direction) {
            case LEFT :
                leftAnimation.runAnimation();
                setX(getX() - getSpeedx());
                break;
            case RIGHT :
                rightanimation.runAnimation();
                setX(getX() + getSpeedx());
                break;
        }
    }
    //in this method we check the collision of the bird with the bounds,if the collide the bird changes direction,animation
    public void checkcollision() {
        if (Bound.start.intersects(this)) {
            direction = Direction.RIGHT;
        } else if(Bound.end.intersects(this)) {
            direction = Direction.LEFT;
        }

    }
}
