package objects;

import main.game.Animation;
import main.game.GamePanel;
import main.game.Resource;

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
    public Obstacle(double worldX, double worldY, int width, int height, String name, GamePanel gamePanel, int numofimages) {
        super(worldX, worldY, width, height, name, gamePanel, numofimages);
        collision = false;
    }

    @Override
    public void getStaticObjectImage() {
        images = Resource.getAnimationimages(this, "Idle", numofImages).toArray(new BufferedImage[0]);
        animation = new Animation(0, images);
    }

    /**
     * renders our obstacles , ie draws them on the gamepanel
     * if in place so that obstacles with no animation and with animation handled differently
     *
     * @param graphics2D graphics instance used to draw on screen
     */
    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
    }

    /**
     * update() used to draw our obstacle and update it in real time
     * runs the animation of the obstacle when game is updated
     */
    @Override
    public void update() {
        super.update();
    }

    public boolean checkCollision() {
        if (gamePanel.player.intersects(this)) {
            collision = true;
            return true;
        } else {
            return false;
        }
    }

}