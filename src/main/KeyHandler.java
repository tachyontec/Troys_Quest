package main;

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
        if(gp.gameState == GamePanel.MENU_STATE || gp.gameState == GamePanel.PAUSE_STATE || gp.gameState == GamePanel.WIN_LOSE_STATE){
            //Since there are 3 choices in each menu we handle them similarly
            if (key == KeyEvent.VK_UP) { //UP
                gp.menu.choice--;
                //menu arrow cycling between the 3 choices and never going out of bounds
                if (gp.menu.choice < 0){
                    gp.menu.choice = 2;
                }
            }
            if (key == KeyEvent.VK_DOWN){ //DOWN
                gp.menu.choice++;
                //menu arrow cycling between the 3 choices and never going out of bounds
                if (gp.menu.choice > 2){
                    gp.menu.choice = 0;
                }
            }

            if (key == KeyEvent.VK_ENTER){ //if enter is pressed to select a choice //ENTER
                switch (gp.menu.choice){
                    case 0: //FIRST CHOICE
                        if(gp.gameState == GamePanel.MENU_STATE){ //NEW GAME
                            //Start of level reset
                            gp.player.setX(7 * gp.tileSize);
                            gp.player.setY(9 * gp.tileSize);
                            gp.player.setLivesLeft(3);
                            gp.hud.levelTimer = 0;
                            //End of level reset
                            gp.gameState = GamePanel.PLAY_STATE;
                            gp.music.stopMusic();
                            gp.music.playMusic(0); //And PLAY_STATE music is the first track in the array
                        } else if(gp.gameState == GamePanel.PAUSE_STATE) { //RESUME
                            gp.gameState = GamePanel.PLAY_STATE;
                            gp.music.stopMusic();
                            gp.music.playMusic(0); //GAME SONG
                        } else if(gp.gameState == GamePanel.WIN_LOSE_STATE){ //REPLAY LEVEL
                            gp.music.stopMusic();
                            gp.music.playMusic(0); //GAME SONG
                            gp.gameState = GamePanel.PLAY_STATE;
                            //Start of level reset
                            gp.player.setX(7 * gp.tileSize);
                            gp.player.setY(9 * gp.tileSize);
                            gp.player.setLivesLeft(3);
                            GamePanel.i = 0;//mhn peirazete
                            gp.hud.levelTimer = 0;
                            //End of level reset
                        }
                        break;
                    case 1: //SECOND CHOICE
                        if(gp.gameState == GamePanel.MENU_STATE){ //SELECT LEVEL
                        //LEVEL SELECTION TO BE IMPLEMENTED
                        } else if(gp.gameState == GamePanel.PAUSE_STATE) { //BACK TO MAIN MENU
                            gp.gameState = GamePanel.MENU_STATE;
                            gp.music.stopMusic();
                            gp.music.playMusic(5); //INTRO SONG
                        } else if(gp.gameState == GamePanel.WIN_LOSE_STATE){ //BACK TO MAIN MENU
                            gp.gameState = GamePanel.MENU_STATE;
                            gp.music.stopMusic();
                            gp.music.playMusic(5); //INTRO SONG
                        }
                        break;
                    case 2 : //3RD CHOICE
                        //3rd choice always exits the game
                        System.exit(0);
                }
            }
        }



        //GAME STATE KEY INPUT
        //The speed that we will be moving objects.Player
        //We need to check after every button click because it is increasing through time

        if (gp.gameState == GamePanel.PLAY_STATE){
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