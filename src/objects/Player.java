package objects;

import main.Animation;
import main.GamePanel;
import main.KeyHandler;
import main.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject {
    public double floor; //floor of every platform
    public boolean jumped = true;
    public float jumpingTime = 100;
    //player gets keyhandler to implement keyboard input
    public KeyHandler keyHandler;
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public BufferedImage[] run;
    public BufferedImage[] jump;
    public BufferedImage[] idle;
    public final int axisX = 400;
    public final int axisY = 400;
    private int livesLeft = 3;
    public final int screenX;
    public int screenY;

    //creating for each animation needed for the player an object of Animation class
    Animation walkinganimation;
    Animation jumpinganimation;
    Animation idleanimation;

    //Constructor using fields and initializing the animations objects
    public Player(double worldX, double worldY, double speedx, double speedy, KeyHandler keyHandler, GamePanel gamePanel) {
        super(gamePanel.tileSize * 7, gamePanel.tileSize * 9, speedx, speedy, 30, gamePanel.tileSize);
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        screenX = gamePanel.tileSize * 7;
        screenY = gamePanel.tileSize * 9;
        getPlayerImage();
        walkinganimation = new Animation(2, run);
        jumpinganimation = new Animation(10, jump);
        idleanimation = new Animation(1, idle);
        super.direction = "run";
    }

    // in this method we are loading the images for each animation from resources folder res
    //this needs to be implemented in another class named Resources later
    public void getPlayerImage() {
        run = Resource.getFilesInDir("res/Player/Run");
        jump = Resource.getFilesInDir("res/Player/Jump");
        idle = Resource.getFilesInDir("res/Player/Idle");
    }

    //moves the player by altering the x,y coordinates with keyboard arrows
    @Override
    public synchronized void tick() { //synchronized because we involve another thread for jumping and we don't want it to collide with our main game thread
        floor = getY();//sets the floor on which player is for every platform he stands on
        if ((!keyHandler.rightPressed) && (!keyHandler.leftPressed) && (!keyHandler.upPressed)) {
            idleanimation.runAnimation();
            direction = "idle";
        }
        if (keyHandler.upPressed) {
            if (jumped) {
                direction = "jump";
                new Thread(new thread()).start();//initiating a new thread to perform the jump act
                screenY -= 20;
                this.setY(this.getY() - 20);//moves the player upwards along the y axis
                jumpinganimation.runAnimation();
            }
        }
          if (keyHandler.leftPressed) {
            direction = "run";
            this.setX(this.getX() - this.getSpeedx());//moves the player along the x axis to the left
            walkinganimation.runAnimation();
        } else if (keyHandler.rightPressed) {
            direction = "run";
            this.setX(this.getX() + getSpeedx());//moves the player along the x axis to the right
            walkinganimation.runAnimation();
        }
        if ((!keyHandler.upPressed)||(floor == getY())) {
            jumped = true;
        }
    }

    // the direction variable indicates which images are to be drawn for each animation of the player
    @Override
    public void render(Graphics2D g) {
        //g.setColor(Color.RED);
        //g.drawRect(x,y,width,height);
        switch (direction) {
            case "jump" -> jumpinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
            case "run" -> walkinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
            default -> idleanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    public class thread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep((long) jumpingTime);
                screenY += 20;
                setY(getY() + 20);//moves the player downwards along the y axis
                direction = "run";//changes the direction to run in order to continue the run animation
                jumped = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
}
