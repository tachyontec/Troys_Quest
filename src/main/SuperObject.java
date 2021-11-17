package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SuperObject {

    BufferedImage image;
    private String name;
    private boolean collision = false;
    private int x, y;
    //We create any object ONLY with it's name as a parameter
    public SuperObject(String name) {
        this.name=name;
        try {
            //And we have an image in /objects/  folder with the same name, which represents the object
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/"+this.name+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {

        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}