package main.game;

import junit.framework.TestCase;

public class GamePanelTest extends TestCase {

    /*public void testSetUpGame() {
        GamePanel gp = new GamePanel();
        TileManager tileManager = new TileManager(gp);
        gp.setUpGame(tileManager);
        assert gp.gameState == GamePanel.INTRO_STATE;
    }*/

    public void testStopGameThread() {
        GamePanel gp = new GamePanel();
        gp.stopGameThread();
        assert gp.gameThread == null;
    }

}