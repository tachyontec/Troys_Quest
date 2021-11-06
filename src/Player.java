import java.awt.*;
//Subclass of Game Object responsible for the moving and drawwing the caracter of the game
public class Player extends GameObject{

    public Player(int x,int y) {
        super(x,y);
    }

    @Override
    public void tick() {
        this.setX(this.getX()+this.getSpeedx());
        this.setY(this.getY()+this.getSpeedy());
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(this.getX(),this.getY(),32,32);
        g.setColor(Color.BLUE);
    }
}

