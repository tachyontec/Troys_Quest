package Tiles;

import main.game.GamePanel;
import main.game.ImageScaler;
import main.game.Resource;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class manages Background Tiles by
 * initializing them and drawing them according to the mapLayout.
 */

public class TileManager {

    /**
     * gp: we need an instance of the game panel.
     */
    GamePanel gp;
    /**
     *  tile: we create an array of tiles for all pngs.
     */
    Tile[] tile;
    /**
     * mapTileNumber: a 2D array that witch represents our mapLayout.
     */
    int[][] mapTileNumber;
    public int worldx;
    public int worldy;
    /**
     * ntiles number of tiles we have.
     */
    private final int ntiles = 30;

    /**
     * Tile Manager Constructor.
     * Sets the size of the array containing the tiles
     * thus representing how many different style tiles exist in our game
     *
     * @param gp main Game Panel on witch all components are combined and drawn
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[ntiles];
        //we initialise the array that represents our map
        // so that its size is the same as our level
        mapTileNumber = new
                int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];
        getTileImage(); //we load the images of the tiles from the /res folder

    }

    /**
     * This method initialises our tile[] array so that every cell contains
     * a single  tile represented by a .png
     * We do that because the map is constructed based on a .txt file
     * (e.g. mapLayout) which contains integers ranging from 1 to tile.length()
     * Instead of initializing the tiles one by one we pass all the tile names
     * to an array, and we use a for loop to specify each tile image
     */
    public void getTileImage() {
        for (int i = 0; i < ntiles; i++) {
            tile[i] = new Tile();
        } //if there are no tiles placed at a certain spot, the background shows
        //we input 0 on the .txt file where we want the background to show
        String[] tilename = new String[]{"sandRocksTop.png", "seaFull.png",
                "seaDown.png", "cloud1.png", "sky.png", "bush.png",
                "CrossBoxFill.png", "crossBox.png", "RockGround.png",
                "rockBackground.png", "rockRedBG.png", "torch_bg.png",
                "RockCeiling.png", "gravelCeiling.png", "stalagmiteCeiling.png",
                "CasteWallBg.png", "grillFireWallBg.png", "grillWallBg.png",
                "plankOnWaterGround.png",
                "WaterfallEnd.png", "CastleWallTop.png"};
        for (int i = 1; i <= tilename.length; i++) { //for every tile image

            tile[i].image = Resource.getResourceImage("tiles", tilename[i - 1]); //read the image
            tile[i].image = ImageScaler.scaleImage(tile[i].image, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE); //and then scale the image

        }
    }

    /**
     * This method receives the map as a .txt file in the mapPath
     * and fills up our mapTileNumber array accordingly so that the numbers in array match those in the txt file
     *
     * @param mapPath the map Layout .txt path from the res folder
     */

    public void loadMap(String mapPath) {
        try {

            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            assert inputStream != null; //Make sure directory is not empty, or a NullPointerException is thrown
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)); // initialization of the file reader tool

            int col = 0;
            int row = 0;

            while (col < GamePanel.MAX_WORLD_COL && row < GamePanel.MAX_WORLD_ROW) {
                //loop through all the file's rows and columns one by one

                //read each line separately and create a String variable for each one
                String line = br.readLine();
                while (col < GamePanel.MAX_WORLD_COL) { // loop through all columns of the current line

                    String[] numbers = line.split(" "); // make a 1D String array witch contains all the numbers of the specific line
                    // by splitting each element of the line based on spaces

                    int number = Integer.parseInt(numbers[col]); //detaches the element numbers[col] from the array and saves it in number variable

                    mapTileNumber[col][row] = number; // fills the specified cell of the array with the corresponding number from the file
                    col++; //changes column

                }
                if (col == GamePanel.MAX_WORLD_COL) { //when last column of each line is checked
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

        while (worldCol < GamePanel.MAX_WORLD_COL && worldRow < GamePanel.MAX_WORLD_ROW) {//while we are within the boundaries of the level(12x64)

            int tileNum = mapTileNumber[worldCol][worldRow];//we give tileNum a png from the tile[] array

            int worldX = worldCol * GamePanel.TILE_SIZE;
            int worldY = worldRow * GamePanel.TILE_SIZE;
            worldx = worldX;
            worldy = worldY;
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
            int rightDiff = GamePanel.SCREEN_WIDTH - gp.player.screenX;
            if (rightDiff > GamePanel.WORLD_WIDTH - gp.player.getX()) {
                screenX = GamePanel.SCREEN_WIDTH - (GamePanel.WORLD_WIDTH - worldX); //and we subtract the difference from the current tile from the edge of the screen
            }
            //Then we calculate the length between player screenY and the bottom edge of the frame
            int bottomDiff = GamePanel.SCREEN_HEIGHT - (GamePanel.WORLD_HEIGHT - worldY);
            if (bottomDiff > GamePanel.WORLD_HEIGHT - gp.player.getY()) {
                screenY = GamePanel.SCREEN_HEIGHT - (GamePanel.WORLD_HEIGHT - worldY); //and we subtract the difference from the current tile from the bottom edge of the screen
            }

            if (gp.player.screenX > gp.player.getX()
                    || gp.player.screenY > gp.player.getY()
                    || rightDiff > GamePanel.WORLD_WIDTH - gp.player.getX()
                    || bottomDiff > GamePanel.WORLD_HEIGHT - gp.player.getY()) {
                g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }

            //Optimization : we only draw the tiles located around the player and the left and right edge of the frame
            if (worldX + GamePanel.TILE_SIZE > gp.player.getX() - gp.player.screenX && //distance between player and left edge
                    worldX - GamePanel.TILE_SIZE < gp.player.getX() + gp.player.screenX + 2 * GamePanel.TILE_SIZE) { //distance between player and right edge
                g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, null); //draws the tile in the specified screenX and screenY
            }

            worldCol++;

            if (worldCol == GamePanel.MAX_WORLD_COL) { // if we have reached the end of the tileRow in the map ,we change row in order to draw the next one
                worldCol = 0;
                worldRow++;

            }
        }
    }

}
