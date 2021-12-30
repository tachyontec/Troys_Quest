package main;

import objects.*;
import tiles.TileManager;

import java.util.Random;

public class Level {

    public String txtPath;
    public String [] obstacleName;
    public String [] enemyName;
    GamePanel gamePanel;
    TileManager tileM;
    public int enemyCounter;
    public int coinCounter;
    public boolean hasEnemies;
    public Handler handler;
    Random rand = new Random();


    public Level(GamePanel gamePanel, String txtPath,
                        TileManager tileM, String[] obstacleName, String[] enemyName , boolean hasEnemies) {
        this.gamePanel = gamePanel;
        this.txtPath = txtPath;
        this.obstacleName = obstacleName;
        this.enemyName = enemyName;
        this.tileM = tileM;
        this.hasEnemies = hasEnemies;
        handler = new Handler(gamePanel.player);
    }

    public void setupLevel() {
        setupBackground();
        setupGameObjects();
    }


    /*this method will setup the Background image of the Level*/
    public void setupBackground() {
        tileM.loadMap(txtPath);
    }

    /*this method will load the gameObjects(Enemies,Coins,Obstacles)*/
    public void setupGameObjects() {
        int startingpoint = 9 * gamePanel.tileSize; //starting point in map is where the player spawns
        //we spawn 4 times in a row an obstacle and the we spawn an enemy for our player to fight
        for (int i = 1; i <= 20; i++) {
            int randomTile = rand.nextInt(3);
            int spawnX = startingpoint + randomTile * gamePanel.tileSize;
            if (i % 4 == 0 && hasEnemies == true) {
                Enemy enemy = new Enemy(spawnX, 8.6 * gamePanel.tileSize,
                        1, 0, 3 * gamePanel.tileSize, 3 * gamePanel.tileSize, enemyName[0], gamePanel);
                // so that the enemy touches the ground , because minotaur png's are not the resolution we need
                //enemy.y = (int) enemy.worldY + gamePanel.tileSize / 2;
                handler.enemies.add(enemy);
            } else {
                // used to choose randomly
                // one of the 2 obstacles(Fire or spikesRoller)
                handler.obstacles.add(new Obstacle(spawnX, gamePanel.floor, 0,
                        0, 30, gamePanel.tileSize, obstacleName[0], gamePanel));
                /* we spawn a coin above an obstacle with a random way to make player
                jump a little or a lot to reach it and also make game harder*/
                int randomY = (rand.nextInt(3) + 1);
                handler.coinlist.add(new Coin(spawnX, gamePanel.floor - (randomY * gamePanel.tileSize),
                        0, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel));
            }
            // we spawn coins after an obstacle or enemy for reward
            handler.coinlist.add(new Coin(spawnX + 2 * gamePanel.tileSize, gamePanel.floor,
                    0, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel));
            startingpoint += 5 * gamePanel.tileSize;
        }
        Bird bird = new Bird(18 * gamePanel.tileSize, 5 * gamePanel.tileSize,
                4, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel, "Bird");
        handler.obstacles.add(bird);
    }


    public void addArrow() {
        if (gamePanel.player.getX() < 85 * gamePanel.tileSize) {
            if ((gamePanel.hud.counter % 180 == 0)) {
                MovingObstacle arrow = new MovingObstacle(100 * gamePanel.tileSize,
                        gamePanel.floor - rand.nextInt(4) * gamePanel.tileSize,
                        4, 4, gamePanel.tileSize, gamePanel.tileSize, "arrow", gamePanel);
                arrow.height -= 30;// changed arrows rectangle height because of the way Rectangles are drawn and made
                arrow.y += 14;// changed arrows rectangle y because of the way Rectangles are drawn and made
                handler.obstacles.add(arrow);
            }
        }
    }


    //clears all the objects created in the game by emptying the lists that contain them
    public void clearObjects() {
        handler.enemies.clear();
        handler.obstacles.clear();
        handler.coinlist.clear();
    }

}
