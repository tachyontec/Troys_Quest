package objects;

import main.GamePanel;

public class ObstacleSetter {

    GamePanel gamePanel;

    public ObstacleSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //floor is Y=8
    //initializes obstacles in obstacles array with their image icon and x ,y position relative to the map (worldX , worldY)
    public void setObject() {

        gamePanel.obstacles[0] = new Obstacle("Arrow");
        gamePanel.obstacles[0].setX(15 * gamePanel.tileSize);
        gamePanel.obstacles[0].setY(9 * gamePanel.tileSize);

        gamePanel.obstacles[1] = new Obstacle("Arrow");
        gamePanel.obstacles[1].setX(5 * gamePanel.tileSize);
        gamePanel.obstacles[1].setY(9 * gamePanel.tileSize);

    }
}