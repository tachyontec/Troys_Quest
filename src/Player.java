import java.awt.*;
//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject{

    public Player(double x,double y) {
        super(x,y);
    }

    @Override
    public void tick() {
        this.setX(this.getX()+this.getSpeedx());
        this.setY(this.getY()+this.getSpeedy());
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int) this.getX(),(int) this.getY(),32,32);
        g.setColor(Color.BLUE);
    }
}