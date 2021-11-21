package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject {

    public float jumpingTime = 100;
    //player gets keyhandler to iimplement keyboard input
    public KeyHandler keyHandler;
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public BufferedImage [] run;
    public BufferedImage [] jump;
    public BufferedImage [] idle;
    public final int axisX=400;
    public final int axisY=400;

    public final int screenX;
    public final int screenY;

    //creating for each animation needed for the player an object of Animation class
    Animation walkinganimation;
    Animation jumpinganimation;
    Animation idleanimation;
    //Constructor using fields and initializing the animations objects
    public Player(double worldX, double worldY, double speedx, double speedy, KeyHandler keyHandler, GamePanel gamePanel) {
        super(gamePanel.tileSize * 7 , gamePanel.tileSize * 9 , speedx, speedy);
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        screenX = gamePanel.tileSize * 7;
        screenY = gamePanel.tileSize * 9;
        getPlayerImage();
        walkinganimation = new Animation(2,run);
        jumpinganimation = new Animation(10,jump);
        idleanimation = new Animation(1,idle);
        super.direction="run";
    }
    // in this method we are loading the images for each animation from resources folder res
    //this needs to be implemented in another class named Resources later
    public void getPlayerImage() {
        try {
            File path1 = new File("res/Player/Run");
            File [] allfiles1 = path1.listFiles();
            File path2 = new File("res/Player/Jump");
            File [] allfiles2 = path2.listFiles();
            File path3 = new File("res/Player/Idle");
            File [] allfiles3 = path3.listFiles();
            run = new BufferedImage[allfiles1.length];
            jump = new BufferedImage[allfiles2.length];
            idle = new BufferedImage[allfiles3.length];
            for(int i=0 ; i<run.length;i++) {
                run[i] = ImageIO.read(allfiles1[i]);
            }
            for(int i=0 ; i<jump.length;i++) {
                jump[i] = ImageIO.read(allfiles2[i]);
            }
            for(int i=0 ; i<idle.length;i++) {
                idle[i] = ImageIO.read(allfiles3[i]);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    //moves the player by altering the x,y coordinates with keyboard arrows
    @Override
    public void tick() {
            if(!(keyHandler.rightPressed&keyHandler.leftPressed&keyHandler.upPressed)) {
                idleanimation.runAnimation();
                direction = "idle";
            }
            if (keyHandler.upPressed) {
                direction = "jump";
                new Thread(new thread()).start();//initiating a new thread to perform the jump act
                this.setY(this.getY() - 10);//moves the player upwards along the y axis
                jumpinganimation.runAnimation();
            } else if (keyHandler.leftPressed) {
                direction = "run";
                this.setX(this.getX() - this.getSpeedx());//moves the player along the x axis to the left
                walkinganimation.runAnimation();
            } else if (keyHandler.rightPressed) {
                direction= "run";
                this.setX(this.getX() + getSpeedx());//moves the player along the x axis to the right
                walkinganimation.runAnimation();
            }

        }

    // the direction variable indicates which images are to be drawn for each animation of the player
    @Override
    public void render(Graphics2D g) {

        switch (direction) {
            case "jump" :
                jumpinganimation.drawAnimation(g,(int) screenX,(int) screenY,gamePanel.tileSize, gamePanel.tileSize);
                break;
            case "run" :
                walkinganimation.drawAnimation(g, (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize);
                break;
            default :
                idleanimation.drawAnimation(g, (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    public class thread implements Runnable{

        @Override
        public void run() {
            try{
                Thread.sleep((long) jumpingTime);
                setY(getY() + 10);//moves the player downwards along the y axis
                direction = "run";//changes the direction to run in order to continue the run animation
            } catch(Exception e){
                e.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }
        }
    }
}
