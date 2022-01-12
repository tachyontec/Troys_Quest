package objects;


import main.game.GamePanel;

import java.awt.Graphics2D;

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
        start = new Obstacle(5 * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE * 5,
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE * 5, "Flag", gamepanel);
        /*we change start worldX because we need tha flag
        to spawn down next to the player but the rectangle to be taller */
        start.worldY = (9 * GamePanel.TILE_SIZE);
        end = new Obstacle((GamePanel.MAX_WORLD_COL - 2) * GamePanel.TILE_SIZE,
                GamePanel.TILE_SIZE * 5, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE * 5, "Flag", gamePanel);
        /* we change end worldX because we need tha flag
        to spawn down next to the player but the rectangle to be taller */
        end.worldY = (9 * GamePanel.TILE_SIZE);

    }

    /* checking the collision of the player with the starting and
    ending bound only if the player is very near the bounds */
    public void update() {
        start.update();
        end.update();
        if (gamepanel.gameState == GamePanel.PLAY_STATE) {
            if (start.worldX - player.getX() <= 10) {
                if (start.intersects(player)) {
                    player.setX(player.getX() + 5);
                }
            }
            if (end.worldX - player.getX() <= 10) {
                if (end.intersects(player)) {
                    gamepanel.music.stopMusic();
                    gamepanel.music.playMusic(7, false);
                    gamepanel.player.setCollision(false);
                    gamepanel.gameState = GamePanel.WIN_LOSE_STATE;
                }
            }
        }
    }

    public void render(Graphics2D g2) {
        start.render(g2);
        end.render(g2);
    }


}
