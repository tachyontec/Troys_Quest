package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject {

    public KeyHandler keyHandler;
    public GamePanel gamePanel;

    public final int axisX=400;
    public final int axisY=400;
    Animation walkinganimation;

    public Player(double x, double y, double speedx, double speedy, KeyHandler keyHandler, GamePanel gamePanel) {
        super(x, y, speedx, speedy);
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        getPlayerImage();
        walkinganimation = new Animation(2,run);
        super.direction="run";
    }
    public void getPlayerImage() {
        try {
            File path1 = new File("C:/Users/Μαριοςς/IdeaProjects/Troys_Quest/res/Player/Run");
            File [] allfiles1 = path1.listFiles();
            File path2 = new File("C:/Users/Μαριοςς/IdeaProjects/Troys_Quest/res/Player/Jump");
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
                this.setY(this.getY() - this.getSpeedy());
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
                g.drawImage(jump[0],(int)getX(),(int)getY(),gamePanel.tileSize,gamePanel.tileSize,null);
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
}