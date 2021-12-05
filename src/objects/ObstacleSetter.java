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

        gamePanel.enemies.add(new Enemy(400,350,0,0,gamePanel.tileSize,gamePanel.tileSize,"Minotaur",gamePanel));//adding enemy
        /*for (int i=0;i<20;i++) {
            gamePanel.obstacles.add(new Obstacle((10+15*i)*gamePanel.tileSize,9*gamePanel.tileSize , 0, 0, gamePanel.tileSize , gamePanel.tileSize,"spikesRoller",5,gamePanel));
            gamePanel.obstacles.add(new MovingObstacle((15+9*i)*gamePanel.tileSize, 7*gamePanel.tileSize, 3, 8, gamePanel.tileSize,gamePanel.tileSize,gamePanel, "Arrow", 5));
            gamePanel.obstacles.add( new MovingObstacle((20+15*i)*gamePanel.tileSize, 5*gamePanel.tileSize, 3, 4,gamePanel.tileSize, gamePanel.tileSize,gamePanel, "Bird", 2));
            gamePanel.obstacles.add(new Obstacle((25+7*i)*gamePanel.tileSize,9*gamePanel.tileSize,0,0,gamePanel.tileSize,gamePanel.tileSize,"long_wood",20,gamePanel));
        }*/
        //gamePanel.obstacles.get(0).y = gamePanel.obstacles.get(0).y + 16;
        //gamePanel.obstacles.add(new Obstacle(9*gamePanel.tileSize,9*gamePanel.tileSize,0,0,gamePanel.tileSize ,gamePanel.tileSize,"spikes",0,gamePanel));
    }
}