package tiles;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int mapTileNumber[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile=new Tile[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/mapLayout.txt");//poly shmantiko na mpei swsto path edw . /maps/file.txt
    }
    //arranges so that every cell of array tile[] contains an image
    //e.g. tile[0] erdogan,tile[1] grass etc
    public void getTileImage()  {
        try{

            tile[0] = new Tile();//when we want the backround to show at a tile , enter 0 at the maplayout
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("sandRocksTop.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("seaFull.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("seaDown.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("cloud1.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("sky.png"));


        }catch(IOException e){

            e.printStackTrace();

        }
    }

    //parses the map .txt file and arranges what block will be placed in what tile
    public void loadMap(String mapPath) {
        try {

            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol   && row < gp.maxWorldRow ) {

                String line = br.readLine();
                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = number;
                    col++;

                }
                if(col == gp.maxWorldCol) {
                    System.out.println("col2 " + col);
                    col = 0;
                    row++;
                }

            }
            br.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
        //draws shit
        public void draw(Graphics2D g2){
            int worldCol = 0;
            int worldRow = 0;

            while(worldCol < gp.maxWorldCol && worldRow <gp.maxWorldRow) {

                int tileNum = mapTileNumber[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                double screenX = worldX - gp.player.getX() + gp.player.screenX;
                double screenY = worldY - gp.player.getY() + gp.player.screenY;

                g2.drawImage(tile[tileNum].image ,(int) screenX ,(int) screenY , gp.tileSize , gp.tileSize , null);

                worldCol++;

                if(worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;

                }
            }
        }
}
