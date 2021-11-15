/*import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 640;
    private static final int HEIGHT = WIDTH / 12 * 9; //standard 16:9 ratio

    private Thread thread; //thread initialization
    private boolean running = false; //checks the game status
    private Handler handler; //handler instance for managing objects according to its tick and render methods

    public Game() {
        handler = new Handler(); //handler init before everything , to manage objects before they are created
        this.addKeyListener(new KeyboardInput()); // to be discussed . propably needs a handler instance as a parameter , as we manage (create , remove etc)all of our objects through handler

        new GameWindow(WIDTH, HEIGHT, "Troy's Quest" , this ); //Game window init
        //handler.addObject(new main.Player(100 , 100));
    }
    //starting up our thread
    public synchronized void start() {
        thread = new Thread(this); //passing out game instance as a parameter so that the 'target' of the thread is out entire game and its components
        thread.start();
        running = true; // when the thread is active , the game is now running
    }
    //kills our thread
    private synchronized void stop() {
        try {
            thread.join();
            running = false; //when the thread is no longer active  ,the game stops running
        } catch (Exception e) {
            e.printStackTrace(); //if for some reason the thread can't stop
        }
    }

    //Our main game loop , ready-made algorithm explanation below for understanding purposes.
    //"lastTime", "now," and "ns" are used to calculate "delta." amountOfTicks is the amount of tics/second, and ns is the amount of nanoseconds/tick.
    //When delta is calculated, you have (now-lastTime)/(ns/tick), but now and lastTime  are in nanoseconds, so it has units "tick". We then add this to delta, and keep going.
    //Whenever delta+=1, one tick has passed, and we therefore call the command tick() [[[which is explained in the video]]], and reset delta to 0 in the while(delta>=1) loop.
    //the if(running) loop updates the window (by rendering again), and increases the frames with 1.
    //the if(System.currentTimeMillis()-timer>1000) loop writes out the FPS once per second by checking if the current time is more than 1000 milliseconds (1 second) larger than "timer" was.
    //IF so, we update "timer" to be 1 second later (timer+=1000;), and print the amount of frames that have passed, and set frames to 0. Since this event happens once every second, the value "frames" is the frames per second.
    //stop() stops the game.
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) { //do this if the thread is active
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
                System.out.println("FPS " + frames); //prints out our fps to check if it works
                frames = 0;
            }
        }
        stop();
    }
    //ticks all of our objects since the tick method of handler loops through all of the created GameObjects
    private void tick(){
    handler.tick();
    }

    private void render(){ //main.Main render method
        BufferStrategy bs = this.getBufferStrategy(); // BufferStrategy means how many ready made panels we buffer (load) before showing them in the screen
        if(bs == null) { //the first time that render is called , a new buffer strategy is created for our main game instance
            this.createBufferStrategy(3); // 2 buffers loading behind our main panel (3 including current one)
            return;
        }

        Graphics g = bs.getDrawGraphics(); //all graphics of our components are drawn
        handler.render(g); //renders all of our objects since the render method of handler loops through all the created GameObjects
        g.dispose(); // constantly disposing the panel (frame) that is just being shown in order to display the new one
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    } //no need for more code in main , just creates an instance of our game, and it instantly runs

}
*/