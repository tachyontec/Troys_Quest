package objects;

import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends StaticObject {
    boolean collision;

    //Constructor to initiate x,y,speed of the x and y axis and the sprites width,height
    public Obstacle(double worldX, double worldY, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, width, height, name, gamePanel);
        collision = false;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
    }

    @Override
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