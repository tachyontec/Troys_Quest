package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp; //we need an instance of the game panel
    Tile[] tile; // we create an array of tiles , where all the pngs which we use shall be stored

    int[][] mapTileNumber; //a 2D array that witch represents our mapLayout

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];//we initialise the array that represents our map so that its size is the same as our level
        getTileImage();//we load the images of the tiles from the /res folder
        loadMap("/maps/mapLayout.txt");//we load the map
    }

    /*
    This method initialises our tile[] array so that every cell contains a single png
    we do that because the map is constructed with a .txt file (e.g. mapLayout) which contains a bunch of integers 1-10
    we want to take that .txt file and replace all ints with a tile of our choice
    every int i in the .txt file is going to be replaced with tile[i] on our gamesmenu
    */
    public void getTileImage() {
        try {
            for (int i = 0; i < 7; i++) {
                tile[i] = new Tile();
            }//if there are no tiles placed at a certain spot , the background shows
            //we input 0 on the .txt file where we want the background to show
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sandRocksTop.png")));
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("seaFull.png")));
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("seaDown.png")));
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("cloud1.png")));
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sky.png")));
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("bush.png")));

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /*This method receives the map as a .txt file in the mapPath
     and fills up our mapTileNumber array accordingly so that the numbers in the txt file match those in the array
     */
    public void loadMap(String mapPath) {
        try {

            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            assert inputStream != null;//Make sure directory is not empty, or we will have NullPointerException
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)); // initialization of the file reader tool

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) { //loop through all the file's rows and columns one by one

                String line = br.readLine(); //read each line separately and create a String variable for each one
                while (col < gp.maxWorldCol) { // loop through all columns of the current line

                    String[] numbers = line.split(" "); // make a 1D array witch contains all the numbers of the specific line

                    int number = Integer.parseInt(numbers[col]); //detaches the element numbers[col] from the array and saves it in number variable

                    mapTileNumber[col][row] = number; // fills the specified cell of the array with the corresponding number from the file
                    col++;

                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }
            br.close(); //dispose reader for optimization purposes
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    via the loadmap method we end up with the array mapTileNumber[][] filled with the data of our level ,
    mapTileNumber[i][j] contains the int k of the .txt map file , so basically we take tile[k] and draw it on the (i,j) coordinates on the level
    that is done for every tile on the grid
    we end up with a fully drawn map
    */
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {//while we are within the boundaries of the level(12x64)

            int tileNum = mapTileNumber[worldCol][worldRow];//we give tileNum a png from the tile[] array

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            double screenX = worldX - gp.player.getX() + gp.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
            double screenY = worldY - gp.player.getY() + gp.player.screenY; //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference


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
            //Then we calculate the length between player screenY and the right edge of the frame
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
