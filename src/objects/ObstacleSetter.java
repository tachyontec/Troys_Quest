package objects;

import main.GamePanel;

public class ObstacleSetter {

    GamePanel gamePanel;

    public ObstacleSetter(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    //floor is Y=8
    public void setObject() {

        gamePanel.objects[0] = new Obstacle("Arrow");
        gamePanel.objects[0].setX(8 * gamePanel.tileSize);
        gamePanel.objects[0].setY(9 * gamePanel.tileSize);

        gamePanel.objects[1] = new Obstacle("Arrow");
        gamePanel.objects[1].setX(5 * gamePanel.tileSize);
        gamePanel.objects[1].setY(9 * gamePanel.tileSize);

    }
}