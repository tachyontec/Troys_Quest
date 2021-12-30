package objects;

import main.GamePanel;

import java.util.Random;

public class GameObjectSetter {

    GamePanel gamePanel;
    String[] str = new String[2];//string array that contains the names of the obstacles
    Random rand = new Random();

    public GameObjectSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //floor is Y=8
    //initializes obstacles in obstacles array with their image icon
    // x ,y position relative to the map (worldX , worldY)
    public void setObject() {

        str[0] = "Fire";
        str[1] = "spikesRoller";
        int startingpoint = 9 * gamePanel.tileSize; //starting point in map is where the player spawns

        if (GamePanel.currentLevel == 1) {
            //We divide the map witch is 16x102 tiles in areas along
            // the x axis containing 5 tiles each like so 1._2._3._4._5._.
            //Each area has 3 tiles on witch obstacles or enemies are spawnable and 2 tiles that are void of objects.
            // to be implemented as new obstacles get added!
            /*we spawn 4 times in a row an obstacle and the we spawn an enemy for our player to fight.*/
            for (int i = 1; i <= 20; i++) {
                int randomTile = rand.nextInt(3);
                int spawnX = startingpoint + randomTile * gamePanel.tileSize;
                //spawn a spike roller
                Handler.obstacles.add(new Obstacle(spawnX, gamePanel.floor, 0,
                        0, 30, gamePanel.tileSize, str[1], gamePanel));
                    /* we spawn a coin above an obstacle with a random way to make player
                    jump a little or a lot to reach it and also make game harder*/
                int randomY = (rand.nextInt(3) + 1);
                Handler.coinlist.add(new Coin(spawnX, gamePanel.floor - (randomY * gamePanel.tileSize),
                        0, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel));
                // we spawn coins after an obstacle or enemy for reward
                Handler.coinlist.add(new Coin(spawnX + 2 * gamePanel.tileSize, gamePanel.floor,
                        0, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel));
                startingpoint += 5 * gamePanel.tileSize;
            }
            Bird bird = new Bird(18 * gamePanel.tileSize, 5 * gamePanel.tileSize,
                    4, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel, "Bird");
            Handler.obstacles.add(bird);
        } else if (GamePanel.currentLevel == 2) {
            /*we spawn 4 times in a row an obstacle and the we spawn an enemy for our player to fight.*/
            for (int i = 1; i <= 20; i++) {
                int randomTile = rand.nextInt(3);
                int spawnX = startingpoint + randomTile * gamePanel.tileSize;
                if (i % 4 == 0) {
                    Enemy enemy = new Enemy(spawnX, 8.6 * gamePanel.tileSize,
                            1, 0, 3 * gamePanel.tileSize,3 * gamePanel.tileSize, "Minotaur", gamePanel);
                    // so that the enemy touches the ground , because minotaur png's are not the resolution we need
                    //enemy.y = (int) enemy.worldY + gamePanel.tileSize / 2;
                    Handler.enemies.add(enemy);
                } else {
                    // used to choose randomly
                    // one of the 2 obstacles(Fire or spikesRoller)
                    Handler.obstacles.add(new Obstacle(spawnX, gamePanel.floor, 0,
                            0, 30, gamePanel.tileSize, str[0], gamePanel));
                /* we spawn a coin above an obstacle with a random way to make player
                jump a little or a lot to reach it and also make game harder*/
                    int randomY = (rand.nextInt(3) +1);
                    Handler.coinlist.add(new Coin(spawnX , gamePanel.floor - (randomY * gamePanel.tileSize),
                            0, 0, gamePanel.tileSize, gamePanel.tileSize,gamePanel ));
                }
                // we spawn coins after an obstacle or enemy for reward
                Handler.coinlist.add(new Coin(spawnX + 2 * gamePanel.tileSize, gamePanel.floor,
                        0, 0, gamePanel.tileSize, gamePanel.tileSize,gamePanel ));
                startingpoint += 5 * gamePanel.tileSize;
            }
            Bird bird = new Bird(18 * gamePanel.tileSize, 5 * gamePanel.tileSize,
                    4,0,gamePanel.tileSize,gamePanel.tileSize,gamePanel,"Bird");
            Handler.obstacles.add(bird);
        } else {
            Enemy enemy = new Enemy(16 * gamePanel.tileSize, 8.6 * gamePanel.tileSize,
                    1, 0, 3 * gamePanel.tileSize,3 * gamePanel.tileSize, "Minotaur", gamePanel);
            // so that the enemy touches the ground , because minotaur png's are not the resolution we need
            enemy.y = (int) enemy.worldY + gamePanel.tileSize / 2;
            Handler.enemies.add(enemy);
        }
    }

    //since arrow spawning is dynamic , we need to handle it separately from level obstacle layout and call it repetitively from gamepanel.update()
    //TO BE OPTIMIZED --> CUT MAP INTO AREAS OF 20 TILES , CHECK PLAYER'S worldX and spawn them along as the player moves between them
    //change arrow rectangle
    public void addArrow() {
        if (gamePanel.player.getX() < 85 * gamePanel.tileSize) {
            if ((gamePanel.hud.counter % 180 == 0)) {
                MovingObstacle arrow = new MovingObstacle(100 * gamePanel.tileSize,
                        gamePanel.floor - rand.nextInt(4) * gamePanel.tileSize,
                        4, 4, gamePanel.tileSize, gamePanel.tileSize, "arrow", gamePanel);
                arrow.height -= 30;// changed arrows rectangle height because of the way Rectangles are drawn and made
                arrow.y += 14;// changed arrows rectangle y because of the way Rectangles are drawn and made
                Handler.obstacles.add(arrow);
            }
        }
    }


    //clears all the objects created in the game by emptying the lists that contain them
    public void clearObjects() {
        Handler.enemies.clear();
        Handler.obstacles.clear();
        Handler.coinlist.clear();
    }


}