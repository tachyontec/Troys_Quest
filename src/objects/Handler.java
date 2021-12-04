package objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    LinkedList<Obstacle> obstacleLinkedList;
    Player player;

    public Handler(LinkedList obstacleLinkedList,Player player) {
        this.obstacleLinkedList = obstacleLinkedList;
        this.player = player;
    }

    public void tick() {
        for (Obstacle obstacle : obstacleLinkedList) {
            obstacle.tick();
        }
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        for (Obstacle obstacle : obstacleLinkedList) {
            //g2.drawRect(obstacleLinkedList.get(i).x,obstacleLinkedList.get(i).y,obstacleLinkedList.get(i).width,obstacleLinkedList.get(i).height);
            obstacle.render(g2);

        }
    }

    public boolean checkcollision () {
        boolean b = false;
        for (Obstacle obstacle : obstacleLinkedList) {
            if (obstacle.intersects(player)) {
                b = true;
                //player.setX(player.getX()-40);
                break;
            }

        }
        return b;
    }


}