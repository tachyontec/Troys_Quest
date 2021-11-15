package tiles;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile=new Tile[10];
        getTileImage();

    }
    public void getTileImage()  {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("blockEdge.png"));

        }catch(IOException e){

            e.printStackTrace();

        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
    }
}
