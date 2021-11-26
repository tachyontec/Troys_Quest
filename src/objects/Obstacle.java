package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Obstacle extends GameObject {

    BufferedImage image;
    private String name;
    //We create any object ONLY with its name as a parameter in ObstacleSetter.setObject
    public Obstacle(String name) {
        this.name = name;
        try {
            //And we have an image in /objects/  folder with the same name, which represents the object
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/"+this.name+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
       double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
       double screenY = this.getY() - gamePanel.player.getY() + gamePanel.player.screenY; //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference

       graphics2D.drawImage(image ,(int) screenX ,(int) screenY , gamePanel.tileSize , gamePanel.tileSize , null); //draws the object in the specified screenX and screenY
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