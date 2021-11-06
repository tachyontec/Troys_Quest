import java.awt.*;
//subclass of GameObject
//responsible for spawning obstacles in the game
//handler.addObject(new Player(x , y)) in Game constructor to create a new player in the game
public class Obstacle extends GameObject{
    public Obstacle(int x, int y) {
        super(x,y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
    g.setColor(Color.GREEN);
    g.fillRect(this.getX() , this.getY() , 12 , 20);
    }
}