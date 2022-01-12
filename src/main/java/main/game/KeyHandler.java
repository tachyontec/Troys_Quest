package main.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This is class is called whenever we press a key
 * It handles all user's input from keyboard
 * menu selection also implemented here
 */
public class KeyHandler implements KeyListener {
    //This class is referred only at a player
    //User will only be able to move its player
    public boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed, attackPressed;
    public boolean leftReleased = false;
    public boolean rightReleased = false;

    GamePanel gp;

    /**
     * Creates a keyhandler instance and the keys handled are used in the gamepanel
     *
     * @param gp the gamepanel in which keys are used
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method receives key inputs and translates them into readable code , then treats them accordingly
     * @param k the key input received
     * int key then receives the value of key pressed
     * in this method the menu options are handled as well , depending on the user key input
     * main menu , pause menu and death menu handled here
     *
     */
    @Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode(); //get the code of key pressed
        //MENU_STATE and PAUSE_STATE KEY INPUT
        //Each key has a different function in each game state
        if (gp.gameState == GamePanel.MENU_STATE || gp.gameState == GamePanel.PAUSE_STATE
                || gp.gameState == GamePanel.WIN_LOSE_STATE || gp.gameState == GamePanel.LEVEL_SELECTION_STATE) {
            //Since there are 3 choices in each menu we handle them similarly
            if (key == KeyEvent.VK_UP) { //UP
                gp.menu.choice--;
                //menu arrow cycling between the 3 choices and never going out of bounds
                if (gp.menu.choice < 0) {
                    gp.menu.choice = 2;
                }
            }
            if (key == KeyEvent.VK_DOWN) { //DOWN
                gp.menu.choice++;
                //menu arrow cycling between the 3 choices and never going out of bounds
                if (gp.menu.choice > 2) {
                    gp.menu.choice = 0;
                }
            }

            if (key == KeyEvent.VK_ENTER) { //if enter is pressed to select a choice //ENTER
                switch (gp.menu.choice) {
                    case 0: //FIRST CHOICE
                        if (gp.gameState == GamePanel.MENU_STATE) { //NEW GAME
                            //Start of level reset
                            GamePanel.currentLevelNumber = 1;
                            gp.resetGame(1);
                            switchSound(0);
                            //End of level reset
                            gp.gameState = GamePanel.PLAY_STATE;
                            switchSound(0); //And PLAY_STATE music is the first track in the array
                        } else if (gp.gameState == GamePanel.PAUSE_STATE) { //RESUME
                            gp.gameState = GamePanel.PLAY_STATE;
                            switchSound(0);  //GAME SONG
                        } else if (gp.gameState == GamePanel.WIN_LOSE_STATE) {
                            if (gp.player.getLivesLeft() == 0) { //REPLAY LEVEL
                                switchSound(0); //GAME SONG
                                //Start of level reset
                                gp.resetGame(GamePanel.currentLevelNumber);
                                //End of level reset
                                gp.gameState = GamePanel.PLAY_STATE;
                            } else {                            //NEXT LEVEL
                                GamePanel.currentLevelNumber++;
                                gp.resetGame(GamePanel.currentLevelNumber);
                                switchSound(0);
                                gp.gameState = GamePanel.PLAY_STATE;
                            }
                        } else if (gp.gameState == GamePanel.LEVEL_SELECTION_STATE) { //LEVEL 1 SELECTED
                            GamePanel.currentLevelNumber = 1;
                            gp.resetGame(1);
                            switchSound(0);
                            gp.gameState = GamePanel.PLAY_STATE;
                        }
                        break;
                    case 1: //SECOND CHOICE
                        if (gp.gameState == GamePanel.MENU_STATE) { //SELECT LEVEL
                            gp.gameState = GamePanel.LEVEL_SELECTION_STATE;
                        } else if (gp.gameState == GamePanel.PAUSE_STATE) { //BACK TO MAIN MENU
                            gp.gameState = GamePanel.MENU_STATE;
                            switchSound(5);  //INTRO SONG
                        } else if (gp.gameState == GamePanel.WIN_LOSE_STATE) { //BACK TO MAIN MENU
                            switchSound(5);  //INTRO SONG
                            gp.resetGame(GamePanel.currentLevelNumber);
                            gp.gameState = GamePanel.MENU_STATE;
                        } else if (gp.gameState == GamePanel.LEVEL_SELECTION_STATE) { //LEVEL 2 SELECTED
                            GamePanel.currentLevelNumber = 2;
                            gp.resetGame(2);
                            gp.gameState = GamePanel.PLAY_STATE;
                            switchSound(0);
                        }
                        break;
                    case 2: //3RD CHOICE
                        if (gp.gameState == GamePanel.LEVEL_SELECTION_STATE) { //LEVEL 3 SELECTED
                            GamePanel.currentLevelNumber = 3;
                            gp.resetGame(3);
                            gp.gameState = GamePanel.PLAY_STATE;
                            switchSound(0);
                        } else {
                            System.exit(0);
                        }
                }
            }
        }


        //GAME STATE KEY INPUT
        //The speed that we will be moving objects.Player
        //We need to check after every button click because it is increasing through time
        if (gp.gameState == GamePanel.PLAY_STATE) {
            if (key == KeyEvent.VK_RIGHT) {
                rightReleased = false;
                rightPressed = true;
            } else if (key == KeyEvent.VK_LEFT) {
                leftPressed = true;
                leftReleased = false;
            } else if (key == KeyEvent.VK_UP) {
                upPressed = true;
            } else if (key == KeyEvent.VK_P) {
                gp.gameState = GamePanel.PAUSE_STATE;
                gp.music.stopMusic();
                pausePressed = true;
            } else if (key == KeyEvent.VK_SPACE) {
                attackPressed = true;
            }
        }
    }

    /**
     * This method update our main boolean variables
     * Which will change the status of the player
     *
     * @param k :Represents the key that was released
     */
    @Override
    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            rightReleased = true;
        } else if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
            leftReleased = true;
        } else if (key == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (key == KeyEvent.VK_SPACE) {
            attackPressed = false;
        }

    }

    /**
     * switches between the sounds we want played
     *
     * @param soundNumber the number of the sound file we want played
     */
    public void switchSound(int soundNumber) {
        gp.music.stopMusic();
        gp.music.playMusic(soundNumber);
    }
}

