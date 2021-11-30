package objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    LinkedList<Obstacle> obstacleLinkedList =new LinkedList<>();
    Player player;

    public Handler(LinkedList obstacleLinkedList,Player player) {
        this.obstacleLinkedList = obstacleLinkedList;
        this.player = player;
    }

    public void tick() {
        for(int i=0;i<obstacleLinkedList.size();i++) {
            obstacleLinkedList.get(i).tick();
        }
    }

    public void render(Graphics2D g2) {
        for (int i = 0; i < obstacleLinkedList.size(); i++) {
            obstacleLinkedList.get(i).render(g2);
        }
    }

    public boolean checkcollision () {
        boolean b = false;
        for(int i =0;i < obstacleLinkedList.size(); i++) {
            if(obstacleLinkedList.get(i).intersects(player)){
                b = true;
            }
            break;
        }
        return b;
    }


}