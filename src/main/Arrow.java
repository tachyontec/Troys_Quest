package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Arrow extends GameObject{
    private static BufferedImage image;//image of the arrow

    public static Image getImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(Arrow.class.getResourceAsStream("/objects/arrow.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
