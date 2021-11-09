import java.awt.*;
//subclass of GameObject
//responsible for spawning obstacles in the game
//handler.addObject(new Player(x , y)) in Game constructor to create a new player in the game
public class Obstacle extends GameObject{
    public Obstacle(double x, double y) {
        super(x,y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
    g.fillRect((int)this.getX() , (int)this.getY() , 12 , 20);
    g.setColor(Color.GREEN);
    }
}