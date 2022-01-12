package objects;

import main.game.Animation;
import main.game.GamePanel;
import main.game.KeyHandler;
import main.game.Resource;
import sounds.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends MovingObject {
    public double floor; //floor of every platform
    public double platfloor;
    public boolean jumped = true;
    public float jumpingTime = 100;
    //player gets keyhandler to implement keyboard input
    public KeyHandler keyHandler;
    //player needs Game Panel to spawn on it
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.
    //the three bufferedImage tables run,jump,idle contain the photos that are needed in animations
    public Sound soundEffect = new Sound();
    public BufferedImage[] jump;
    public BufferedImage[] idle;
    public BufferedImage[] death;
    public BufferedImage[] attack;
    private int livesLeft = 3; //at the start of each level , our player has 3 lives by default
    private int coinsCollected; // counts the coins that have been collected
    private int enemiesKilled;
    public int screenX;
    public int screenY;
    //used so that we do not have the death sound used recursively when the player dies and the state is dead
    public boolean deathSoundIsDone;
    private boolean collision;

    public int counter = 0;
    public int friction = 0;

    //a rectangle that dictates where the player attacks
    public Rectangle attackHitbox;
    public boolean isAttackCollision;//and a boolean value to say if you are attacking or not

    //creating for each animation needed for the player an object of Animation class
    Animation jumpinganimation;
    Animation idleanimation;
    Animation deathanimation;
    Animation attackanimation;

    //creating enumeration for player state
    public enum State {ALIVE, DEAD, JUMP, RIGHT, ATTACK, LEFT}

    public State state = State.RIGHT;//state stores current player state

    public double deathTime = 0;//the time the player dies

    public static final double GRAVITY = 0.4;


    //Constructor using fields and initializing the animations objects
    public Player(double worldX, double worldY, int width, int height, String name,
                  GamePanel gamePanel, double speedx, double speedy, KeyHandler keyHandler) {

        super(worldX, worldY, width, height, name, gamePanel, speedx, speedy);
        this.keyHandler = keyHandler;

        screenX = GamePanel.TILE_SIZE * 7;
        screenY = GamePanel.TILE_SIZE * 9;


        floor = 9 * GamePanel.TILE_SIZE;//sets the floor on which player is for every platform he stands on
        rightanimation = new Animation(0, right);
        leftanimation = new Animation(0, left);
        jumpinganimation = new Animation(0, jump);
        idleanimation = new Animation(0, idle);
        deathanimation = new Animation(0, death);
        attackanimation = new Animation(5, attack);

        this.collision = true;
        //attack hitbox's y same as the player's because they need to  be on the same height
        //attack hitbox's coordinates x in front of the player by a tile so that the enemy hits in front of him
        this.attackHitbox = new Rectangle((int) (this.worldX + GamePanel.TILE_SIZE)
                , (int) (this.worldY), GamePanel.TILE_SIZE / 4, GamePanel.TILE_SIZE);
        this.isAttackCollision = false;

    }

    // in this method we are loading the images for each animation from resources folder res
    //this needs to be implemented in another class named Resources later
    @Override
    public void getMovingObjectImage() {
        right = Resource.getFilesInDir("src/main/resources/Player/Walk/Right");
        left = Resource.getFilesInDir("src/main/resources/Player/Walk/Left");
        jump = Resource.getFilesInDir("src/main/resources/Player/Jump");
        idle = Resource.getFilesInDir("src/main/resources/Player/Idle");
        death = Resource.getFilesInDir("src/main/resources/Player/Die");
        attack = Resource.getFilesInDir("src/main/resources/Player/Attack");
    }

    //moves the player by altering the x,y coordinates with keyboard arrows
    @Override
    public void update() {
        still();
        gravity();
        jump();
        run();
        death();
        // when space is pressed , isAttackCollision indicates that enemies can collide with the sword's hitbox
        isAttackCollision = keyHandler.attackPressed;
    }

    // the direction variable indicates which images are to be drawn for each animation of the player
    @Override
    public void render(Graphics2D g) {
        //this if is in place so that when the player is hit ,
        // he is invulnerable and his body is shown blinking to indicate that state
        if (this.isCollidable() || this.state == State.DEAD) {//when a player is hit collision is turned off for some seconds so this.isCollision comes out false
            switch (state) {
                case JUMP -> jumpinganimation.drawAnimation(g, screenX, screenY,
                        GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                case DEAD -> deathanimation.drawAnimation(g, screenX, screenY,
                        GamePanel.TILE_SIZE + 24, GamePanel.TILE_SIZE + 24);
                case LEFT -> leftanimation.drawAnimation(g, screenX, screenY,
                        GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                case RIGHT -> rightanimation.drawAnimation(g, screenX, screenY
                        , GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                case ALIVE -> idleanimation.drawAnimation(g, screenX, screenY
                        , GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                case ATTACK -> attackanimation.drawAnimation(g, screenX, screenY,
                        GamePanel.TILE_SIZE + 50, GamePanel.TILE_SIZE);
            }
        } else {
            if ((int) (this.gamePanel.currentLevel.handler.timer * 50) % 2 == 0) {
                switch (state) {
                    case JUMP -> jumpinganimation.drawAnimation(g, screenX, screenY,
                            GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                    case DEAD -> deathanimation.drawAnimation(g, screenX, screenY,
                            GamePanel.TILE_SIZE + 24, GamePanel.TILE_SIZE + 24);
                    case LEFT -> leftanimation.drawAnimation(g, screenX, screenY,
                            GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                    case RIGHT -> rightanimation.drawAnimation(g, screenX, screenY,
                            GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                    case ALIVE -> idleanimation.drawAnimation(g, screenX, screenY,
                            GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                    case ATTACK -> attackanimation.drawAnimation(g, screenX, screenY,
                            GamePanel.TILE_SIZE + 50, GamePanel.TILE_SIZE);
                }
            }
        }
        //Only when player reaches the right edge of the screen , his screenX and screenY need to be adjusted
        int rightDiff = GamePanel.SCREEN_WIDTH - screenX;
        if (rightDiff > GamePanel.WORLD_WIDTH - getX()) {
            screenX =
                    GamePanel.SCREEN_WIDTH - (GamePanel.WORLD_WIDTH - (int) getX()); //and we subtract the difference from the current tile from the edge of the screen
        }
    }

    public void gravity() {
        this.setY(this.getY() - getSpeedy());
        attackHitbox.y = (int) (this.getY() - getSpeedy());//moves the attack hitbox to follow players' hitbox
        screenY -= getSpeedy();
        this.setSpeedy(this.getSpeedy() - GRAVITY);
        //collision with floors
        if (this.getY() > (floor - 1)) {
            this.setSpeedx(3);
            counter = 0;
            jumped = false;
            this.setY(floor - 1);
            attackHitbox.y = (int) (floor - 1);
            screenY = (int) floor;
            this.setSpeedy(0);
        }
    }

    public void still() {
        if (((!keyHandler.rightPressed) && (!keyHandler.leftPressed) &&
                (!keyHandler.upPressed) && (!keyHandler.attackPressed))) {
            idleanimation.runAnimation();
            this.state = State.ALIVE;
        }
        if (keyHandler.attackPressed) {
            state = State.ATTACK;
            attackanimation.runAnimation();
            //soundEffect.playSE(8); //FIX ME PLAYS REPETITIVELY
        }
    }

    public void jump() {
        if (keyHandler.upPressed && !jumped) {
            this.jumped = true;
            state = State.JUMP;
            this.setSpeedy(12);
            jumpinganimation.runAnimation();
            soundEffect.playSE(3);
        }
    }

    public void run() {
        if (keyHandler.leftPressed) {
            setSpeedx(3);
            state = State.LEFT;
            this.setX(this.getX() - this.getSpeedx());//moves the player along the x axis to the left
            attackHitbox.x =
                    (int) (this.getX() + GamePanel.TILE_SIZE);//moves the attack hitbox to follow players' hitbox
            leftanimation.runAnimation();
        }
        if (keyHandler.rightPressed) {
            setSpeedx(3);
            state = State.RIGHT;
            this.setX(this.getX() + getSpeedx());//moves the player along the x axis to the right
            attackHitbox.x =
                    (int) (this.getX() + GamePanel.TILE_SIZE);//moves the attack hitbox to follow players' hitbox
            rightanimation.runAnimation();
        }
        if (keyHandler.rightReleased) {
            friction++;
            this.setSpeedx(getSpeedx() * 0.85);
            this.setX(this.getX() + getSpeedx());
            attackHitbox.x =
                    (int) (this.getX() + GamePanel.TILE_SIZE);//moves the attack hitbox to follow players' hitbox
            rightanimation.runAnimation();
            if (friction == 5) {
                friction = 0;
                setSpeedx(0);
                keyHandler.rightReleased = false;
            }
        }
        if (keyHandler.leftReleased) {
            friction++;
            this.setSpeedx(getSpeedx() * 0.85);
            this.setX(this.getX() - getSpeedx());
            attackHitbox.x =
                    (int) (this.getX() + GamePanel.TILE_SIZE);//moves the attack hitbox to follow players' hitbox
            leftanimation.runAnimation();
            if (friction == 5) {
                friction = 0;
                setSpeedx(0);
                keyHandler.leftReleased = false;
            }
        }


    }

    public void death() {
        if (this.getLivesLeft() == 0) {
            this.state = State.DEAD;
            deathanimation.runAnimation();
            if (!deathSoundIsDone) {
                soundEffect.playSE(1);
                deathSoundIsDone = true;// so that we stop the death sound
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

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public boolean isCollidable() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}

