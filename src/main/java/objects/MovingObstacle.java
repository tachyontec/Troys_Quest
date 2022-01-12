package objects;

import main.game.GamePanel;

import java.awt.*;

/**
 * public class MovingObstacle
 * creates obstacles that move and can kill our player
 * setAnimation() creates the animation instances and gets the images for the animation
 * render() sets the movingobstacle at a fixed location constantly and renders the animations
 * tick() dictates what movingobstacle does at any given moment
 */

//A game object that is moving towards the Player
public class MovingObstacle extends MovingObject {

    public MovingObstacle(double worldX, double worldY, double speedx,
                          double speedy, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, width, height, name, gamePanel, speedx, speedy);
    }

    @Override
    //paint Bird left animation if flys left else paints right animation
    public void render(Graphics2D g) {
        /*centers the player in relation to the screen in x axis,
        gp.player.screenX is used to offset the difference*/
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        /*centers the player in relation to the screen in y axis,
        gp.player.screenY is used to offset the difference*/
        double screenY = this.getY();
        leftanimation.drawAnimation(g, (int) screenX, (int) screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    @Override
    public void update() {
        leftanimation.runAnimation();
        setX(getX() - getSpeedx());
    }
}