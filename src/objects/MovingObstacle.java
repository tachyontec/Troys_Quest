package objects;

import main.*;


import java.awt.*;
import java.awt.image.BufferedImage;

//A game object that is moving towards the Player
public class MovingObstacle extends GameObject {
    //MovingObstacle needs Game Panel to spawn on it
    public GamePanel gamePanel;
    public BufferedImage[] leftHandSide;
    public BufferedImage[] rightHandSide;
    Animation leftAnimation;
    Animation rightanimation;
    public enum Direction {LEFT, RIGHT};
    public Direction direction;
    public String name;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.

    public MovingObstacle(double worldX, double worldY, double speedx, double speedy, int width, int height, GamePanel gamePanel, String name) {
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
        switch (direction) {
            case LEFT -> leftAnimation.drawAnimation(g, (int) this.getX(), (int) this.getY(), gamePanel.tileSize, gamePanel.tileSize);
            case RIGHT -> rightanimation.drawAnimation(g, (int) this.getX(), (int) this.getY(), gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    public void getMovingObstacleImage() {
        leftHandSide = Resource.getFilesInDir("res/objects/"+ name +"/Left" );
        rightHandSide = Resource.getFilesInDir("res/objects/"+ name +"/Right" );
        rightanimation = new Animation(0 , rightHandSide);
        leftAnimation = new Animation(0 , leftHandSide);
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



