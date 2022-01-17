package objects;

import main.game.Animation;
import main.game.GamePanel;
import main.game.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StaticObject extends GameObject {
    BufferedImage[] images;
    Animation animation;
    int numofImages;

    public StaticObject(double worldX, double worldY,
                        int width, int height, String name, GamePanel gamePanel, int numofImages) {
        super(worldX, worldY, width, height, name, gamePanel);
        this.numofImages = numofImages;
        getStaticObjectImage();
    }

    public abstract void getStaticObjectImage();



    public void update() {
        if (images.length != 1) {
            animation.runAnimation();
        }
    }

    public void render(Graphics2D g2) {
        /*centers the player in relation to the screen in x axis,
        gp.player.screenX is used to offset the difference*/
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        double screenY = this.getY();
        if (images.length == 1) {
            g2.drawImage(images[0], (int) screenX, (int) screenY,
                    GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        } else {
            animation.drawAnimation(g2, (int) screenX, (int) screenY,
                    GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        }
    }

}
