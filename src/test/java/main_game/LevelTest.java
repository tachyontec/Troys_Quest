package main.game;

import junit.framework.TestCase;

public class LevelTest extends TestCase {

    public void testCalculateScore() {
        Level level = new Level();
        int result = level.calculateScore(34.00, 3, 20, 5);
        assert result == 1350;
    }
}