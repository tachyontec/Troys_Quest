package objects;


import main.GamePanel;
/**
 * public class Heart
 * used to create a Heart for our player to increase his lives
 */
public class Heart extends StaticObject {

    public Heart(double worldX, double worldY, int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, width, height, name, gamePanel);
    }

    @Override
    public void update() {
        super.update();
    }
    /**
     * checkcollision() used to check the cOllision with the player
     * if heart collides with player he gets one more life
     * we check collision with all the Hearts in Handler class
     */
    public boolean checkcollision() {
        boolean collision =  false;
        if (gamePanel.player.intersects(this)) {
            collision = true;
            System.out.println("picked");
            gamePanel.player.setLivesLeft(gamePanel.player.getLivesLeft() + 1);
        }
        return collision;
    }

}
