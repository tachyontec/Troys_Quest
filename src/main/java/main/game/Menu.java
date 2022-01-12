package main.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Implements menu system screens
 */
public class Menu {
    GamePanel gamepanel;
    Font menuFont;
    BufferedImage helmetImage;
    BufferedImage backgroundImage;
    BufferedImage buttonImage;
    BufferedImage tombImage;
    BufferedImage wreath;
    BufferedImage lv1Preview;
    BufferedImage lv2Preview;
    BufferedImage lv3Preview;
    int dynamicTextX;

    public int choice = 0; //option selection default
    int defaultX = 200; //reference point used for drawing
    int defaultY = 300; //reference point used for drawing
    int shadowOffset = 3; //offset used to create the shadow effect behind text
    DecimalFormat decFormat = new DecimalFormat("#0.00");

    /**
     * Menu constructor
     * Initializes various images used in menu system
     * Initializes menu font
     *
     * @param gamePanel : the main game panel
     */
    public Menu(GamePanel gamePanel) {
        this.gamepanel = gamePanel;
        this.menuFont = new Font("MV Boli", Font.PLAIN, 35);
        this.dynamicTextX = gamePanel.TILE_SIZE * 16;
        try {
            this.helmetImage = ImageIO.read(new File("src/main/resources/menu/MenuHelmet.png"));
            this.backgroundImage = ImageIO.read(new File("src/main/resources/Backgrounds/backgroundMenu.png"));
            this.buttonImage = ImageIO.read(new File("src/main/resources/Buttons/Button.png"));
            this.tombImage = ImageIO.read(new File("src/main/resources/menu/tombstone2.png"));
            this.wreath = ImageIO.read(new File("src/main/resources/menu/olive wreath.png"));
            this.lv1Preview = ImageIO.read(new File("src/main/resources/menu/Level1Preview.png"));
            this.lv2Preview = ImageIO.read(new File("src/main/resources/menu/Level2Preview.png"));
            this.lv3Preview = ImageIO.read(new File("src/main/resources/menu/Level3Preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the moving text coordinates in main menu
     */
    public void textUpdate() {
        dynamicTextX -= 2;
        if (dynamicTextX == -2000) {
            dynamicTextX = GamePanel.TILE_SIZE * 16;
        }
    }

    /**
     * Graphic drawing of main menu
     *
     * @param g2 main game graphics
     */
    public void drawMainMenu(Graphics2D g2) {
        g2.setFont(menuFont);
        //Background
        g2.drawImage(backgroundImage, 0, 0, GamePanel.TILE_SIZE * 16, GamePanel.TILE_SIZE * 12, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY + 70, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY + 10, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY - 50, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(helmetImage, 600, 45, GamePanel.TILE_SIZE * 2, 60, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        String title = "Troy's Quest";
        //Title Shadow
        g2.drawString(title, 93, 103);
        g2.setColor(new Color(181, 179, 92));
        //Title
        g2.drawString(title, 90, 100);

        //Menu options
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
        String option;
        option = "NEW GAME"; //1st option
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset, defaultY + shadowOffset);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX, defaultY);
        if (choice == 0) {
            g2.drawString(">", defaultX - 30, defaultY); //arrow shows each option instead of buttons . It is placed slightly to the left
        }
        option = "SELECT LEVEL"; //2nd option
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset, defaultY + shadowOffset + 60);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX, defaultY + 60);
        if (choice == 1) {
            g2.drawString(">", defaultX - 30, defaultY + 60);
        }
        option = "EXIT"; //3rd option
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset + 50, defaultY + shadowOffset + 120);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX + 50, defaultY + 120);
        if (choice == 2) {
            g2.drawString(">", defaultX + 20, defaultY + 120);
        }

        //Dynamic Text
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        g2.setColor(Color.BLACK);
        g2.drawString("Tip:Use arrow keys to move the player and navigate the Menu ,ENTER to select an option,P to pause the game", dynamicTextX, 500);
    }

