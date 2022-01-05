package objects;

import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * public class Obstacle
 * used to create and obstacle for our playuer to face in his levels
 * obstacles created in this class are stationary (@ see MonignObstacle for moving obstacles)
 * obstacles created in this class can be animated or not
 * obstacles are placed on the gamepanel our game takes place
 */
public class Obstacle extends GameObject {

    public BufferedImage[] movement;
    private String name;
    Animation animation;
    GamePanel gamePanel;


    /**
     * creates an obstacle instance
     * @param worldX the obstacles' position on the X axis of our world (@see gamepanel.MaxWorldCol * gamepanel.tileSize)
     * @param worldY the obstacles' position on the Y axis of our world (@see gamepanel.MaxWorldRow * gamepanel.tileSize)
     * @param speedX the obstacles' speed that the obstacle moves on the X axis
     * @param speedY the obstacles' speed that it moves on the Y axis
     * @param width obstacles' width
     * @param height obstacles' height
     * @param name obstacles ' name
     * @param gamePanel the gamepanel instance where our obstacle is placed and handled
     */
    public Obstacle(double worldX, double worldY, double speedX,
                    double speedY, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, speedX, speedY, width, height);
        this.name = name;
        //Image is taken automatically from the folder with same name with the object
        this.movement = Resource.getFilesInDir("res/objects/" + name);
        animation = new Animation(0 , movement);
        this.gamePanel = gamePanel;
    }

    @Override
    /**
     * renders our obstacles , ie draws them on the gamepanel
     * if in place so that obstacles with no animation and with animation handled differently
     * @param graphics2D graphics instance used to draw on screen
     *
     */
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        /*centers the player in relation to the screen in x axis,
        gp.player.screenX is used to offset the difference*/
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        /*centers the player in relation to the screen in y axis,
         gp.player.screenY is used to offset the difference*/
        double screenY = this.getY();
        if (movement.length == 1) {
            graphics2D.drawImage(movement[0], (int) screenX, (int) screenY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        } else {
            animation.drawAnimation(graphics2D, (int) screenX, (int) screenY,
                    gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    @Override
    /**
     * update() used to draw our obstacle and update it in real time
     * runs the animation of the obstacle when game is updated
     */
    public void update() {
        animation.runAnimation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}