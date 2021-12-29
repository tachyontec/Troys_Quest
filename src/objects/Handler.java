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

        if (timer - collisionTime > 2.00) {//if player is invulnerable for 2 seconds , make him vulnerable again
            player.setCollision(true);
        }

        for (GameObject object : obstacleLinkedList) {//for every object that may harm the player
            if (object.intersects(player) && player.isCollision()) {//if object collides with player while player is vulnerable
                b = true;//return true , meaning that the player is hit
                player.setCollision(false);//make player invulnerable
                collisionTime = timer;//store the time that the player is hit so that we can revert his state to vulnerable in time
                break;
            }
            //if an object that is not a bird or an arrow intersects with our player , we want the player to move back so that he can't just go through the obstacles , he has to avoid them
            if (object.intersects(player) && !object.getClass().equals(Bird.class) && !object.getClass().equals(MovingObstacle.class)) {
                player.setX(player.getX() - 20);//so as not to go "into" obstacles
            }
        }

            for (Enemy enemy : enemies) {//for every enemyu on the level
                //if both player and enemy are in a state that enables them to collide
                if (enemy.intersects(player) && player.isCollision() && enemy.isCollision()) {
                    b = true;//return that player is hit
                    player.setCollision(false);//make player invulnerable
                    collisionTime = timer;//store the time that the player is hit so that we can revert his state to vulnerable in time
                    player.setX(player.getX() - 20);//so as not to go "into" enemies
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
        //checks whether an enemy is hit or not , and removes enemies when they die
        public void checkEnemyCollision () {
            for (Enemy enemy : enemies) {//for every enemy
                //if enemy collides with players' attack hitbox (his sword) and player is attacking (pressing space) and the enemy is alive and ccollidable
                if (enemy.intersects(player.attackHitbox) &&
                        player.isAttackCollision && enemy.livesLeft > 0 && enemy.isCollision()) {
                    enemy.livesLeft = 0;//kill enemy
                    this.enemyDeathTime = this.timer;//store enemys' death time so that we remove them from the map after death animation is done
                    enemy.setCollision(false);//make it so that enemy can't hit us when he is dead

                }
                //wait for death animation to play , then remove enemy
                if (this.timer - this.enemyDeathTime > 1 && enemy.livesLeft == 0) {
                    enemies.remove(enemy);
                    break;//so that when an enemy is removes the for loop doesn't crash
                }
            }
        }
    }

