package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

//superclass for all the object of the game
public abstract class GameObject {
    //what all the objects have in common
    //worldx,worldy are the coordinates of the game object relative to the map as pictured in mapLayout.txt,represented in tiles 
    //screenx,screeny are the coordinates of the game object relative to the screen (frame)
    // id is the differentiator for player , obstacle etc.
    //counter helps each object have a ID
    //we have a speed variable for each axis (x and y)

    private double worldx;
    private double worldy;
    //private final double screenx;
    //private final double screeny;
    private int id;
    private double speedx;
    private double speedy;
    public static int counter;
    public String direction;




    //sprites are smaller rectangles that make up the whole window
    //we hold the counter and the number for each of our sprites
    public int spriteCounter = 0;
    public int spriteNumber = 0;

    public Rectangle area; //creating invisible rectangle to store data
    public boolean collisionOn = false;

    //generic constructor , spawns game objects at x ,y coordinates , sets the speed in x,y axis 
    public GameObject(double worldx, double worldy ,double speedx, double speedy) {
        counter++;
        this.worldx = worldx;
        this.worldy = worldy;
        this.speedx = speedx;
        this.speedy = speedy;
        this.id=counter;
    }

    //we use a default constructor in the objects.Player class
    public GameObject(){}



    public void render(Graphics2D g) {}

    public void tick() {}

    //getters and setters for each field
    public double getX() {
        return worldx;
    }

    public void setX(double worldx) {
        this.worldx = worldx;
    }

    public double getY() {
        return worldy;
    }

    public void setY(double worldy) {
        this.worldy = worldy;
    }

    public double getSpeedx() {
        return speedx;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}