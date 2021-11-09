//superclass for all the object of the game
import java.awt.Graphics;
public abstract class GameObject {
    //what all the objects have in common
    //x,y are the coordinates of the game objects
    //speedx,speedy is the speed of all the objects
    // id is the differentiator for player , obstacle etc.
    public static int counter;
    private double x;
    private double y;
    private int id;
    private double speedx;
    private double speedy;
    private static double speed = 3;

    //generic constructor , spawns game objects at x ,y coordinates
    public GameObject(double x, double y) {
        counter++;
        this.x=x;
        this.y=y;
        this.id=counter;
    }
    //to be implemented in each specific class
    //what each object does
    public abstract void tick();

    //this method pops in the screen the object
    //is responsible for the graphic managment
    public abstract void render(Graphics g);

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

    public static double getSpeed() {
        return speed;
    }

    public static void setSpeed(double speed) {
        GameObject.speed = speed;
    }
}
