package objects;

import main.Animation;
import main.GamePanel;
import main.Resource;
import sounds.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * public class Coin
 * creates coins for reward
 * Coin will have an animation for idle and one animation for player when coliding with it
 * The constructor creates the Coin instance and sets them on the map at a fixed location
 * setAnimation() creates the animation instances and gets the images for the animation
 * render() sets the Coin at a fixed location constantly and renders the animations
 * tick() dictates what our coin does at any given moment
 */
public class Coin extends GameObject {
    //this list will contain all Coins
    public GamePanel gamePanel;
    enum State {IDLE, PICKED}
    public State state;
    Animation idleanimation;
    BufferedImage [] idleimages;
    public Sound soundEffect = new Sound(); // to implement sound of ncoin when picked

    public Coin(double worldX, double worldY,
                double speedX, double speedY, int width, int height,GamePanel gamePanel ) {
        super(worldX, worldY, speedX, speedY, width, height);
        this.gamePanel = gamePanel;
        state = State.IDLE;
        getCoinImage();
    }

    public void getCoinImage() {
        idleimages = Resource.getFilesInDir("res/objects/Coin");
        idleanimation = new Animation(0,idleimages);
    }

    @Override
    public void render(Graphics2D g2) {
        super.render(g2);
        //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        //centers the player in relat
        double screenY = this.getY() - gamePanel.player.getY() + gamePanel.player.screenY;
        g2.setColor(Color.BLACK);
        if(state == State.IDLE){
            idleanimation.drawAnimation(g2, (int) screenX, (int) screenY,
                    gamePanel.tileSize /2, gamePanel.tileSize /2);
        }else {
            System.out.println("picked");
        }

    }

    @Override
    public void update() {
        super.update();
        if(state == State.IDLE) {
            idleanimation.runAnimation();
        }
    }

    public boolean checkCollision() {
        boolean result = false;
        if (gamePanel.player.intersects(this)) {
            state = State.PICKED;
            result = true;
            gamePanel.player.setCoinsCollected(gamePanel.player.getCoinsCollected() + 1);
            soundEffect.playSE(4);

        }
        return result;
    }
}
