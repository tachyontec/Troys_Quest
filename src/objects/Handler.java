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
        for (GameObject object : obstacleLinkedList) {
            object.tick();
        }
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        for (GameObject object : obstacleLinkedList) {
            //g2.drawRect(obstacleLinkedList.get(i).x,obstacleLinkedList.get(i).y,obstacleLinkedList.get(i).width,obstacleLinkedList.get(i).height);
            object.render(g2);

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
        return b;
    }


}