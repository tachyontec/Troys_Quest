package main;

import tiles.*;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    public final int originalTileSize = 16; // size of tile
    public final int scale = 3; // scaling size that will scale the tiles
    public final int tileSize = originalTileSize * scale; //tile size after scaling 48
    public final int maxScreenCol = 16; // how many tiles will be horizontaly
    public final int maxScreenRow = 12; //how many tiles will be vertically
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int screenWidth = tileSize * maxScreenCol; // screen width 768
    public final int screenHeight = tileSize * maxScreenRow; // screen height 576
    public final int fps=60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(400,400,3,4,keyHandler,this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);



    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread(){
        gameThread = null;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = 0;
        int frames = 0;
        while(gameThread != null) { //do this if the thread is active
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            timer += (now - lastTime);
            lastTime = now;
            if  (delta >= 1) {
                update();
                repaint();
                delta--;
                frames++;
            }
            if (timer > 1000000000) {
                System.out.println("FPS " + frames); //prints out our fps to check if it works
                frames = 0;
                timer = 0;
            }
        }

    }



    public void update() {
        player.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.render(g2);
        g2.dispose();
    }
}
