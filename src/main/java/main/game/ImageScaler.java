package main.game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image scaling class
 * Each time we draw an object or tile in Gamepanel paintComponent method
 * its size needs to be adjusted to the specified width and height
 * Because this process is being done 60times/sec (FPS), to optimize our game
 * we scale each image beforehand
 * So when graphics draws the image , it is already scaled by scaleImage method
 */

public class ImageScaler {


    /**
     * Method used to scale images of tiles , objects etc.
     *
     * @param originalImage Original unscaled image
     * @param width         the preferred in-game width for the image
     * @param height        the preferred in-game height for the image
     * @return the scaled image ready to be drawn
     */
    public static BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType()); //Initiate buffered image , a blank canvas with specified width and height
        Graphics2D g2 = scaledImage.createGraphics(); //whatever g2 draws, the output is stored in the scaledImage
        g2.drawImage(originalImage, 0, 0, width, height, null); //g2 draws
        g2.dispose();

        return scaledImage;
    }

    /**
     * Method used to scale array of images of tiles , objects etc.
     *
     * @param originalImages Original unscaled image array
     * @param width          the preferred in-game width for each image in the array
     * @param height         the preferred in-game height for each image in the array
     * @return the scaled image array ready to be drawn
     */

    public static BufferedImage[] scaleImageArray(BufferedImage[] originalImages, int width, int height) {
        BufferedImage[] scaledImages = new BufferedImage[originalImages.length];
        for (int i = 0; i < originalImages.length; i++) {
            BufferedImage scaledImage = new BufferedImage(width, height, originalImages[i].getType()); //Initiate buffered image , a blank canvas with specified width and height
            Graphics2D g2 = scaledImage.createGraphics(); //whatever g2 draws, the output is stored in the scaledImage
            g2.drawImage(originalImages[i], 0, 0, width, height, null); //g2 draws
            scaledImages[i] = scaledImage;
            g2.dispose();
        }
        return scaledImages;
    }
}
