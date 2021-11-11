import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 640;
    private static final int HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;

    public Game() {
        GameWindow window = new GameWindow(WIDTH, HEIGHT, "Troy's Quest");
        handler = new Handler();
        //handler.addObject(new Player(100 , 100));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
    handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

}
