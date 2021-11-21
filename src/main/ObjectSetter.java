package main;

public class ObjectSetter {

    GamePanel gamePanel;

    public ObjectSetter(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    //floor is Y=8
    public void setObject() {

        gamePanel.objects[0] = new SuperObject("Arrow");
        gamePanel.objects[0].setX(8 * gamePanel.tileSize);
        gamePanel.objects[0].setY(9 * gamePanel.tileSize);

        gamePanel.objects[1] = new SuperObject("Arrow");
        gamePanel.objects[1].setX(5 * gamePanel.tileSize);
        gamePanel.objects[1].setY(9 * gamePanel.tileSize);

    }
}