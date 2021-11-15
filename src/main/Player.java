package main;

import main.GameObject;
import main.GamePanel;
import main.KeyHandler;

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

    public Player(double x, double y, double speedx, double speedy, KeyHandler keyHandler, GamePanel gamePanel) {
        super(x, y, speedx, speedy);
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        getPlayerImage();
        super.direction="run";
    }
    public void getPlayerImage() {
        try {
            File path1 = new File("C:\\Users\\PC\\IdeaProjects\\Troys_Quest\\res\\main.Player\\Run");
            File [] allfiles1 = path1.listFiles();
            File path2 = new File("C:\\Users\\PC\\IdeaProjects\\Troys_Quest\\res\\main.Player\\Jump");
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
            if (keyHandler.upPressed == true) {
                direction = "jump";
                this.setY(this.getY() - this.getSpeedy());
            } else if (keyHandler.leftPressed == true) {
                direction = "run";
                this.setX(this.getX() - this.getSpeedx());
            } else if (keyHandler.rightPressed == true) {
                direction= "run";
                this.setX(this.getX() + getSpeedx());
            }
            spriteCounter=0;
            while(spriteCounter<=60){
                if(spriteCounter%5==0){
                    spriteNumber++;
                }
                spriteCounter++;
            }
            if(spriteCounter==61){
                spriteCounter = 0;
                spriteNumber=0;
            }
        }


    @Override
    public void render(Graphics2D g) {
        BufferedImage  image =null;
        switch(direction) {
            case "jump":
               image = jump[spriteNumber];
               break;
            case "run":
                image = run[spriteNumber];
        }
        g.drawImage(image,(int) this.getX(), (int) this.getY() ,gamePanel.tileSize,gamePanel.tileSize,null);
    }
}