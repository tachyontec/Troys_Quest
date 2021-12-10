package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
    //Implementing start and pause menu navigation
    GamePanel gamepanel;
    Font menuFont;
    BufferedImage helmetImage;
    public int choice = 0;

    public Menu(GamePanel gamePanel){
        this.gamepanel = gamePanel;
        this.menuFont = new Font("MV Boli", Font.PLAIN, 35);
        try {
            this.helmetImage = ImageIO.read(new File("res/menu/helmet3.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawMainMenu(Graphics2D g2){
        //Graphic drawing of main menu
       g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
       String title = "Troy's Quest";
        //Title Shadow
        g2.drawString(title , 93 , 103);
        //Title
        g2.setColor(new Color(181 , 179 , 92));
        g2.drawString(title , 90 , 100);
        g2.drawImage(helmetImage , 580 , 25 , gamepanel.tileSize * 2 , gamepanel.tileSize * 2 , null );

        //Menu options
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        String option;
        option = "NEW GAME";
        g2.drawString(option , 200 , 250);
        if (choice == 0){
            g2.drawString(">", 200 -30 , 250); //arrow shows each option instead of buttons . It is placed slightly to the left
        }
        option = "SELECT LEVEL";
        g2.drawString(option , 200 , 310);
        if (choice == 1){
            g2.drawString(">", 200 -30 , 310);
        }
        option = "EXIT";
        g2.drawString(option , 200 , 370);
        if (choice == 2){
            g2.drawString(">", 200 -30 , 370);
        }
    }

    public void drawPauseMenu(Graphics2D g2){
        //Graphic drawing of pause menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        g2.setColor(new Color(200 , 179 , 92));
        String title = "Pause";
        g2.drawString(title , gamepanel.screenWidth / 2 - 150 , gamepanel.screenHeight / 2 + -200);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        String option;
        option = "RESUME";
        g2.drawString(option , 200 , 250);
        if (choice == 0){
            g2.drawString(">", 200 -30 , 250);
        }
        option = "BACK TO MAIN MENU";
        g2.drawString(option , 200 , 310);
        if (choice == 1){
            g2.drawString(">", 200 -30 , 310);
        }
        option = "EXIT";
        g2.drawString(option , 200 , 370 );
        if (choice == 2){
            g2.drawString(">", 200 - 30 , 370);
        }
    }

    public void drawWinLoseMenu(Graphics2D g2) {
        if (gamepanel.player.getLivesLeft() > 0 ) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
            g2.setColor(new Color(200 , 179 , 92));
            String title = "YOU WIN!";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
            String option;
            option = "REPLAY LEVEL";
            g2.drawString(option , 200 , 250);
            if (choice == 0){
                g2.drawString(">", 200 -30 , 250);
            }
            option = "BACK TO MAIN MENU";
            g2.drawString(option , 200 , 310);
            if (choice == 1){
                g2.drawString(">", 200 -30 , 310);
            }
            option = "EXIT";
            g2.drawString(option , 200 , 370 );
            if (choice == 2) {
                g2.drawString(">", 200 - 30 , 370);
            }
        }
    }




}
