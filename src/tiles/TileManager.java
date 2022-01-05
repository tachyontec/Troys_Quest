package tiles;

import main.GamePanel;
import main.Resource;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * This class manages Background Tiles by
 * initializing them and drawing them according to the mapLayout.
 *
 */

public class TileManager {

    GamePanel gp; //we need an instance of the game panel
    Tile[] tile; // we create an array of tiles , where all the pngs which we use shall be stored

    int[][] mapTileNumber; //a 2D array that witch represents our mapLayout

    /**
     * Tile Manager Constructor
     * Sets the size of the array containing the tiles
     * thus representing how many different style tiles exist in our game
     * @param gp Our main Game Panel on witch all our components are combined and drawn
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[30];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];//we initialise the array that represents our map so that its size is the same as our level
        getTileImage();//we load the images of the tiles from the /res folder

    }

    /**
     * This method initialises our tile[] array so that every cell contains a single tile represented by a .png
     * We do that because the map is constructed based on a .txt file (e.g. mapLayout) which contains integers ranging from 1 to tile.length()
     * Instead of initializing the tiles one by one we pass all the tile names to an array, and we use a for loop to specify each tile image
    */
    public void getTileImage() {
        for (int i = 0; i < 30; i++) {
            tile[i] = new Tile();
        }//if there are no tiles placed at a certain spot , the background shows
        //we input 0 on the .txt file where we want the background to show
        String[] tilename = new String[]{"sandRocksTop.png", "seaFull.png", "seaDown.png", "cloud1.png", "sky.png", "bush.png", "CrossBoxFill.png", "crossBox.png",
                "RockGround.png", "rockBackground.png", "rockRedBG.png", "torch_bg.png", "RockCeiling.png", "gravelCeiling.png", "stalagmiteCeiling.png",
                "CasteWallBg.png", "grillFireWallBg.png", "grillWallBg.png", "plankOnWaterGround.png", "WaterfallEnd.png", "CastleWallTop.png"};
        int size = tilename.length;
        for (int i = 1; i <= size; i++) {
            tile[i].image = Resource.getResourceImage("tiles", tilename[i - 1]);
        }
    }

    /**
     *  This method receives the map as a .txt file in the mapPath
     *  and fills up our mapTileNumber array accordingly so that the numbers in array match those in the txt file
     * @param mapPath the map Layout .txt path from the res folder
     */

    public void loadMap(String mapPath) {
        try {

            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            assert inputStream != null; //Make sure directory is not empty, or a NullPointerException is thrown
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)); // initialization of the file reader tool

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) { //loop through all the file's rows and columns one by one

                String line = br.readLine(); //read each line separately and create a String variable for each one
                while (col < gp.maxWorldCol) { // loop through all columns of the current line

                    String[] numbers = line.split(" "); // make a 1D String array witch contains all the numbers of the specific line
                    // by splitting each element of the line based on spaces

                    int number = Integer.parseInt(numbers[col]); //detaches the element numbers[col] from the array and saves it in number variable

                    mapTileNumber[col][row] = number; // fills the specified cell of the array with the corresponding number from the file
                    col++; //changes column

                }
                if (col == gp.maxWorldCol) { //when last column of each line is checked
                    col = 0;
                    row++; // we finally change the row
                }

            }
            br.close(); //dispose reader for optimization purposes
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * via the loadMap() method we end up with the array mapTileNumber[][] filled with the data concerning the tiles of our level ,
     * mapTileNumber[i][j] contains the int k of the .txt map file , so we take tile[k] and draw it on the (i,j) coordinates on the level
     * This process repeats itself until every tile is drawn on the grid (i.e. gamepanel)
     * we end up with a fully drawn map
     */

    public void render(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {//while we are within the boundaries of the level(12x64)

            int tileNum = mapTileNumber[worldCol][worldRow];//we give tileNum a png from the tile[] array

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            double screenX = worldX - gp.player.getX() + gp.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
            double screenY = worldY;


            //Make camera not go out of bounds
            //Left edge of the map

            if (gp.player.screenX > gp.player.getX()) {
                screenX = worldX; //prevent screenX become larger than worldX
            }

            //Top edge of the map
            if (gp.player.screenY > gp.player.getY()) {
                screenY = worldY; //prevent screenY become larger than worldY
            }

            //Then we calculate the length between player screenX and the right edge of the frame
            int rightDiff = gp.screenWidth - gp.player.screenX;
            if (rightDiff > gp.worldWidth - gp.player.getX()) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX); //and we subtract the difference from the current tile from the edge of the screen
            }
            //Then we calculate the length between player screenY and the bottom edge of the frame
            int bottomDiff = gp.screenHeight - (gp.worldHeight - worldY);
            if (bottomDiff > gp.worldHeight - gp.player.getY()) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY); //and we subtract the difference from the current tile from the bottom edge of the screen
            }

            if (gp.player.screenX > gp.player.getX()
                    || gp.player.screenY > gp.player.getY()
                    || rightDiff > gp.worldWidth - gp.player.getX()
                    || bottomDiff > gp.worldHeight - gp.player.getY()) {
                g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);
            }


            g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null); //draws the tile in the specified screenX and screenY

            worldCol++;

            if (worldCol == gp.maxWorldCol) { // if we have reached the end of the tileRow in the map ,we change row in order to draw the next one
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