    /**
     * Graphic drawing of pause menu
     *
     * @param g2 main game graphics
     */
    public void drawPauseMenu(Graphics2D g2) {
        g2.setFont(menuFont);
        //Background
        g2.drawImage(backgroundImage, 0, 0, GamePanel.TILE_SIZE * 16, GamePanel.TILE_SIZE * 12, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY + 70, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY + 10, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY - 50, GamePanel.TILE_SIZE * 9, 60, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        //Title
        String title = "PAUSE";
        g2.setColor(Color.BLACK);
        g2.drawString(title, 233, 103);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(title, 230, 100);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
        String option;
        option = "RESUME";
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset + 20, defaultY + shadowOffset);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX + 20, defaultY);
        if (choice == 0) {
            g2.drawString(">", defaultX - 10, defaultY);
        }
        option = "BACK TO MAIN MENU";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 33));
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset - 5, defaultY + shadowOffset + 57);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX - 5, defaultY + 57);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
        if (choice == 1) {
            g2.drawString(">", defaultX - 30, defaultY + 60);
        }
        option = "EXIT";
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset + 50, defaultY + shadowOffset + 120);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX + 50, defaultY + 120);
        if (choice == 2) {
            g2.drawString(">", defaultX + 20, defaultY + 120);
        }
    }

    /**
     * Graphic drawing of win or lose menu according to player state
     *
     * @param g2 main game graphics
     */
    public void drawWinLoseMenu(Graphics2D g2) {
        //check if player is alive to decide if he has won or lost when the game state switches to WIN_LOSE_STATE
        //if he has lives left we draw the win menu
        g2.setFont(menuFont);
        //BACKGROUND
        g2.drawImage(backgroundImage, 0, 0, GamePanel.TILE_SIZE * 16, GamePanel.TILE_SIZE * 12, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY + 70, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY + 10, GamePanel.TILE_SIZE * 9, 60, null);
        g2.drawImage(buttonImage, defaultX - 50, defaultY - 50, GamePanel.TILE_SIZE * 9, 60, null);
        String title;
        if (gamepanel.player.getLivesLeft() == 0) {
            g2.drawImage(tombImage, 250, 110, GamePanel.TILE_SIZE * 5, GamePanel.TILE_SIZE * 3, null);
            title = "YOU LOSE!";
        } else {
            title = "YOU WIN!";
            int levelScore = gamepanel.currentLevel.calculateScore(gamepanel.hud.levelTimer,
                    gamepanel.player.getLivesLeft(), gamepanel.player.getCoinsCollected(), gamepanel.player.getEnemiesKilled());
            int numOfStars;
            g2.setColor(Color.BLACK);
            g2.drawString("TOTAL SCORE: " + levelScore, 170 + shadowOffset, 480 + shadowOffset);
            g2.setColor(new Color(181, 179, 92));
            g2.drawString("TOTAL SCORE: " + levelScore, 170, 480);
            if (levelScore <= 850) {
                g2.drawImage(wreath, 250, 107, GamePanel.TILE_SIZE * 5, GamePanel.TILE_SIZE * 3, null);
                numOfStars = 1;
            } else if (levelScore <= 1150) {
                g2.drawImage(wreath, 130, 150, GamePanel.TILE_SIZE * 4, GamePanel.TILE_SIZE * 2, null);
                g2.drawImage(wreath, 250, 107, GamePanel.TILE_SIZE * 5, GamePanel.TILE_SIZE * 3, null);
                numOfStars = 2;
            } else {
                g2.drawImage(wreath, 130, 150, GamePanel.TILE_SIZE * 4, GamePanel.TILE_SIZE * 2, null);
                g2.drawImage(wreath, 250, 107, GamePanel.TILE_SIZE * 5, GamePanel.TILE_SIZE * 3, null);
                g2.drawImage(wreath, 420, 150, GamePanel.TILE_SIZE * 4, GamePanel.TILE_SIZE * 2, null);
                numOfStars = 3;
            }
            g2.setColor(Color.BLACK);
            g2.drawString("YOU RECEIVE " + numOfStars + " WREATH(S)!", 170 + shadowOffset, 510 + shadowOffset);
            g2.setColor(new Color(181, 179, 92));
            g2.drawString("YOU RECEIVE " + numOfStars + " WREATH(S)!", 170, 510);


        }
        //Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        g2.setColor(Color.BLACK);
        g2.drawString(title, 153, 103);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(title, 150, 100);
        //Options
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        String option;
        if (gamepanel.player.getLivesLeft() == 0) {
            option = "REPLAY LEVEL";
        } else {
            option = "NEXT LEVEL";
        }
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset, defaultY + shadowOffset);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX, defaultY);
        if (choice == 0) {
            g2.drawString(">", defaultX - 30, defaultY);
        }
        option = "BACK TO MAIN MENU";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 33));
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset - 5, defaultY + shadowOffset + 57);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX - 5, defaultY + 57);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        if (choice == 1) {
            g2.drawString(">", defaultX - 30, defaultY + 60);
        }
        option = "EXIT";
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultX + shadowOffset + 50, defaultY + shadowOffset + 120);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultX + 50, defaultY + 120);
        if (choice == 2) {
            g2.drawString(">", defaultX + 20, defaultY + 120);
        }
    }

    /**
     * Graphic drawing of level selection menu
     *
     * @param g2 main game graphics
     */
    public void drawLevelSelectionMenu(Graphics2D g2) {
        g2.setFont(menuFont);
        int defaultXs = 100; // change reference points to adjust to new menu environment
        int defaultYs = 200;
        //Background
        g2.drawImage(backgroundImage, 0, 0, GamePanel.TILE_SIZE * 16, GamePanel.TILE_SIZE * 12, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        String title = "Level Selection";
        //Title Shadow
        g2.drawString(title, 93, 103);
        g2.setColor(new Color(181, 179, 92));
        //Title
        g2.drawString(title, 90, 100);
        //OPTIONS
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        String option;
        option = "LEVEL 1"; //1st option
        g2.setColor(Color.BLACK);
        g2.drawString(option, defaultXs + shadowOffset, defaultYs + shadowOffset);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultXs, defaultYs);
        g2.drawImage(lv1Preview, defaultXs + 200, defaultYs - 65, GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE * 2, null);
        g2.setColor(Color.BLACK);
        if (choice == 0) {
            g2.drawString(">", defaultXs - 30, defaultYs);
        }
        option = "LEVEL 2"; //2nd option
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        g2.drawString(option, defaultXs + shadowOffset, defaultYs + shadowOffset + 130);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultXs, defaultYs + 130);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        g2.drawImage(lv2Preview, defaultXs + 200, defaultYs + 70, GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE * 2, null);
        g2.setColor(Color.BLACK);
        if (choice == 1) {
            g2.drawString(">", defaultXs - 30, defaultYs + 130);
        }
        option = "LEVEL 3"; //3rd option
        g2.drawString(option, defaultXs + shadowOffset, defaultYs + shadowOffset + 260);
        g2.setColor(new Color(181, 179, 92));
        g2.drawString(option, defaultXs, defaultYs + 260);
        g2.drawImage(lv3Preview, defaultXs + 200, defaultYs + 205, GamePanel.TILE_SIZE * 3,
                GamePanel.TILE_SIZE * 2, null);
        g2.setColor(Color.BLACK);
        if (choice == 2) {
            g2.drawString(">", defaultXs - 30, defaultYs + 260);
        }
    }

}
