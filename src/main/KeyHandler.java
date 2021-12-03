package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//This is class is called whenever we press a key
//So it will EXTEND KeyAdapter
public class KeyHandler implements KeyListener {
    //This class is referred only at a player
    //User will only be able to move its player
    public boolean upPressed, leftPressed, rightPressed, pausePressed, attackPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        //The speed that we will be moving objects.Player
        //We need to check after every button click because it is increasing through time
        int key = k.getKeyCode(); //get the code of key presses

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (key == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (key == KeyEvent.VK_P) {
            gp.gameState = GamePanel.PAUSE_STATE;
            pausePressed = true;
        } else if (key == KeyEvent.VK_U) {
            gp.gameState = GamePanel.PLAY_STATE;
            pausePressed = false;
        } else if (key == KeyEvent.VK_SPACE) {
            attackPressed = true;
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