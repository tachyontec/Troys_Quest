package objects;


import main.game.Animation;
import main.game.GamePanel;
import main.game.Resource;

import java.awt.*;
import java.awt.geom.Line2D;

public class Block extends StaticObject {
    public Line2D rightLine;
    public Line2D leftLine;
    public Line2D topLine;
    public Line2D bottomLine;
    public double platformfloor;

    public Block(double worldX, double worldY,
                 int width, int height, String name, GamePanel gamePanel) {
        super(worldX, worldY, width, height, name, gamePanel);
        rightLine = new Line2D.Double();
        leftLine = new Line2D.Double();
        topLine = new Line2D.Double();
        bottomLine = new Line2D.Double();
        setlineBounds(getX(), getY(), this.width, this.height);
        this.platformfloor = worldY;
    }

    @Override
    public void getStaticObjectImage() {
        images = Resource.getFilesInDir("src/main/resources/Platforms/" + name);
        animation = new Animation(0, images);
    }


    public void setlineBounds(double worldX, double worldY, int width, int height) {
        rightLine.setLine(worldX + width, worldY + 0.1, worldX + width, worldY + height + 0.1);
        leftLine.setLine(worldX, worldY + 0.1, worldX, worldY + height + 0.1);
        topLine.setLine(worldX + 0.1, worldY, worldX + width - 0.1, worldY);
        bottomLine.setLine(worldX, worldY + height, worldX + width, worldY + height);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
}
