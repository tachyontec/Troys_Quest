package objects;

import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends GameObject {

    BufferedImage [] movement;
    private String name;
    Animation animation;
    GamePanel gamePanel;


    //Constructor to initiate x,y,speed of the x and y axis and the sprites width,height
    public Obstacle(double worldX, double worldY, double speedX, double speedY, int width, int height, String name,int animationspeed,GamePanel gamePanel) {
        super(worldX, worldY, speedX, speedY, width, height);
        this.name = name;
        //Image is taken automatically from the folder with same name with the object
        this.movement = Resource.getFilesInDir("res/objects/"+name);
        animation = new Animation(animationspeed, movement);
        this.gamePanel = gamePanel;
    }
    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenY = this.getY() - gamePanel.player.getY() + gamePanel.player.screenY; //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference
        if(movement.length == 1) {
            graphics2D.drawImage(movement[0], (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize, null); //draws the object in the specified screenX and screenY
        }else {
            animation.drawAnimation(graphics2D, (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    @Override
    public void tick() {
        animation.runAnimation();
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