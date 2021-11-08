//superclass for all the object of the game
import java.awt.Graphics;
public abstract class GameObject {
    //what all the objects have in common
    //x,y are the coordinates of the game objects
    //speedx,speedy is the speed of all the objects
    // id is the differentiator for player , obstacle etc.
    public static int counter;
    private int x;
    private int y;
    private int id;
    private int speedx;
    private int speedy;

    //generic constructor , spawns game objects at x ,y coordinates
    public GameObject(int x, int y) {
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

    public int getSpeedx() {
        return speedx;
    }

    public void setSpeedx(int speedx) {
        this.speedx = speedx;
    }

    public int getSpeedy() {
        return speedy;
    }

    public void setSpeedy(int speedy) {
        this.speedy = speedy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
