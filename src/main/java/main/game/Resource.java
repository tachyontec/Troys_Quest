package main.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for all images that we load for the game
 * This way we don't need all Io imports or catching exception in all the other classes
 */
public class Resource {
    /**
     * @param gameObject : the object that we want to take images
     * @param image      : Name of file
     * @return The final image we Need
     */
    public static BufferedImage getResourceImage(Object gameObject, String image) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(gameObject.getClass().getResourceAsStream(gameObject + "_" + image + "_0.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


    /**
     * @return All pictures in the animation
     */
    public static ArrayList<BufferedImage> getAnimationimages(final Object gameObject, final String animation, final int numofimages) {

        ArrayList<BufferedImage> imageArrayList = new ArrayList();
        try {
            for (int i = 0; i < numofimages; i++) {
                imageArrayList.add(ImageIO.read(Objects.requireNonNull(gameObject.getClass().getResourceAsStream(gameObject + "_" + animation + "_" + i + ".png"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageArrayList;
    }


}