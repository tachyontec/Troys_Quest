package objects;

import main.GamePanel;

import java.util.Random;

public class ObstacleSetter {

    GamePanel gamePanel;

    public ObstacleSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    //floor is Y=8
    //initializes obstacles in obstacles array with their image icon and x ,y position relative to the map (worldX , worldY)
    public void setObject() {
        Random rand = new Random();
        //We divide the map witch is 16x69 tiles in areas along the x axis containing 5 tiles each like so 1._2._3._4._5._.
        //Each area has 3 tiles on witch obstacles are spawnable and 2 tiles that are void of objects.
        // !Array ,containing obstacle .png names for rand to pick randomly from , to be implemented as new obstacles get added!
        int startingpoint = 9 * gamePanel.tileSize; //starting point in map is where the player spawns

        /*for (int i = 1; i <= 13; i++) {
            int randomvariable = rand.nextInt(3);// a random number that will help to spawn player randomly
            int spawnX = startingpoint + randomvariable * gamePanel.tileSize; //the X coordinate that obstacle will spawn
            gamePanel.obstacles.add(new Obstacle(spawnX, 9 * gamePanel.tileSize, 0, 0, 30, gamePanel.tileSize, "spikesRoller", gamePanel));
            startingpoint += 5 * gamePanel.tileSize; // starting point changes each time new obstacle will be created
        }*/
        Enemy enemy1 = new Enemy(18 * gamePanel.tileSize, 8.6 * gamePanel.tileSize, 1, 0, gamePanel.tileSize , gamePanel.tileSize  , "Minotaur", gamePanel);
        Enemy enemy2 = new Enemy(40 * gamePanel.tileSize, 8.6 * gamePanel.tileSize, 1, 0, gamePanel.tileSize , gamePanel.tileSize  , "Minotaur", gamePanel);
        Enemy enemy3 = new Enemy(69 * gamePanel.tileSize, 8.6 * gamePanel.tileSize, 1, 0, gamePanel.tileSize , gamePanel.tileSize  , "Minotaur", gamePanel);
        enemy1.y = (int) enemy1.worldY + gamePanel.tileSize / 2; // so that the enemy touches the ground , because minotaur png's are not the resolution we need
        enemy2.y = (int) enemy2.worldY + gamePanel.tileSize / 2; // so that the enemy touches the ground , because minotaur png's are not the resolution we need
        enemy3.y = (int) enemy3.worldY + gamePanel.tileSize / 2; // so that the enemy touches the ground , because minotaur png's are not the resolution we need
        gamePanel.enemies.add(enemy1);
        gamePanel.enemies.add(enemy2);
        gamePanel.enemies.add(enemy3);
    }

}