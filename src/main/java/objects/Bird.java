
package objects;

import main.game.Animation;
import main.game.GamePanel;
import main.game.Resource;

import java.awt.*;

//A game object that is moving towards the Player
public class Bird extends MovingObject {
    //MovingObstacle needs Game Panel to spawn on it
    public enum Direction {LEFT, RIGHT}

    public Direction direction;
    //Buffered Images are the ones that contain our main character
    //they look when they move left,right and jump etc.

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP2")
    public Bird(double worldX, double worldY, int width, int height,
                String name, GamePanel gamePanel, double speedx, double speedy) {
        super(worldX, worldY, width, height, name, gamePanel, speedx, speedy);
        this.gamePanel = gamePanel;
        this.direction = Direction.LEFT;
        //While making the object we also determine its speed
    }

    @Override
    //paint Bird left animation if flys left else paints right animation
    public void render(Graphics2D g) {
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX;
        double screenY = this.getY();
        switch (direction) {
            case LEFT -> {
                leftanimation.drawAnimation(g, (int) screenX, (int) screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            }
            case RIGHT -> {
                rightanimation.drawAnimation(g, (int) screenX, (int) screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            }
        }

    }

    @Override
    public void getMovingObjectImage() {
        left = Resource.getFilesInDir("src/main/resources/objects/" + name + "/Left");
        right = Resource.getFilesInDir("src/main/resources/objects/" + name + "/Right");
        rightanimation = new Animation(0, right);
        leftanimation = new Animation(0, left);
    }

    @Override
    public void update() {
        checkcollision();
        switch (direction) {
            case LEFT -> {
                leftanimation.runAnimation();
                setX(getX() - getSpeedx());
            }
            case RIGHT -> {
                rightanimation.runAnimation();
                setX(getX() + getSpeedx());
            }
        }
    }

    /*in this method we check the collision of the bird
    with the bounds,if the collide the bird changes direction, animation*/

    public void checkcollision() {
        if (Bound.start.intersects(this)) {
            direction = Direction.RIGHT;
        } else if (Bound.end.intersects(this)) {
            direction = Direction.LEFT;
        }

    }
}
