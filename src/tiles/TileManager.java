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
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
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

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();
                System.out.println("col " + col);
                System.out.println("row " + row);
                while(col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = number;
                    col++;

                }
                if(col == gp.maxScreenCol) {
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
            int col = 0;
            int row = 0;
            int x = 0;
            int y = 0;

            while(col< gp.maxScreenCol && row <gp.maxScreenRow) {

                int tileNum = mapTileNumber[col][row];

                g2.drawImage(tile[tileNum].image , x ,y , gp.tileSize , gp.tileSize , null);
                col++;
                x += gp.tileSize;
                if(col == gp.maxScreenCol) {
                    col = 0;
                    x = 0;
                    row++;
                    y+=gp.tileSize;
                }
            }
        }
}
