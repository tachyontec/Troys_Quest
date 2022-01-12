package main.game;

import junit.framework.TestCase;

public class GamePanelTest extends TestCase {

    public void testSetUpGame() {
        GamePanel gp = new GamePanel();
        gp.setUpGame();
        assert gp.gameState == GamePanel.MENU_STATE;
    }

    public void testStopGameThread() {
        GamePanel gp = new GamePanel();
        gp.stopGameThread();
        assert gp.gameThread == null;
    }

}