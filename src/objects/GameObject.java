package objects;

import main.GamePanel;

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

    double worldX;
    double worldY;
    GamePanel gamePanel;
    public boolean collision = false;
    final String name;


    public Rectangle area; //creating invisible rectangle to store data

    //generic constructor , spawns game objects at x ,y coordinates , sets the speed in x,y axis 
    public GameObject(double worldX, double worldY, int width, int height, String name,  GamePanel gamePanel) {
        super((int) worldX, (int) worldY, width, height);
        this.worldX = worldX;
        this.worldY = worldY;
        this.name = name;
        this.gamePanel = gamePanel;
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

}