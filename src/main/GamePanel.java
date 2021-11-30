package main;

import objects.*;
import tiles.*;

import javax.swing.JPanel;
import java.awt.*;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable {
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
    public final int fps = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(400, 400, 3, 4, keyHandler, this);
    public ObstacleSetter obstacleSetter = new ObstacleSetter(this);
    public LinkedList<Obstacle> obstacles = new LinkedList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setUpGame() {
        obstacleSetter.setObject();
    }


    //this method starts the thread and automaticly calls method run
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread() {
        gameThread = null;
    }

    @Override
    //implementing game loop algorithm
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = 0;
        int frames = 0;
        while (gameThread != null) { //do this if the thread is active
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            timer += (now - lastTime);
            lastTime = now;
            if (delta >= 1) {
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

    //method to determine if two objects of the game have collided
    //returns a boolean statement true if collided false if not
    public boolean checkcollition() {
        boolean b = false;
        if (player.intersects(obstacles.get(0))) {
            System.out.println("collided");
            b = true;
        }
        return b;
    }

    //in this method we update all GameObject objects
    public void update() {
        /*player.tick();
        bird.tick();*/
        for (GameObject object : GameObject.l) {
            object.tick();
        }
        checkcollition();
    }

    ////in this method we paint all GameObject objects
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //TILE RENDERING
        tileM.draw(g2);
        //OBJECT RENDERING
        obstacles.get(0).draw(g2, this);
        //PLAYER RENDERING
        /*player.render(g2);
        bird.render(g2);*/
        for (GameObject object : GameObject.l) {
            object.render(g2);
        }
        if (checkcollition()) {
            g2.setColor(Color.RED);
            g2.setFont(new Font("MV Boli", Font.PLAIN, 45));
            g2.drawString("You lost ", 300, 300);
        }
        g2.dispose();
    }
}




