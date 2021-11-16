package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject {

    public float jumpingTime = 100;
    public KeyHandler keyHandler;
    public GamePanel gamePanel;

    public final int axisX=400;
    public final int axisY=400;
    Animation walkinganimation;
    Animation jumpinganimation;

    public Player(double x, double y, double speedx, double speedy, KeyHandler keyHandler, GamePanel gamePanel) {
        super(x, y, speedx, speedy);
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        getPlayerImage();
        walkinganimation = new Animation(2,run);
        jumpinganimation = new Animation(2,jump);
        super.direction="run";
    }
    public void getPlayerImage() {
        try {
            File path1 = new File("C:\\Users\\Μαριοςς\\IdeaProjects\\Troys_Quest3\\res\\Player\\Run");
            File [] allfiles1 = path1.listFiles();
            File path2 = new File("C:\\Users\\Μαριοςς\\IdeaProjects\\Troys_Quest3\\res\\Player\\Jump");
            File [] allfiles2 = path2.listFiles();
            run = new BufferedImage[allfiles1.length];
            jump = new BufferedImage[allfiles2.length];
            for(int i=0 ; i<run.length;i++) {
                run[i] = ImageIO.read(allfiles1[i]);
                System.out.println("loaded");
            }
            for(int i=0 ; i<jump.length;i++) {
                String str=i+".png";
                jump[i] = ImageIO.read(allfiles2[i]);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
            if (keyHandler.upPressed) {
                direction = "jump";
                new Thread(new thread()).start();
                this.setY(this.getY() - 10);
                jumpinganimation.runAnimation();
            } else if (keyHandler.leftPressed) {
                direction = "run";
                this.setX(this.getX() - this.getSpeedx());
                walkinganimation.runAnimation();
            } else if (keyHandler.rightPressed) {
                direction= "run";
                this.setX(this.getX() + getSpeedx());
                walkinganimation.runAnimation();
            }

        }


    @Override
    public void render(Graphics2D g) {
        switch (direction) {
            case "jump" :
                jumpinganimation.drawAnimation(g,(int) getX(),(int) getY(),gamePanel.tileSize, gamePanel.tileSize);
                break;
            case "run" :
                if(this.getX() != 400) {
                    walkinganimation.drawAnimation(g, (int) getX(), (int) getY(), gamePanel.tileSize, gamePanel.tileSize);
                    break;
                }else {
                    g.drawImage(run[0],(int)getX(),(int)getY(),gamePanel.tileSize,gamePanel.tileSize,null);
                }

        }
    }

    public class thread implements Runnable{

        @Override
        public void run() {
            try{
                Thread.sleep((long) jumpingTime);
                setY(getY() + 10);
                direction = "run";
            } catch(Exception e){
                e.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }
        }
    }
}
