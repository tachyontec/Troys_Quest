package objects;

import main.GamePanel;

import java.awt.*;

//class that will set the bound of the screen
//it sets the starting point and ending point
public class Bound {
    public static Obstacle start;//obstacle that will be in the start of the screen
    public static Obstacle end;////obstacle that will be in the end of the screen
    public Player player;
    public GamePanel gamepanel;

    //constructor that sets the obstacles and its collision bounds
    public Bound(Player player, GamePanel gamePanel) {
        this.player = player;
        this.gamepanel = gamePanel;
        start = new Obstacle(5 * gamePanel.tileSize, gamePanel.tileSize * 5,
                0, 0, gamePanel.tileSize, gamePanel.tileSize * 5, "Flag", gamePanel);
        /*we change start worldX because we need tha flag
        to spawn down next to the player but the rectangle to be taller */
        start.worldY = (9 * gamePanel.tileSize);
        end = new Obstacle(110 * gamePanel.tileSize, gamePanel.tileSize * 5,
                0, 0, gamePanel.tileSize, gamePanel.tileSize * 5, "Flag", gamePanel);
        /* we change end worldX because we need tha flag
        to spawn down next to the player but the rectangle to be taller */
        end.worldY = (9 * gamePanel.tileSize);

    }

    /* checking the collision of the player with the starting and
    ending bound only if the player is very near the bounds */
    public void update() {
        start.update();
        end.update();

        if (start.worldX - player.getX() <= 10) {
            if (start.intersects(player)) {
                player.setX(player.getX() + 5);
            }
        }
        if (end.worldX - player.getX() <= 10) {
            if (end.intersects(player)) {
                gamepanel.gameState = GamePanel.WIN_LOSE_STATE;
            }
        }
    }

    public void render(Graphics2D g2) {
        start.render(g2);
        end.render(g2);
    }


}
