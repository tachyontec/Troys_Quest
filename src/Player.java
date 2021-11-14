import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


//Subclass of Game Object responsible for the moving and drawing the character of the game
public class Player extends GameObject {

    public KeyboardInput keyboardInput;
    public GamePanel gamePanel;

    public final int axisX;
    public final int axisY;

    public Player(double x, double y, double speedx, double speedy, KeyboardInput keyboardInput, GamePanel gamePanel) {
        super(x, y, speedx, speedy);
        this.keyboardInput = keyboardInput;
        this.gamePanel = gamePanel;
    }

    public Player(KeyboardInput keyboardInput, GamePanel gamePanel) {
        this.keyboardInput = keyboardInput;
        this.gamePanel = gamePanel;

        axisX = gamePanel.screenWidth/2 -(gamePanel.tileSize/2); //To center player on the screen because we're gonna scroll the background as player moves
        axisY = gamePanel.screenHeight/2-(gamePanel.tileSize/2);

        super.area = new Rectangle(0, 0, gamePanel.tileSize-(int)(gamePanel.tileSize*0.3), gamePanel.tileSize-(int)(gamePanel.tileSize*0.3));
        this.setDefaultValues();
        this.getPlayerImage();

    }

    public void setDefaultValues() {

        this.setX(gamePanel.tileSize * 23); //Players position on world map
        this.setY(gamePanel.tileSize * 21);
        this.setSpeedx(4);
        this.setSpeedy(4);
        direction ="down";

    }


    public void tick() {
        if (keyboardInput.upPressed == true) {
            this.setY(this.getY() - this.getSpeedy());
        } else if (keyboardInput.leftPressed == true) {
            this.setX(this.getX() - this.getSpeedx());
        } else if (keyboardInput.rightPressed == true) {
            this.setX(this.getX() + getSpeedx());
        }
    }


    public void render(Graphics2D g) {
        g.fillRect((int) this.getX(),(int) this.getY(),32,32);
        g.setColor(Color.WHITE);
    }
}