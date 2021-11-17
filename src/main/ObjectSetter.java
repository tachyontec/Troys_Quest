package main;

public class ObjectSetter {

    GamePanel gamePanel;

    public ObjectSetter(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    public void setObject() {

        gamePanel.objects[0] = new ObjectBarrier();
        gamePanel.objects[0].setX(8 * gamePanel.tileSize);
        gamePanel.objects[0].setY(7 * gamePanel.tileSize);

        gamePanel.objects[1] = new ObjectBarrier();
        gamePanel.objects[1].setX(5 * gamePanel.tileSize);
        gamePanel.objects[1].setY(4 * gamePanel.tileSize);

    }
}