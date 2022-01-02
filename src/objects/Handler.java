package objects;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> obstacles = new LinkedList<>();
    public  LinkedList<Enemy> enemies = new LinkedList<>();
    public LinkedList<Coin> coinlist = new LinkedList<>();
    public LinkedList<Block> blockArrayList = new LinkedList<>();
    //Create an Array to irretate over all lists at once
    private final LinkedList[] all = new LinkedList[]{obstacles, enemies, coinlist ,blockArrayList};
    Player player;
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    double timer;
    double collisionTime = 0;
    double enemyDeathTime = 0;

    public Handler(Player player) {
        this.player = player;
    }

    public void update() {
        for (LinkedList l : all) {
            for (Object x : l) {
                ((GameObject) x).update();
            }
        }
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.RED);
        for (LinkedList l : all) {
            for (Object x : l) {
                ((GameObject) x).render(g2);
            }
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

        for (GameObject object : obstacles) {
            if (object.intersects(player) && player.isCollision()) {
                b = true;
                player.setCollision(false);
                collisionTime = timer;
                break;
            }
            if (object.intersects(player) && !object.getClass().equals(Bird.class) && !object.getClass().equals(MovingObstacle.class)) {
                player.setX(player.getX() - 20);//so as not to go "into" obstacles

            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.intersects(player) && player.isCollision() && enemy.isCollision()) {
                b = true;
                player.setCollision(false);
                collisionTime = timer;
                player.setX(player.getX() - 20);//so as not to go "into" obstacles
                break;
            }
        }
        //handling the collision of all the coins in game
        for (Coin coin : coinlist) {
            if (coin.checkCollision()) {
                coinlist.remove(coin);
                break;
            }
        }

        return b;
    }

    public void checkEnemyCollision() {
        for (Enemy enemy : enemies) {
            if (this.timer - enemy.colissionTime > 1) {
                enemy.setCollision(true);
            }
            if (enemy.intersects(player.attackHitbox) &&
                    player.isAttackCollision && enemy.livesLeft > 0 && enemy.isCollision()) {
                enemy.livesLeft -= 1;
                enemy.setCollision(false);
                enemy.colissionTime = this.timer;
                System.out.println(enemy.livesLeft);
                if (enemy.livesLeft==0) {
                    this.enemyDeathTime = this.timer;
                    enemy.setCollision(false);
                    player.setEnemiesKilled(player.getEnemiesKilled() + 1);
                }
            }
            if (this.timer - this.enemyDeathTime > 1 && enemy.livesLeft == 0) {
                enemies.remove(enemy);
                break;//so that when an enemy is removes the for loop doesn't crash
            }
        }
    }
}

