package objects;

import main.game.Animation;
import main.game.GamePanel;
//import sounds.Sound;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * public class Coin
 * creates coins for reward
 * Coin will have an animation for idle and one animation for player when coliding with it
 * The constructor creates the Coin instance and sets them on the map at a fixed location
 * setAnimation() creates the animation instances and gets the images for the animation
 * render() sets the Coin at a fixed location constantly and renders the animations
 * tick() dictates what our coin does at any given moment
 */
public class Coin extends StaticObject {
    //this list will contain all Coins
    public GamePanel gamePanel;
    Animation idleanimation;
    BufferedImage[] idleimages;
    //public Sound soundEffect = new Sound(); // to implement sound of ncoin when picked
    Boolean collision;

    public Coin(double worldX, double worldY,
                int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, width, height, name, gamePanel);
        this.gamePanel = gamePanel;
        this.collision = true;
    }


    @Override
    public void render(Graphics2D g2) {
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        double screenY = this.getY();
        animation.drawAnimation(g2, (int) screenX, (int) screenY,
                GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE / 2);
    }

    @Override
    public void update() {
        super.update();
    }

    public boolean checkCollision() {
        if (gamePanel.player.intersects(this)) {
            collision = true;
            gamePanel.player.setCoinsCollected(gamePanel.player.getCoinsCollected() + 1);
            //soundEffect.playSE(4);
        } else {
            collision = false;
        }
        return collision;
    }
}
