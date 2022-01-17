package main.game;

import objects.*;
import tiles.TileManager;

import java.awt.*;
import java.util.Random;

/**
 * class Level
 * used to create instances for every level of our game
 * instances of Level contain data for the map Layout , the obstacles , the enemies and additional data for other usages
 */
public class Level {

    public String txtPath;
    public String[] obstacleName;
    public String[] enemyName;
    public TileManager tileM;
    public boolean hasEnemies;
    public boolean hasFinalBoss;
    public Handler handler;
    Random rand = new Random();
    public boolean hasBlocks;
    public GamePanel gamePanel;

    /**
     * Creates instances for levels
     *
     * @param gamePanel    passes the instance of gamepanel that we use to play the game and handles the level in the bounds of that instance
     * @param txtPath      the file path for the .txt file containing the data for the background data layout
     * @param tileM        the 2d array containng the data of the aforementioned txtPath .txt file
     * @param obstacleName an array containing the names of every obstacle that is present on the specific level
     * @param enemyName    an array containing the names of every enemy that is present on the specific level
     * @param hasEnemies   boolean value that states whether a level has enemies present or not
     * @param hasFinalBoss boolean value that states whether a level has a final bos or not
     * @param hasBlocks    boolean value that states whether a level has blocks present or not
     */
    public Level(GamePanel gamePanel, String txtPath,
                 TileManager tileM, String[] obstacleName, String[] enemyName, boolean hasEnemies, boolean hasFinalBoss, boolean hasBlocks) {
        this.gamePanel = gamePanel;
        this.txtPath = txtPath;
        this.obstacleName = obstacleName;
        this.enemyName = enemyName;
        this.tileM = tileM;
        this.hasEnemies = hasEnemies;
        handler = gamePanel.handler;
        this.hasFinalBoss = hasFinalBoss;
        this.hasBlocks = hasBlocks;
    }

    public Level() {
    }

    /**
     * sets up the background tiles of the level the instance represents
     * sets up everything in our handler lists enemies/tiles/obstacles/coins
     */
    public void setupLevel() {
        setupBackground();
        setupGameObjects();
    }

    /**
     * updates everything present on our level
     * enemies/tiles/obstacles/coins/bounds etc
     * checks whether player take damage
     * plays player takes damage sound
     */
    public void update() {
        handler.update();
    }

    /**
     * renders anything present on our level
     * tileM.render(g2) renders the tiles of the background
     * handler.render(g2) renders any enemies/obstacles/coins contained in the lists where every game object is stored
     *
     * @param g2 graphics instance used to draw
     */
    public void render(Graphics2D g2) {
        tileM.render(g2);
        handler.render(g2);
    }


    /**
     * sets up the background for our level
     * takes the .txt file o our specific level , parses the data via loadmap()
     * and decides what tiles are drawn where
     */
    public void setupBackground() {
        this.tileM.loadMap(txtPath);
    }

