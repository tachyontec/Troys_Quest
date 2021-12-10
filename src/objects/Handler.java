package objects;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> obstacleLinkedList;
    LinkedList<Enemy> enemies;
    Player player;
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    double collisionTimer;
    double collisionTime=0;

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
            //g2.drawRect(object.x,object.y,object.width,object  .height);
            object.render(g2);

        }

        for (Enemy enemy : enemies) {
            enemy.render(g2);
        }
    }
    /*
    Checks for collision of the player with any obstacles
    if a player collides,he is invulnerable for a couple of seconds
    */
    public boolean checkcollision () {
        decFormat.format(collisionTimer);
        collisionTimer += (double) 1/60;
        boolean b = false;
        if(collisionTimer - collisionTime > 10.00) {
            player.setCollision(true);
        }
        for (GameObject object : obstacleLinkedList) {
            if (object.intersects(player) && player.isCollision()) {

                b = true;
                player.setCollision(false);
                collisionTime = collisionTimer;
                break;
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.intersects(player) && player.isCollision()) {
                b = true;
                player.setCollision(false);
                collisionTime = collisionTimer;
                break;
            }
        }
        return b;
    }


}