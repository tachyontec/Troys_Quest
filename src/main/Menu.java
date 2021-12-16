package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Menu {
    //Implementing start and pause menu navigation
    GamePanel gamepanel;
    Font menuFont;
    BufferedImage helmetImage;
    BufferedImage backgroundImage;
    BufferedImage buttonImage;
    BufferedImage tombImage;

    public int choice = 0; //option selection default
    int defaultX = 200;
    int defaultY = 300;
    DecimalFormat decFormat = new DecimalFormat("#0.00");

    public Menu(GamePanel gamePanel) {
        this.gamepanel = gamePanel;
        this.menuFont = new Font("MV Boli", Font.PLAIN, 35);
        try {
            this.helmetImage = ImageIO.read(new File("res/menu/MenuHelmet.png"));
            this.backgroundImage = ImageIO.read(new File("res/Backgrounds/backgroundMenu.png"));
            this.buttonImage = ImageIO.read(new File("res/Buttons/Button.png"));
            this.tombImage = ImageIO.read(new File("res/menu/tombstone2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Graphic drawing of main menu
    public void drawMainMenu(Graphics2D g2) {
        g2.setFont(menuFont);
        //Background
        g2.drawImage(backgroundImage , 0  , 0 ,gamepanel.tileSize * 16 , gamepanel.tileSize * 12  , null );
        g2.drawImage(buttonImage , defaultX - 50 , defaultY + 70 , gamepanel.tileSize * 9 , 60  ,null);
        g2.drawImage(buttonImage , defaultX - 50 , defaultY + 10, gamepanel.tileSize * 9 , 60  ,null);
        g2.drawImage(buttonImage , defaultX - 50 , defaultY - 50 , gamepanel.tileSize * 9 , 60  ,null);
        g2.drawImage(helmetImage , 600 , 45 , gamepanel.tileSize * 2  , 60  ,null);
        //Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        String title = "Troy's Quest";
        //Title Shadow
        g2.drawString(title, 93, 103);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(title, 90, 100);

        //Menu options
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
        String option;
        option = "NEW GAME";
        g2.drawString(option, defaultX, defaultY);
        if (choice == 0) {
            g2.drawString(">", defaultX - 30, defaultY); //arrow shows each option instead of buttons . It is placed slightly to the left
        }
        option = "SELECT LEVEL";
        g2.drawString(option, defaultX, defaultY + 60);
        if (choice == 1) {
            g2.drawString(">", defaultX - 30, defaultY + 60);
        }
        option = "EXIT";
        g2.drawString(option, defaultX + 50, defaultY + 120);
        if (choice == 2) {
            g2.drawString(">", defaultX + 20, defaultY + 120);
        }
    }

    //Graphic drawing of pause menu
    public void drawPauseMenu(Graphics2D g2) {
        g2.setFont(menuFont);
        //Background
        g2.drawImage(backgroundImage , 0  , 0 ,gamepanel.tileSize * 16 , gamepanel.tileSize * 12  , null );
        g2.drawImage(buttonImage , defaultX - 50 , defaultY + 70 , gamepanel.tileSize * 9 , 60  ,null);
        g2.drawImage(buttonImage , defaultX - 50 , defaultY + 10, gamepanel.tileSize * 9 , 60  ,null);
        g2.drawImage(buttonImage , defaultX - 50 , defaultY - 50 , gamepanel.tileSize * 9 , 60  ,null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        //Title
        String title = "Pause";
        g2.setColor(Color.BLACK);
        g2.drawString(title, 233, 103);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(title, 230, 100);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
        String option;
        option = "RESUME";
        g2.drawString(option, defaultX + 20, defaultY);
        if (choice == 0) {
            g2.drawString(">", defaultX - 10, defaultY);
        }
        option = "BACK TO MAIN MENU";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 33));
        g2.drawString(option, defaultX - 5, defaultY + 57);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
        if (choice == 1) {
            g2.drawString(">", defaultX - 30, defaultY + 60);
        }
        option = "EXIT";
        g2.drawString(option, defaultX + 50, defaultY + 120);
        if (choice == 2) {
            g2.drawString(">", defaultX + 20, defaultY + 120);
        }
    }

    public void drawWinLoseMenu(Graphics2D g2) {
        //check if player is alive to decide if he has won or lost when the game state switches to WIN_LOSE_STATE
        //if he has lives left we draw the win menu
        //to be optimized for less lines of code
        g2.setFont(menuFont);
        if (gamepanel.player.getLivesLeft() > 0) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
            g2.setColor(new Color(200, 179, 92));
            String title = "YOU WIN!";
            g2.drawString(title, gamepanel.screenWidth / 2 - 180, gamepanel.screenHeight / 2 - 200);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
            g2.drawString("YOUR TIME WAS: " + decFormat.format(gamepanel.hud.levelTimer) + "sec", gamepanel.screenWidth / 2 - 240, 150);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
            String option;
            option = "REPLAY LEVEL";
            g2.drawString(option, 180, 260);
            if (choice == 0) {
                g2.drawString(">", 150, 260);
            }
            option = "BACK TO MAIN MENU";
            g2.drawString(option, 180, 320);
            if (choice == 1) {
                g2.drawString(">", 150, 320);
            }
            option = "EXIT";
            g2.drawString(option, 180, 380);
            if (choice == 2) {
                g2.drawString(">", 150, 380);
            }
        } else { //if he has not any lives left we draw the lose menu
            //BACKGROUND
            g2.drawImage(tombImage , 0 , 0 , gamepanel.tileSize * 6 , gamepanel.tileSize * 5 , null);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
            g2.setColor(new Color(200, 179, 92));
            String title = "YOU LOSE!";
            g2.drawString(title, gamepanel.screenWidth / 2 - 180, gamepanel.screenHeight / 2 - 200);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
            //g2.drawString("YOUR TIME WAS: " + decFormat.format(gamepanel.hud.deathTime) + "sec", gamepanel.screenWidth / 2 - 240, 150);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
            String option;
            option = "REPLAY LEVEL";
            g2.drawString(option, 180, 260);
            if (choice == 0) {
                g2.drawString(">", 150, 260);
            }
            option = "BACK TO MAIN MENU";
            g2.drawString(option, 180, 320);
            if (choice == 1) {
                g2.drawString(">", 150, 320);
            }
            option = "EXIT";
            g2.drawString(option, 180, 380);
            if (choice == 2) {
                g2.drawString(">", 150, 380);
            }
        }
    }


}
