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
        g2.setColor(Color.RED);
        for (int i = 0; i < obstacleLinkedList.size(); i++) {
            //g2.drawRect(obstacleLinkedList.get(i).x,obstacleLinkedList.get(i).y,obstacleLinkedList.get(i).width,obstacleLinkedList.get(i).height);
            obstacleLinkedList.get(i).render(g2);

        }
    }

    public boolean checkcollision () {
        boolean b = false;
        for(int i =0;i < obstacleLinkedList.size(); i++) {
            if(obstacleLinkedList.get(i).intersects(player)){
                b = true;
                player.setX(player.getX()-40);
                break;
            }

        }
        return b;
    }


}