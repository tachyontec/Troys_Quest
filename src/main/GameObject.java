package main;//superclass for all the object of the game
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    //what all the objects have in common
    //x,y are the coordinates of the game obje
    // id is the differentiator for player , obstacle etc.
    //counter helps each object have a ID
    //we have a speed variable for each axis (x and y)
    private double x;
    private double y;
    private int id;
    private double speedx;
    private double speedy;
    public static int counter;
    public BufferedImage [] run;
    public BufferedImage [] jump;
    public BufferedImage [] idle;
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
    public GameObject(double x, double y ,double speedx, double speedy) {
        counter++;
        this.x=x;
        this.y=y;
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
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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