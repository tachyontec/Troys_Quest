package objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> obstacleLinkedList;
    LinkedList<Enemy> enemies;
    Player player;

    public Handler(LinkedList<GameObject> obstacleLinkedList, Player player, LinkedList<Enemy> enemies) {
        this.obstacleLinkedList = obstacleLinkedList;
        this.player = player;
        this.enemies = enemies;
    }

    public void tick() {
        for (GameObject object : obstacleLinkedList) {
            object.tick();
        }
        for (Enemy enemy : enemies) {
            enemy.tick();
        }
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        for (GameObject object : obstacleLinkedList) {
            //g2.drawRect(obstacleLinkedList.get(i).x,obstacleLinkedList.get(i).y,obstacleLinkedList.get(i).width,obstacleLinkedList.get(i).height);
            object.render(g2);

        }

        for (Enemy enemy : enemies) {
            enemy.render(g2);
        }
    }

    public boolean checkcollision () {
        boolean b = false;
        for (GameObject object : obstacleLinkedList) {
            if (object.intersects(player)) {
                b = true;
                //player.setX(player.getX()-40);
                break;
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.intersects(player)) {
                b = true;
                break;
            }
        }
        return b;
    }


}