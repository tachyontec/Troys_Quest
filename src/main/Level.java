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
    public int coinCounter;
    public boolean hasEnemies;
    public boolean hasFinalBoss;
    public Handler handler;
    Random rand = new Random();
    public boolean hasBlocks;


    public Level(GamePanel gamePanel, String txtPath,
                        TileManager tileM, String[] obstacleName, String[] enemyName , boolean hasEnemies, boolean hasFinalBoss, boolean hasBlocks) {
        this.gamePanel = gamePanel;
        this.txtPath = txtPath;
        this.obstacleName = obstacleName;
        this.enemyName = enemyName;
        this.tileM = tileM;
        this.hasEnemies = hasEnemies;
        handler = new Handler(gamePanel.player);
        this.hasFinalBoss = hasFinalBoss;
        this.hasBlocks = hasBlocks;
    }

    public void setupLevel() {
        setupBackground();
        setupGameObjects();
    }


    /*this method will setup the Background image of the Level*/
    public void setupBackground() {
        tileM.loadMap(txtPath);
    }

    /*this method will load the gameObjects(Enemies,Coins,Obstacles,Blocks)*/
    public void setupGameObjects() {
        int startingpoint = 9 * gamePanel.tileSize; //starting point in map is where the player spawns
        //we spawn 4 times in a row an obstacle and the we spawn an enemy for our player to fight
        for (int i = 1; i <= 20; i++) {
            int randomTile = rand.nextInt(3);
            int spawnX = startingpoint + randomTile * gamePanel.tileSize;
            if (i % 4 == 0 && hasEnemies) {
                Enemy enemy = new Enemy(spawnX, 8 * gamePanel.tileSize,
                        1, 0, 3 * gamePanel.tileSize, 3 * gamePanel.tileSize, enemyName[0], gamePanel, 1);
                // so that the enemy touches the ground , because minotaur png's are not the resolution we need
                //enemy.y = (int) enemy.worldY + gamePanel.tileSize / 2;
                handler.enemies.add(enemy);
            } else {
                // used to choose randomly
                // one of the 2 obstacles(Fire or spikesRoller)
                handler.obstacles.add(new Obstacle(spawnX, gamePanel.floor, 0,
                        0, 30, gamePanel.tileSize, obstacleName[GamePanel.currentLevel == 3 ? rand.nextInt(2) : 0 ], gamePanel));
                /* we spawn a coin above an obstacle with a random way to make player
                jump a little or a lot to reach it and also make game harder*/
                int randomY = (rand.nextInt(3) + 1);
                handler.coinlist.add(new Coin(spawnX, gamePanel.floor - (randomY * gamePanel.tileSize),
                        0, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel));
            }
            if(this.hasBlocks) {
                //we spawn blocks on top of obstacles or enemies
                handler.blockArrayList.add(new Block(spawnX + gamePanel.tileSize, 6.5 * gamePanel.tileSize, 0,0,
                        gamePanel.tileSize, gamePanel.tileSize,gamePanel));
            }
            // we spawn coins after an obstacle or enemy for reward
            handler.coinlist.add(new Coin(spawnX + 2 * gamePanel.tileSize, gamePanel.floor,
                    0, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel));
            startingpoint += 5 * gamePanel.tileSize;
            if(GamePanel.currentLevel == 3 && i == 18) {
                break;
            }
        }
        Bird bird = new Bird(18 * gamePanel.tileSize, 5 * gamePanel.tileSize,
                4, 0, gamePanel.tileSize, gamePanel.tileSize, gamePanel, "Bird");
        handler.obstacles.add(bird);
        if (this.hasFinalBoss) {
            Enemy enemy = new Enemy( (gamePanel.maxWorldCol - 10) * gamePanel.tileSize, 7 * gamePanel.tileSize,
                    1, 0, 3 * gamePanel.tileSize, 3 * gamePanel.tileSize, enemyName[1], gamePanel, 3);
            handler.enemies.add(enemy);
        }
    }

    //since arrow spawning is dynamic , we need to handle it separately from level obstacle layout and call it repetitively from gamepanel.update()
    public void addArrow() {
        if (gamePanel.hud.counter % 180 == 0) { //every 3 seconds we spawn an arrow since our game updates itself 60 times per second (FPS)
            //We cut up the level into 4 areas so that arrows spawn throughout the whole map and the player never sees them appear out of thin air
            if ((gamePanel.player.getX() < 25 * gamePanel.tileSize)) { //1st area
                MovingObstacle arrow = new MovingObstacle(50 * gamePanel.tileSize,
                        gamePanel.floor - rand.nextInt(4) * gamePanel.tileSize,
                        4, 4, gamePanel.tileSize, gamePanel.tileSize, "arrow", gamePanel);
                arrow.height -= 30;// changed arrows rectangle height manually to match the actual shape of the arrow and adjust collision box
                arrow.y += 14;// changed arrows rectangle y manually to match the actual shape of the arrow and adjust collision box
                handler.obstacles.add(arrow);
            } else if (gamePanel.player.getX() < 50 * gamePanel.tileSize) { //2nd area
                MovingObstacle arrow = new MovingObstacle(75 * gamePanel.tileSize,
                        gamePanel.floor - rand.nextInt(4) * gamePanel.tileSize,
                        4, 4, gamePanel.tileSize, gamePanel.tileSize, "arrow", gamePanel);
                arrow.height -= 30;
                arrow.y += 14;
                handler.obstacles.add(arrow);
            } else if (gamePanel.player.getX() < 75 * gamePanel.tileSize) { //3rd area
                MovingObstacle arrow = new MovingObstacle(100 * gamePanel.tileSize,
                        gamePanel.floor - rand.nextInt(4) * gamePanel.tileSize,
                        4, 4, gamePanel.tileSize, gamePanel.tileSize, "arrow", gamePanel);
                arrow.height -= 30;
                arrow.y += 14;
                handler.obstacles.add(arrow);
            } else if (gamePanel.player.getX() < 95 * gamePanel.tileSize) { //4th area
                MovingObstacle arrow = new MovingObstacle(115 * gamePanel.tileSize,
                        gamePanel.floor - rand.nextInt(4) * gamePanel.tileSize,
                        4, 4, gamePanel.tileSize, gamePanel.tileSize, "arrow", gamePanel);
                arrow.height -= 30;
                arrow.y += 14;
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

    public int calculateScore( double levelCompletionTime, int livesLeft , int coinsCollected , int enemiesKilled) {
        //In order to calculate points based on time , we set 4 time zones
        //As we move up in time , points from completing the level in each time zone decrease
        //rewarding the player for making it to the end faster
        int pointsFromTime;
        if (levelCompletionTime <= 15) {
            pointsFromTime = 800;
        } else if (levelCompletionTime <= 30){
            pointsFromTime = 600;
        } else if (levelCompletionTime <= 45){
            pointsFromTime = 400;
        } else if (levelCompletionTime <= 60){
            pointsFromTime = 200;
        } else{
            pointsFromTime = 100;
        }
        //We reward the player with 100 points for each heart he has left
        //We reward the player with 10 points for each coin he has collected
        //We reward the player with 50 points for each enemy he has killed
        return pointsFromTime + 100 * livesLeft + 10 * coinsCollected + 50 * enemiesKilled;


    }


}
