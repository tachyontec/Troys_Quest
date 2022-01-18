package objects;


import main.game.Animation;
import main.game.GamePanel;
import main.game.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * public class Heart
 * used to create a Heart for our player to increase his lives
 */
public class Heart extends StaticObject {

    public Heart(double worldX, double worldY, int width, int height, String name, GamePanel gamePanel, int numofimages) {
        super(worldX, worldY, width, height, name, gamePanel, numofimages);
    }

    @Override
    public void getStaticObjectImage() {
        images = Resource.getAnimationimages(this, "Idle", numofImages).toArray(new BufferedImage[0]);
        animation = new Animation(0, images);
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * checkcollision() used to check the cOllision with the player
     * if heart collides with player he gets one more life
     * we check collision with all the Hearts in Handler class
     */
    public boolean checkcollision() {
        boolean collision = false;
        if (gamePanel.player.intersects(this)) {
            collision = true;
            gamePanel.player.setLivesLeft(gamePanel.player.getLivesLeft() + 1);
        }
        return collision;
    }

    @Override
    public void render(Graphics2D g2) {
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        double screenY = this.getY();
        g2.drawImage(images[0], (int) screenX, (int) screenY + GamePanel.TILE_SIZE,
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
