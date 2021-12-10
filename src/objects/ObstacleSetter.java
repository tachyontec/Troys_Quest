package objects;

import main.GamePanel;

import java.awt.*;
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

        Obstacle flagstart = new Obstacle(5 * gamePanel.tileSize,0,0,0,gamePanel.tileSize,gamePanel.tileSize*11,"Flag",12,gamePanel);
        gamePanel.obstacles.add(flagstart);
        flagstart.worldY = (9*gamePanel.tileSize);
        Obstacle flagend = new Obstacle(75 * gamePanel.tileSize,0,0,0,gamePanel.tileSize,gamePanel.tileSize*11,"Flag",12,gamePanel);
        gamePanel.obstacles.add(flagend);
        flagend.worldY = (9*gamePanel.tileSize);

        for(int i = 1;i <= 13;i++) { //
            int randomvariable = rand.nextInt(3);// a random number that will help to spawn player randomly
            int spawnX = startingpoint + randomvariable * gamePanel.tileSize; //the X cordinate that obstacle will spawn
            gamePanel.obstacles.add(new Obstacle(spawnX,9*gamePanel.tileSize,0,0,30,gamePanel.tileSize,"spikesRoller",6,gamePanel));
            startingpoint += 5 * gamePanel.tileSize; // starting point changes each time new obstacle will be created
        }

    }
}