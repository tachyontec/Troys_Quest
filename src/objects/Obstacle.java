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
public class Obstacle extends StaticObject {
    boolean collision;

    //Constructor to initiate x,y,speed of the x and y axis and the sprites width,height
    public Obstacle(double worldX, double worldY, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, width, height, name, gamePanel);
        collision = false;
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
        super.update();
    }

    public boolean checkCollision() {
        if(gamePanel.player.intersects(this)) {
            collision = true;
            return true;
        }else {
            return false;
        }
    }

}