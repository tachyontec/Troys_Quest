package objects;

import main.Animation;
import main.GamePanel;
import main.KeyHandler;
import main.Resource;
import sounds.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject {
    public double floor; //floor of every platform
    public boolean jumped = true;
    public float jumpingTime = 100;
    //player gets keyhandler to implement keyboard input
    public KeyHandler keyHandler;
    //player needs Game Panel to spawn on it
    public GamePanel gamePanel;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public Sound soundEffect = new Sound();
    public BufferedImage[] run;
    public BufferedImage[] jump;
    public BufferedImage[] idle;
    public BufferedImage[] death;
    public BufferedImage[] attack;
    public final int axisX = 400;
    public final int axisY = 400;
    private int livesLeft = 3;
    public int screenX;
    public int screenY;
    //used so that we do not have the death sound used recursively when the player dies and the state is dead
    public boolean deathSoundIsDone;
    private boolean collision;

    //creating for each animation needed for the player an object of Animation class
    Animation walkinganimation;
    Animation jumpinganimation;
    Animation idleanimation;
    Animation deathanimation;
    Animation attackanimation;

    //creating enumarition for player state
    public enum State {ALIVE, DEAD, JUMP, RUN, ATTACK}

    public State state = State.RUN;//state stores current player state

    public double deathTime = 0;//the time the player dies

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    //Constructor using fields and initializing the animations objects
    public Player(double worldX, double worldY, double speedx, double speedy, KeyHandler keyHandler, GamePanel gamePanel) {
        super(worldX, worldY, speedx, speedy, 30, gamePanel.tileSize);
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
        screenX = gamePanel.tileSize * 7;
        screenY = gamePanel.tileSize * 9;
        getPlayerImage();
        floor = this.getY();//sets the floor on which player is for every platform he stands on
        walkinganimation = new Animation(run);
        jumpinganimation = new Animation(jump);
        idleanimation = new Animation(idle);
        deathanimation = new Animation(death);
        attackanimation = new Animation(attack);
        this.collision = true;
    }

    // in this method we are loading the images for each animation from resources folder res
    //this needs to be implemented in another class named Resources later
    public void getPlayerImage() {
        run = Resource.getFilesInDir("res/Player/Walk");
        jump = Resource.getFilesInDir("res/Player/Jump");
        idle = Resource.getFilesInDir("res/Player/Idle");
        death = Resource.getFilesInDir("res/Player/Die");
        attack = Resource.getFilesInDir("res/Player/Attack");
    }

    //moves the player by altering the x,y coordinates with keyboard arrows
    @Override
    public synchronized void tick() { //synchronized because we involve another thread for jumping and we don't want it to collide with our main game thread
        if ((!keyHandler.rightPressed) && (!keyHandler.leftPressed) && (!keyHandler.upPressed) && (!keyHandler.attackPressed)) {
            idleanimation.runAnimation();
            state = State.ALIVE;
        }

        if (keyHandler.attackPressed) {
            state = State.ATTACK;
            attackanimation.runAnimation();
        }

        if (keyHandler.upPressed) {

            Thread t = new Thread(new thread());

            if (jumped) { //if statement to ensure that jumped is invoked once every time
                state = State.JUMP;
                if(getY()>300) {
                    screenY -= 15;
                    this.setY(this.getY() - 15);//moves the player upwards along the y axis
                }
                jumpinganimation.runAnimation();
                soundEffect.playSE(3);
                t.start();//initiating a new thread to perform the jump act
            }

            if((!t.isAlive())&&(floor!=getY())){
                setY(floor);
            }

            if((!jumped)&&(floor==getY())){
                idleanimation.runAnimation();
                state = State.ALIVE;
            }


        }

        if (keyHandler.leftPressed) {
            state = State.RUN;
            this.setX(this.getX() - this.getSpeedx());//moves the player along the x axis to the left
            walkinganimation.runAnimation();
        } else if (keyHandler.rightPressed) {
            state = State.RUN;
            this.setX(this.getX() + getSpeedx());//moves the player along the x axis to the right
            walkinganimation.runAnimation();
        }

        if ((!keyHandler.upPressed) && (floor == getY())) {
            jumped = true;
        }

        if (this.getLivesLeft() == 0) {
            this.state = State.DEAD;
            deathanimation.runAnimation();
            if (!deathSoundIsDone) {
                soundEffect.playSE(1);
                deathSoundIsDone = true;// so that we stop the death sound
            }
        }
    }

    // the direction variable indicates which images are to be drawn for each animation of the player
    @Override
    public void render(Graphics2D g) {
        super.render(g);
        //this if is in place so that when the player is hit , he is invulnerable and his body is shown blinking to indicate that state
        if (this.isCollision() || this.state == State.DEAD) {//when a player is hit collision is turned off for some seconds so this.isCollision comes out false
            switch (state) {
                case JUMP -> jumpinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case DEAD -> deathanimation.drawAnimation(g, screenX, screenY, 72, 72);
                case RUN -> walkinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case ALIVE -> idleanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case ATTACK -> attackanimation.drawAnimation(g, screenX, screenY, 68, 68);
            }
        } else {
            if ((int) (this.gamePanel.handler.collisionTimer * 50) % 2 == 0) {
                switch (state) {
                    case JUMP -> jumpinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case DEAD -> deathanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case RUN -> walkinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case ALIVE -> idleanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case ATTACK -> attackanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                }
            }
        }

        int x = screenX;
        int y = screenY;

        if (screenX > getX()) {
            x = (int) getX();
        }

        if (screenY > getY()) {
            y = (int) getY();
        }

        int rightDiff = gamePanel.screenWidth - screenX;
        if (rightDiff > gamePanel.worldWidth - getX()) {
            screenX = gamePanel.screenWidth - (gamePanel.worldWidth - (int) getX()); //and we subtract the difference from the current tile from the edge of the screen
        }
        //Then we calculate the length between player screenY and the right edge of the frame
        int bottomDiff = gamePanel.screenHeight - (gamePanel.worldHeight - (int) getY());
        if (bottomDiff > gamePanel.worldHeight - getY()) {
            screenY = gamePanel.screenHeight - (gamePanel.worldHeight - (int) getY()); //and we subtract the difference from the current tile from the bottom edge of the screen
        }
    }

    public class thread implements Runnable {
        @Override
        public void run() {
                try {
                    Thread.sleep((long) jumpingTime);
                    stalldx();//performing movement along the x axis gradually
                    if(getY()<432) {
                        screenY += 15;
                        setY(getY() + 15);
                    }
                    jumped = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }


        public void stalldx() {
            try {
                if (keyHandler.leftPressed) {
                    for (int i = 0; i < 15; i++)
                        setX(getX() - 1);//moves the player along the x axis to the left gradually
                    Thread.sleep((long) (jumpingTime + 50)); //smooths the jump act
                } else if (keyHandler.rightPressed) {
                    for (int i = 0; i < 15; i++)
                        setX(getX() + 1);//moves the player along the x axis to the right gradually
                    Thread.sleep((long) (jumpingTime + 50)); //smooths the jump act
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
}
