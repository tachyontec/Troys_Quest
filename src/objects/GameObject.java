package objects;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;

//superclass for all the object of the game
public abstract class GameObject extends Rectangle {
    /**
     * this method extends Rectangle class from java.awt to implement collision
     * what all the objects have in common
     * worldx,worldy are the coordinates of the game object relative to the map as pictured in Level1Layout.txt,represented in tiles
     * screenx,screeny are the coordinates of the game object relative to the screen (frame)
     * id is the differentiator for player , obstacle etc.
     * counter helps each object have a ID
     * we have a speed variable for each axis (x and y)
     */

    public double worldX;
    public double worldY;
    private double speedX;
    private double speedY;
    public boolean collision = false;

    //sprites are smaller rectangles that make up the whole window
    //we hold the counter and the number for each of our sprites
    public int spriteCounter = 0;
    public int spriteNumber = 0;

    public Rectangle area; //creating invisible rectangle to store data

    //generic constructor , spawns game objects at x ,y coordinates , sets the speed in x,y axis 
    public GameObject(double worldX, double worldY, double speedX, double speedY, int width, int height) {
        super((int) worldX, (int) worldY, width, height);
        this.worldX = worldX;
        this.worldY = worldY;
        this.speedX = speedX;
        this.speedY = speedY;
    }


    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawRect(x, y, width, height);
    }

    public void update() {
    }

    //getters and setters for each field
    public double getX() {
        return worldX;
    }

    /*on this method we also change the rectangle x,y cords
     to make the collision rectangle follow along with the object*/
    public void setX(double worldx) {
        this.worldX = (int) worldx;
        this.x = (int) worldX;
    }

    public double getY() {
        return worldY;
    }

    public void setY(double worldy) {
        this.worldY = (int) worldy;
        this.y = (int) worldY;
    }

    public double getSpeedx() {
        return speedX;
    }

    public void setSpeedx(double speedx) {
        this.speedX = speedx;
    }

    public double getSpeedy() {
        return speedY;
    }

    public void setSpeedy(double speedy) {
        this.speedY = speedy;
    }

}