package main;//superclass for all the object of the game
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    //what all the objects have in common
    //x,y are the coordinates of the game obje
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
    //Buffered Images are the ones that contain our main character and how
    //they look when they face up,down etc. The difference between 1 and 2 is
    //which foot is in front, and which is back so that it creates an animation



    //sprites are smaller rectangles that make up the whole window
    //we hold the counter and the number for each of our sprites
    public int spriteCounter = 0;
    public int spriteNumber = 0;

    public Rectangle area; //creating invisible rectangle to store data
    public boolean collisionOn = false;

    //generic constructor , spawns game objects at x ,y coordinates
    public GameObject(double worldx, double worldy ,double speedx, double speedy) {
        counter++;
        this.worldx = worldx;
        this.worldy = worldy;
        this.speedx = speedx;
        this.speedy = speedy;
        this.id=counter;
    }

    //we use a default constructor in the main.Player class
    public GameObject(){};



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