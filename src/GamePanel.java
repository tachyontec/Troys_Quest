import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16; // size of tile
    final int scale = 3; // scaling size that will scale the tiles
    final int tileSize = originalTileSize * scale; //tile size after scaling 48
    final int maxScreenCol = 16; // how many tiles will be horizontaly
    final int maxScreenRow = 12; //how many tiles will be vertically
    final int screenWidth = tileSize * maxScreenCol; // screen width 768
    final int screenHeight = tileSize * maxScreenRow; // screen height 576


    KeyboardInput keyboardInput = new KeyboardInput();
    public Player player = new Player(100,100,3,4,keyboardInput,this);

    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardInput);


    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread(){
        gameThread = null;
    }

    @Override
    public void run(){
        int fps = 100;
        long msPerFrame = 1000 *100000 / fps;
        long lastTime =0;
        long elapsed;

        int msSleep;
        int nanoSleep;


        while (gameThread != null) {

            player.tick();
            repaint();

            elapsed = (lastTime + msPerFrame - System.nanoTime());
            msSleep = (int) (elapsed / 1000000);
            nanoSleep = (int) (elapsed % 11000000);

            if(msSleep <= 0){
                lastTime = System.nanoTime();
                continue;
            }
            try{
                Thread.sleep(msSleep, nanoSleep);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            lastTime = System.nanoTime();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.render(g2);
        g2.dispose();
    }
}

