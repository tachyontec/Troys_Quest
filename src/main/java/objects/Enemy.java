package objects;

import main.game.Animation;
import main.game.Resource;
import main.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math;

/**
 * public class Enemy
 * creates enemies for our player to fight
 * most enemies have running , dying , idle and attacking animations
 * The constructor creates the enemy instance and sets them on the map at a fixed location
 * setAnimation() creates the animation instances and gets the images for the animation
 * render() sets the player at a fixed location constantly and renders the animations
 * tick() dictates what our enemy does at any given moment
 */
public class Enemy extends MovingObject {

    public int livesLeft;

    enum State {DEAD, RUN, ATTACK, IDLE}
    //variables used to calculate enemies' death time

    State state;//state stores current player state
    //Animation instances for everything that our enemy does
    Animation walkingAnimation, idleAnimation, deathAnimation, attackAnimation;
    private boolean collision;
    public double colissionTime;


    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public BufferedImage[] run, idle, death, attack;

    public Enemy(double worldX, double worldY, int width, int height, String name,
                 GamePanel gamePanel, double speedx, double speedy, int livesLeft) {
        super(worldX, worldY, width, height, name, gamePanel, speedx, speedy);
        this.state = State.IDLE; //starting with Idle
        this.livesLeft = livesLeft;
        this.collision = true;
    }

    @Override
    public void getMovingObjectImage() {
        //get all Images with the animation from the relevant folder
        run = Resource.getFilesInDir("src/main/resources/Enemies/" + this.name + "/Run");
        attack = Resource.getFilesInDir("src/main/resources/Enemies/" + this.name + "/Attack");
        idle = Resource.getFilesInDir("src/main/resources/Enemies/" + this.name + "/Idle");
        death = Resource.getFilesInDir("src/main/resources/Enemies/" + this.name + "/Death");
        //Create the animations
        walkingAnimation = new Animation(0, run);
        idleAnimation = new Animation(-3, idle);
        attackAnimation = new Animation(3, attack);
        deathAnimation = new Animation(-3, death);
    }

    //renders the animations of the enemy
    @Override
    public void render(Graphics2D g) {
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenY = this.getY(); //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference

        switch (state) {
            case DEAD -> deathAnimation.drawAnimation(g, (int) screenX, (int) screenY,
                    this.width, this.height);
            case RUN -> walkingAnimation.drawAnimation(g, (int) screenX, (int) screenY,
                    this.width, this.height);
            case IDLE -> idleAnimation.drawAnimation(g, (int) screenX, (int) screenY,
                    this.width, this.height);
            case ATTACK -> attackAnimation.drawAnimation(g, (int) screenX, (int) screenY,
                    this.width, this.height);
        }
    }


    //determines what our enemy does at any given moment
    @Override
    public void update() {
        //System.out.println(this.enemyDeathTime);
        if (this.livesLeft <= 0) {
            this.state = State.DEAD;
            deathAnimation.runAnimation();
        } else if (Math.abs(this.getX() - this.gamePanel.player.getX()) <= GamePanel.TILE_SIZE / 2 + GamePanel.TILE_SIZE / 9) {//when player x coordinate in [minotaurX*3/4*tilesize,minotaurX]
            this.state = State.ATTACK;
            attackAnimation.runAnimation();
        } else if (Math.abs(this.getX() - this.gamePanel.player.getX()) < 3 * GamePanel.TILE_SIZE //when player x coordinate in (minotaurX*3*tilesize,minotaurX*3/4*tilesize)
                && (Math.abs(this.getX() - this.gamePanel.player.getX()) > GamePanel.TILE_SIZE / 2 + GamePanel.TILE_SIZE / 10)) {
            this.state = State.RUN;
            walkingAnimation.runAnimation();
            this.setX(this.getX() - 1);
        } else {
            this.state = State.IDLE;
            idleAnimation.runAnimation();
        }
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
