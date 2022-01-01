package objects;


import main.GamePanel;
import main.Resource;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
    GamePanel gamePanel;
    public BufferedImage[] platformImg;
    public Line2D rightLine;
    public Line2D leftLine;
    public Line2D topLine;
    public Line2D bottomLine;
    public double platformfloor;

    public Block(double worldX, double worldY, double speedX,
                 double speedY, int width, int height, GamePanel gamePanel) {
        super(worldX, worldY, speedX, speedY, width, height);
        this.gamePanel = gamePanel;
        this.platformImg = Resource.getFilesInDir("res/Platforms");
        rightLine = new Line2D.Double();
        leftLine = new Line2D.Double();
        topLine = new Line2D.Double();
        bottomLine = new Line2D.Double();
        setlineBounds(getX(), getY(), this.width, this.height);
        this.platformfloor = worldY;
    }

    public void setlineBounds(double worldX, double worldY, int width, int height) {
        rightLine.setLine(worldX + width, worldY, worldX + width, worldY + height);
        leftLine.setLine(worldX, worldY, worldX, worldY + height);
        topLine.setLine(worldX, worldY, worldX + width, worldY);
        bottomLine.setLine(worldX, worldY + height, worldX + width, worldY + height);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        double screenX = this.getX() - gamePanel.player.getX() + gamePanel.player.screenX; //centers the player in relation to the screen in x axis,gp.player.screenX is used to offset the difference
        double screenY = this.getY(); //centers the player in relation to the screen in y axis,gp.player.screenY is used to offset the difference
        g.drawImage(platformImg[0], (int) screenX, (int) screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
