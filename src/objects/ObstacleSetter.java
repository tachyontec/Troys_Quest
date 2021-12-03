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

        gamePanel.obstacles.add( new Obstacle(15 * gamePanel.tileSize, 9 * gamePanel.tileSize, 0, 0, gamePanel.tileSize, 15, "arrow",5,gamePanel));
        gamePanel.obstacles.get(0).y = gamePanel.obstacles.get(0).y + 16;

        gamePanel.obstacles.add( new MovingObstacle(15*gamePanel.tileSize, 7*gamePanel.tileSize, 3, 4,gamePanel.tileSize, gamePanel.tileSize,gamePanel, "Bird", 2, 3));
        gamePanel.obstacles.add(new MovingObstacle(25*gamePanel.tileSize, 8, 3, 4, gamePanel.tileSize,gamePanel.tileSize,gamePanel, "arrow", 2, 3));
        gamePanel.obstacles.add(new Obstacle(12*gamePanel.tileSize,9*gamePanel.tileSize , 0, 0, gamePanel.tileSize , gamePanel.tileSize,"spikesRoller",5,gamePanel));
        gamePanel.obstacles.add(new Obstacle(9*gamePanel.tileSize,9*gamePanel.tileSize,0,0,gamePanel.tileSize ,gamePanel.tileSize,"spikes",0,gamePanel));
        gamePanel.obstacles.add(new Obstacle(5*gamePanel.tileSize,9*gamePanel.tileSize,0,0,gamePanel.tileSize,gamePanel.tileSize,"long_wood",20,gamePanel));
    }
}