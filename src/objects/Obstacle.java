package objects;

import main.GamePanel;
import main.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends GameObject {

    BufferedImage image;
    private String name;


    //Constructor to initiate x,y,speed of the x and y axis and the sprites width,height
    public Obstacle(double worldX, double worldY, double speedX, double speedY, int width, int height, String name) {
        super(worldX, worldY, speedX, speedY, width, height);
        this.name = name;
        this.image = Resource.getResourceImage("objects", name);
    }

    //We create any object ONLY with its name as a parameter in ObstacleSetter.setObject
    public Obstacle(String name) {
        this.name = name;
        this.image = Resource.getResourceImage("objects", name);
    }


    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        //graphics2D.setColor(Color.CYAN);
        //graphics2D.drawRect(x,y,width,height);
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenY = this.getY() - gamePanel.player.getY() + gamePanel.player.screenY; //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference

        graphics2D.drawImage(image, (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize, null); //draws the object in the specified screenX and screenY
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}