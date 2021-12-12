package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//This is class is called whenever we press a key
//So it will EXTEND KeyAdapter
public class KeyHandler implements KeyListener {
    //This class is referred only at a player
    //User will only be able to move its player
    public boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed, attackPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode(); //get the code of key pressed

        //MENU_STATE and PAUSE_STATE KEY INPUT
        //Each key has a different function in each game state
        if(gp.gameState == gp.MENU_STATE || gp.gameState == gp.PAUSE_STATE || gp.gameState == gp.WIN_LOSE_STATE){
            //Since there are 3 choices in each menu we handle them similarly
            if (key == KeyEvent.VK_UP) {
                gp.menu.choice--;
                //menu arrow cycling between the 3 choices and never going out of bounds
                if (gp.menu.choice < 0){
                    gp.menu.choice = 2;
                }
            }
            if (key == KeyEvent.VK_DOWN){
                gp.menu.choice++;
                //menu arrow cycling between the 3 choices and never going out of bounds
                if (gp.menu.choice > 2){
                    gp.menu.choice = 0;
                }
            }

            if (key == KeyEvent.VK_ENTER){ //if enter is pressed to select a choice
                switch (gp.menu.choice){
                    case 0:
                        //first option RESUME (PAUSE_STATE) or NEW GAME (MENU_STATE) always changes the gameState to PLAY_STATE so no need for differentiation
                        gp.gameState = gp.PLAY_STATE;
                        gp.music.stopMusic();
                        gp.music.playMusic(0); //And PLAY_STATE music is the first track in the array
                        break;
                    case 1:
                        //2nd option varies so we check the current game state
                        if(gp.gameState == gp.PAUSE_STATE ){
                            gp.gameState = gp.MENU_STATE;
                            gp.music.playMusic(5);
                            break;
                        } else if (gp.gameState == gp.WIN_LOSE_STATE){
                            gp.music.stopMusic();
                            gp.gameState = gp.MENU_STATE;
                            gp.music.playMusic(5);

                            break; //Level Selection To be implemented
                        }
                    case 2 :
                        //3rd option always exits the game
                        System.exit(0);
                }
            }
        }



        //GAME STATE KEY INPUT
        //The speed that we will be moving objects.Player
        //We need to check after every button click because it is increasing through time

        if (gp.gameState == gp.PLAY_STATE){
            if (key == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            } else if (key == KeyEvent.VK_LEFT) {
                leftPressed = true;
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

    @Override
    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        } else if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (key == KeyEvent.VK_SPACE) {
            attackPressed = false;
        }

    }
}