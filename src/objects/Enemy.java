package objects;

import main.Animation;
import main.Resource;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
/*
public class Enemy
creates enemies for our player to fight
most enemies have running , dying , idle and attacking animations
The constructor creates the enemy instance and sets them on the map at a fixed location
setAnimation() creates the animation instances and gets the images for the animation
render() sets the player at a fixed location constantly and renders the animations
tick() dictates what our enemy does at any given moment
 */
public class Enemy extends GameObject {

    private String name;
    GamePanel gamePanel;
    enum State {ALIVE,DEAD,RUN,ATTACK,IDLE};
    State state ;//state stores current player state

    //Animation instances for everything that our enemy does
    Animation walkingAnimation,idleAnimation,deathAnimation,attackAnimation;

    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public BufferedImage[] run,idle,death,attack;

    public Enemy(double worldX, double worldY, double speedX, double speedY, int width, int height,String name, GamePanel gamePanel) {

        super(worldX,worldY,speedX,speedY,width,height);
        this.name = name;
        this.state = State.IDLE; //starting with Idle
        this.setAnimation();
        this.gamePanel = gamePanel;
    }

    public void setAnimation() {
        //get all Images with the animation from the relevant folder
        run = Resource.getFilesInDir("res/Enemies/"+this.name+"/Run");
        attack = Resource.getFilesInDir("res/Enemies/"+this.name+"/Attack");
        idle = Resource.getFilesInDir("res/Enemies/"+this.name+"/Idle");
        death = Resource.getFilesInDir("res/Enemies/"+this.name+"/Death");
        //Create the animations
        walkingAnimation = new Animation(5,run);
        idleAnimation = new Animation(5,idle);
        attackAnimation = new Animation(5,attack);
        deathAnimation = new Animation(5,death);
    }
    //renders the animations of the enemy
    @Override
    public void render(Graphics2D g) {
        super.render(g);
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenY = this.getY() - gamePanel.player.getY() + gamePanel.player.screenY; //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference

        switch (state) {
            case DEAD -> deathAnimation.drawAnimation(g,(int)screenX,(int) screenY , gamePanel.tileSize * 3,gamePanel.tileSize * 3 );
            case RUN -> walkingAnimation.drawAnimation(g,(int)screenX,(int) screenY ,gamePanel.tileSize * 3 ,gamePanel.tileSize * 3 );
            case IDLE -> idleAnimation.drawAnimation(g,(int)screenX,(int) screenY ,gamePanel.tileSize * 3 ,gamePanel.tileSize * 3);
            case ATTACK -> attackAnimation.drawAnimation(g,(int)screenX,(int) screenY ,gamePanel.tileSize * 3 ,gamePanel.tileSize * 3 );
        }
    }
    //determines what our enemy does at any given moment
    @Override
    public void tick() {
        idleAnimation.runAnimation();
    }

    public String getName() {
        return name;
    }
}
