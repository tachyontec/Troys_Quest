package objects;

import main.*;


import java.awt.*;

//A game object that is moving towards the Player
public class MovingObstacle extends Obstacle {
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    int speed;

    public MovingObstacle(double worldX, double worldY, double speedx, double speedy, int width, int height, GamePanel gamePanel, String name, int speed) {
        super(worldX,worldY,speedx,speedy,width,height,name,speed,gamePanel);
        this.gamePanel = gamePanel;
        //While making the object we also determine its speed
        this.speed = speed;
        super.direction = "run";
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        animation.drawAnimation(g, (int) this.getX(), (int) this.getY(), gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void tick() {
        animation.runAnimation();
        this.setX(this.getX() - speed);
    }
}
