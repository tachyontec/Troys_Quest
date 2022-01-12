package objects;

import junit.framework.TestCase;
import main.game.KeyHandler;

public class PlayerTest extends TestCase {

    public void testJump() {
        KeyHandler keyHandler = new KeyHandler(null);
        Player player = new Player(1, 1, 1, 1, "Greek_warrior",null,1,1,keyHandler);
        player.keyHandler.upPressed = true;
        assert (player.jumped);
    }

    public void testDeath() {
        Player player = new Player(1, 1, 1, 1, "Greek_warrior",null,1,1,null);
        player.setLivesLeft(0);
        assertEquals(player.getLivesLeft(),0);
    }
}