    /**
     * Sets up the objects that a player encounters in each level
     * obstacles are placed differently depending on the level number ,
     * whether it has enemies/final boss
     * no parameters passed , different levels handled internally via values of instance variables
     */
    public void setupGameObjects() {
        int startingpoint = 9 * GamePanel.TILE_SIZE; //starting point in map is where the player spawns
        //we spawn 4 times in a row an obstacle and the we spawn an enemy for our player to fight
        for (int i = 1; i <= 20; i++) {
            int randomTile = rand.nextInt(3);
            int spawnX = startingpoint + randomTile * GamePanel.TILE_SIZE;
            if (i % 4 == 0 && hasEnemies) {
                Enemy enemy = new Enemy(spawnX, 8 * GamePanel.TILE_SIZE,
                        3 * GamePanel.TILE_SIZE, 3 * GamePanel.TILE_SIZE, enemyName[0], gamePanel, 1, 0, 1);
                // so that the enemy touches the ground , because minotaur png's are not the resolution we need
                //enemy.y = (int) enemy.worldY + gamePanel.tileSize / 2;
                handler.add(enemy);
            } else {
                // used to choose randomly
                // one of the 2 obstacles(Fire or spikesRoller)
                handler.add(new Obstacle(spawnX, GamePanel.FLOOR, 30, GamePanel.TILE_SIZE,
                        "spikesRoller", gamePanel, 3));
                /* we spawn a coin above an obstacle with a random way to make player
                jump a little or a lot to reach it and also make game harder*/
                int randomY = (rand.nextInt(2) + 1);
                handler.add(new Coin(spawnX, GamePanel.FLOOR - (randomY * GamePanel.TILE_SIZE),
                        GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE / 2, "Coin", gamePanel, 4));
            }
            if (this.hasBlocks) {
                if (rand.nextInt(100) < 60) {
                    handler.add(new Block(spawnX, 5.5 * GamePanel.TILE_SIZE,
                            GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, ("Level" + GamePanel.currentLevelNumber + "plat"), gamePanel, 1));
                    if (rand.nextInt(100) < 60 - GamePanel.currentLevelNumber * 10) {
                        handler.add(new Coin(spawnX, 4.5 * GamePanel.TILE_SIZE,
                                GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE / 2, "Coin", gamePanel, 4));
                    } else {
                        if (GamePanel.currentLevelNumber == 3) {
                            int randomobj = rand.nextInt(2);
                            if (randomobj == 0) {
                                handler.add(new Obstacle(spawnX, 4.5 * GamePanel.TILE_SIZE, 30, GamePanel.TILE_SIZE,
                                        obstacleName[0], gamePanel, 3));
                            } else {
                                handler.add(new Obstacle(spawnX, 4.5 * GamePanel.TILE_SIZE, 30, GamePanel.TILE_SIZE,
                                        obstacleName[1], gamePanel, 5));
                            }
                        } else {
                            handler.add(new Obstacle(spawnX, 4.5 * GamePanel.TILE_SIZE, 30, GamePanel.TILE_SIZE,
                                    obstacleName[0], gamePanel, 3));
                        }
                    }
                    if (rand.nextInt(100) < 65) {
                        handler.add(new Block(spawnX + 3 * GamePanel.TILE_SIZE, 3.5 * GamePanel.TILE_SIZE,
                                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Level" + GamePanel.currentLevelNumber + "plat", gamePanel, 1));
                        if (rand.nextInt(100) < 70 - GamePanel.currentLevelNumber * 15) {
                            Heart heart = new Heart(spawnX + 3 * GamePanel.TILE_SIZE, 1.5 * GamePanel.TILE_SIZE,
                                    GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Heart", gamePanel, 1);
                            heart.y += GamePanel.TILE_SIZE;
                            handler.add(heart);
                        }
                    }
                }
            }

            startingpoint += 5 * GamePanel.TILE_SIZE;
            if (GamePanel.currentLevelNumber == 3 && i == 18) {
                break;
            }
        }
        Bird bird = new Bird(18 * GamePanel.TILE_SIZE, 5 * GamePanel.TILE_SIZE,
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Bird", gamePanel, 4, 0);
        handler.add(bird);
        if (this.hasFinalBoss) {
            Enemy enemy = new Enemy((GamePanel.MAX_WORLD_COL - 10) * GamePanel.TILE_SIZE,
                    7 * GamePanel.TILE_SIZE, 3 * GamePanel.TILE_SIZE, 3 * GamePanel.TILE_SIZE,
                    enemyName[1], gamePanel, 1, 0, 3);
            handler.add(enemy);
        }
    }

    /**
     * Adds an arrow on the level from the instance of which the method is called
     * since arrow spawning is dynamic , we need to handle it separately from level obstacle layout and call it repetitively from gamepanel.update()
     */
    public void addArrow() {
        if (gamePanel.hud.counter % 180 == 0) { //every 3 seconds we spawn an arrow since our game updates itself 60 times per second (FPS)
            //We cut up the level into 4 areas so that arrows spawn throughout the whole map and the player never sees them appear out of thin air
            if ((gamePanel.player.getX() < 25 * GamePanel.TILE_SIZE)) { //1st area
                MovingObstacle arrow = new MovingObstacle(50 * GamePanel.TILE_SIZE,
                        GamePanel.FLOOR - rand.nextInt(6) * GamePanel.TILE_SIZE,
                        4, 4, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Arrow", gamePanel);
                arrow.height -=
                        30;// changed arrows rectangle height manually to match the actual shape of the arrow and adjust collision box
                arrow.y +=
                        14;// changed arrows rectangle y manually to match the actual shape of the arrow and adjust collision box
                handler.add(arrow);
            } else if (gamePanel.player.getX() < 50 * GamePanel.TILE_SIZE) { //2nd area
                MovingObstacle arrow = new MovingObstacle(75 * GamePanel.TILE_SIZE,
                        GamePanel.FLOOR - rand.nextInt(6) * GamePanel.TILE_SIZE,
                        4, 4, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Arrow", gamePanel);
                arrow.height -= 30;
                arrow.y += 14;
                handler.add(arrow);
            } else if (gamePanel.player.getX() < 75 * GamePanel.TILE_SIZE) { //3rd area
                MovingObstacle arrow = new MovingObstacle(100 * GamePanel.TILE_SIZE,
                        GamePanel.FLOOR - rand.nextInt(6) * GamePanel.TILE_SIZE,
                        4, 4, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Arrow", gamePanel);
                arrow.height -= 30;
                arrow.y += 14;
                handler.add(arrow);
            } else if (gamePanel.player.getX() < 95 * GamePanel.TILE_SIZE) { //4th area
                MovingObstacle arrow = new MovingObstacle(115 * GamePanel.TILE_SIZE,
                        GamePanel.FLOOR - rand.nextInt(6) * GamePanel.TILE_SIZE,
                        4, 4, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, "Arrow", gamePanel);
                arrow.height -= 30;
                arrow.y += 14;
                handler.add(arrow);
            }
        }
    }

    /**
     * Calculates the score that a player has achieved when he finishes a level
     *
     * @param levelCompletionTime = the time that a player took to complete a level
     * @param livesLeft           = the numbers of lives left that the player had when level ended
     * @param coinsCollected      = the number of coins player collected
     * @param enemiesKilled       = the number of enemies player killed in the level
     * @return int value of the calculated score , computed in line @see 195
     */
    public int calculateScore(double levelCompletionTime, int livesLeft, int coinsCollected, int enemiesKilled) {
        //In order to calculate points based on time , we set 4 time zones
        //As we move up in time , points from completing the level in each time zone decrease
        //rewarding the player for making it to the end faster
        int pointsFromTime;
        if (levelCompletionTime <= 35) {
            pointsFromTime = 800;
        } else if (levelCompletionTime <= 45) {
            pointsFromTime = 600;
        } else if (levelCompletionTime <= 55) {
            pointsFromTime = 400;
        } else if (levelCompletionTime <= 65) {
            pointsFromTime = 200;
        } else {
            pointsFromTime = 100;
        }
        //We reward the player with 100 points for each heart he has left
        //We reward the player with 10 points for each coin he has collected
        //We reward the player with 50 points for each enemy he has killed
        return pointsFromTime + 100 * livesLeft + 10 * coinsCollected + 50 * enemiesKilled;


    }

    /**
     * minor method that commences boss music when player meets final boss
     */
    public void checkForFinalBoss() {
        if (gamePanel.player.getX() > 80 * GamePanel.TILE_SIZE && gamePanel.player.getX() <= 80 * GamePanel.TILE_SIZE + 5) {
            gamePanel.music.stopMusic();
            gamePanel.music.playMusic(6);
        }
    }

}
