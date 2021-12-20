package objects;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> obstacleLinkedList;
    ArrayList<Enemy> enemies;
    LinkedList<Coin> coinlist;
    Player player;
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    double timer;
    double collisionTime = 0;
    double enemyDeathTime = 0;

    public Handler(LinkedList<GameObject> obstacleLinkedList, Player player,
                   ArrayList<Enemy> enemies, LinkedList<Coin> coinlist) {
        this.obstacleLinkedList = obstacleLinkedList;
        this.player = player;
        this.enemies = enemies;
        this.coinlist = coinlist;
    }

    public void tick() {
        for (GameObject object : obstacleLinkedList) {
            object.tick();
        }
        for (Enemy enemy : enemies) {
            enemy.tick();
        }

        for (Coin coin : coinlist) {
            coin.tick();
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

        for(Coin coin : coinlist) {
            coin.render(g2);
        }
    }

    /*
    Checks for collision of the player with any obstacles
    if a player collides,he is invulnerable for a couple of seconds
    */
    public boolean checkPlayerCollision() {

        decFormat.format(timer);
        timer += (double) 1 / 60;

        boolean b = false;

        if (timer - collisionTime > 2.00) {
            player.setCollision(true);
        }

        for (GameObject object : obstacleLinkedList) {
            if (object.intersects(player) && player.isCollision()) {
                b = true;
                player.setCollision(false);
                collisionTime = timer;
                break;
            }
            if (object.intersects(player)) {
                player.setX(player.getX() - 20);//so as not to go "into" obstacles
            }

        }

        for (Enemy enemy : enemies) {
            if (enemy.intersects(player) && player.isCollision() && enemy.livesLeft !=0) {
                b = true;
                player.setCollision(false);
                collisionTime = timer;
                player.setX(player.getX() - 20);//so as not to go "into" enemies
                break;
            }
            //handling the collision of all the coins in game
            for (Coin coin : coinlist) {
                if(coin.checkCollision()) {
                    coinlist.remove(coin);
                    break;
                }
            }

        }
        return b;
    }

    public void checkEnemyCollision() {
        for (Enemy enemy : enemies) {
            if (enemy.intersects(player.attackHitbox) &&
                    player.isAttackCollision && enemy.livesLeft > 0) {
                enemy.livesLeft = 0;
                this.enemyDeathTime = this.timer;
            }
            if (this.timer - this.enemyDeathTime > 1 && enemy.livesLeft == 0) {
                enemies.remove(enemy);
                break;//so that when an enemy is removes the for loop doesn't crash
            }
        }
    }


}