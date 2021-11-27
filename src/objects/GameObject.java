package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//superclass for all the object of the game
public abstract class GameObject extends Rectangle {
    //this method extends Rectangle class from java.awt to implement collision
    //what all the objects have in common
    //worldx,worldy are the coordinates of the game object relative to the map as pictured in mapLayout.txt,represented in tiles 
    //screenx,screeny are the coordinates of the game object relative to the screen (frame)
    // id is the differentiator for player , obstacle etc.
    //counter helps each object have a ID
    //we have a speed variable for each axis (x and y)

    private double worldX;
    private double worldY;
    //private final double screenx;
    //private final double screeny;
    private int id;
    private double speedX;
    private double speedY;
    public static int counter;
    public String direction;
    public boolean collision = false;




    //sprites are smaller rectangles that make up the whole window
    //we hold the counter and the number for each of our sprites
    public int spriteCounter = 0;
    public int spriteNumber = 0;

    public Rectangle area; //creating invisible rectangle to store data
    public boolean collisionOn = false;

    //generic constructor , spawns game objects at x ,y coordinates , sets the speed in x,y axis 
    public GameObject(double worldX, double worldY ,double speedX, double speedY,int width,int height) {
        super((int) worldX,(int) worldY, width, height);
        counter++;
        this.worldX = worldX;
        this.worldY = worldY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.id=counter;
    }

    //we use a default constructor in the objects.Player class
    public GameObject(){}



    public void render(Graphics2D g2) {
    }

    public void tick() {}

    //getters and setters for each field
    public double getX() {
        return worldX;
    }

    public void setX(double worldx) {
        this.worldX = worldx;
         x = (int)  worldx;
    }

    public double getY() {
        return worldY;
    }

    public void setY(double worldy) {
        this.worldY = worldy;
         y = (int)  worldy;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}