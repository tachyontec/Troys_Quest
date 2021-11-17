package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SuperObject {

    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int x, y;

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {

        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public BufferedImage getImage(String path) {

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/arrow.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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