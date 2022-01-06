package objects;

import sounds.Sound;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * This class Handles all game objects and ticks and renders them
 * It organizes objects in different lists to achieve this and
 * in the end of every level all lists are cleared to get ready
 * for the next level and save RAM space
 */
public class Handler {
    private LinkedList<GameObject> obstacles = new LinkedList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LinkedList<Coin> coinlist = new LinkedList<>();
    private LinkedList<Block> blockArrayList = new LinkedList<>();
    //Create an Array to irretate over all lists at once
    private final LinkedList[] all = new LinkedList[]{obstacles, enemies,
            coinlist, blockArrayList};
    Player player;
    Sound soundEffect = new Sound();
    DecimalFormat decFormat = new DecimalFormat("#0.00");
    double timer;
    double collisionTime = 0;
    double enemyDeathTime = 0;
    /**
     * We only pass Player as a parameter because all the other objects
     * are added with the add() method in each Level class
     */
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

    /**
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
            if (object.intersects(player) && player.isCollidable() && !object.getClass().equals(Bird.class)) {
                b = true;
                player.setCollision(false);
                collisionTime = timer;
                break;
            }
            if (object.intersects(player) && !object.getClass().equals(Bird.class)
                    && !object.getClass().equals(MovingObstacle.class)) {
                player.setX(player.getX() - 20);//so as not to go "into" obstacles

            }
        }
        //this is used to check if player has collided with the top of a block.
        // if yes we dont check collision with the next block but we keep checking with the same block
        boolean hascollided = false;
        int i = 0;
        while (i < blockArrayList.size() && hascollided == false) {
            Block block = blockArrayList.get(i);
            //right collision with platform
            if (player.intersectsLine(block.rightLine) && player.counter < 1) {
                hascollided = false;
                player.counter++;
                player.setSpeedy(0);
                player.setSpeedx(0);
            }
            //left collision with platform
            if (player.intersectsLine(block.leftLine) && player.counter < 1) {
                hascollided = false;
                player.counter++;
                player.setSpeedy(0);
                player.setSpeedx(0);
            }
            //bottom collision with platform
            if (player.intersectsLine(block.bottomLine) && player.counter < 1) {
                hascollided = false;
                player.counter++;
                player.setSpeedy(0);
            }
            //top collision with platform
            if (player.intersectsLine(block.topLine)) {
                hascollided = true;
                player.counter++;
                player.floor = block.platformfloor - player.height;
                player.worldY = block.platformfloor - player.height;
                player.screenY = (int) (block.platformfloor - player.height);
                player.jumped = false;
            } else {
                player.floor = player.gamePanel.floor;
            }
            i++;
        }

        for (Enemy enemy : enemies) {
            if (enemy.intersects(player) && player.isCollidable() && enemy.isCollision()) {
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
                soundEffect.playSE(4);
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
                //System.out.println(enemy.livesLeft);
                if (enemy.livesLeft == 0) {
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

    /**
     * Accepts a game object (x) and decides in what list to put it in
     * So we check collisions in the right way
     * @param x: Game object that we want to add
     */
    public void add(GameObject x) {
        if (x.getClass().equals(Coin.class)) {
            coinlist.add((Coin) x);
        } else if (x.getClass().equals(Enemy.class)) {
            enemies.add((Enemy) x);
        } else if (x.getClass().equals(Block.class)) {
            blockArrayList.add((Block) x);
        } else {
            obstacles.add(x);
        }
    }

    /**
     * Clears all objects in all lists
     * This is used before initializing a new level
     */
    public void clear() {
        for (LinkedList l : all) {
            l.clear();
        }
    }
}

