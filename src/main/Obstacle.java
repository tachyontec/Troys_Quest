package main;

import java.awt.*;
//subclass of main.GameObject
//responsible for spawning obstacles in the game
//handler.addObject(new main.Player(x , y)) in Game constructor to create a new player in the game
public class Obstacle extends main.GameObject{
    public Obstacle(double x, double y) {
        super(x,y,0,0);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
    g.fillRect((int)this.getX() , (int)this.getY() , 12 , 20);
    g.setColor(Color.GREEN);
    }
}