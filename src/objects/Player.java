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
    public BufferedImage[] right;
    public BufferedImage[] left;
    public BufferedImage[] jump;
    public BufferedImage[] idle;
    public BufferedImage[] death;
    public BufferedImage[] attack;
    public final int axisX = 400;
    public final int axisY = 400;
    private int livesLeft = 3;

    private int coinsCollected; // counts the coins that have been collected
    public int screenX;
    public int screenY;
    //used so that we do not have the death sound used recursively when the player dies and the state is dead
    public boolean deathSoundIsDone;
    private boolean collision;

    //a rectangle that dictates where the player attacks
    public Rectangle attackHitbox;
    public boolean isAttackCollision;//and a boolean value to say if you are attacking or not

    //creating for each animation needed for the player an object of Animation class
    Animation leftanimation;
    Animation rightanimation;
    Animation jumpinganimation;
    Animation idleanimation;
    Animation deathanimation;
    Animation attackanimation;

    //creating enumarition for player state
    public enum State {ALIVE, DEAD, JUMP, RIGHT, ATTACK, LEFT}

    public State state = State.RIGHT;//state stores current player state

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
        rightanimation = new Animation(0 , right);
        leftanimation = new Animation(0 , left);
        jumpinganimation = new Animation(0 , jump);
        idleanimation = new Animation(0 , idle);
        deathanimation = new Animation(0 , death);
        attackanimation = new Animation(5 , attack);

        this.collision = true;

        this.attackHitbox = new Rectangle( (int) (this.worldX + gamePanel.tileSize)//attack hitbox's y same as the player's because they need to  be on the same height
                ,(int) (this.worldY), gamePanel.tileSize / 4 , gamePanel.tileSize);//attack hitbox's coordinates x in front of the player by a tile so that the enemy hits in front of him
        this.isAttackCollision = false ;

    }

    // in this method we are loading the images for each animation from resources folder res
    //this needs to be implemented in another class named Resources later
    public void getPlayerImage() {
        right = Resource.getFilesInDir("res/Player/Walk/Right");
        left = Resource.getFilesInDir("res/Player/Walk/Left");
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
            state = State.LEFT;
            this.setX(this.getX() - this.getSpeedx());//moves the player along the x axis to the left
            attackHitbox.x = (int) (this.getX() + gamePanel.tileSize);//moves the attack hitbox to follow players' hitbox
            attackHitbox.y = (int) this.getY();//moves the attack hitbox to follow players' hitbox
            leftanimation.runAnimation();
        } else if (keyHandler.rightPressed) {
            state = State.RIGHT;
            this.setX(this.getX() + getSpeedx());//moves the player along the x axis to the right
            attackHitbox.x = (int) (this.getX() + gamePanel.tileSize);//moves the attack hitbox to follow players' hitbox
            attackHitbox.y = (int) this.getY();//moves the attack hitbox to follow players' hitbox
            rightanimation.runAnimation();
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
        if(keyHandler.attackPressed) { // when space is pressed , isAttackCollision indicates that enemies can collide with the sword's hitbox
            isAttackCollision = true;
        } else {
            isAttackCollision = false;
        }
    }

    // the direction variable indicates which images are to be drawn for each animation of the player
    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.blue);
        g.drawRect(this.attackHitbox.x , this.attackHitbox.y , this.attackHitbox.width , this.attackHitbox.height);
        //this if is in place so that when the player is hit , he is invulnerable and his body is shown blinking to indicate that state
        if (this.isCollision() || this.state == State.DEAD) {//when a player is hit collision is turned off for some seconds so this.isCollision comes out false
            switch (state) {
                case JUMP -> jumpinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case DEAD -> deathanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize + 24, gamePanel.tileSize + 24);
                case LEFT -> leftanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case RIGHT -> rightanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case ALIVE -> idleanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                case ATTACK -> attackanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize + 50, gamePanel.tileSize );
            }
        } else {
            if ((int) (this.gamePanel.handler.timer * 50) % 2 == 0) {
                switch (state) {
                    case JUMP -> jumpinganimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case DEAD -> deathanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize + 24, gamePanel.tileSize + 24);
                    case LEFT -> leftanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case RIGHT -> rightanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case ALIVE -> idleanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
                    case ATTACK -> attackanimation.drawAnimation(g, screenX, screenY, gamePanel.tileSize + 50, gamePanel.tileSize);
                }
            }
        }

        //FIX ME NIKOLAOS KATSIOS RENDERING GLITCH FROM UP PRESSED
       /* int x = screenX;
        int y = screenY;

        if (screenX > getX()) {
            x = (int) getX();
        }


        if (screenY > getY()) {
            y = (int) getY();
        }

        */

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
                    if(getY() < 432) {
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

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public void setCoinsCollected(int coinsCollected) {
        this.coinsCollected = coinsCollected;
    }

}

