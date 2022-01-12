package main.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is used for all images that we load for the game
 * This way we don't need all Io imports or catching exception in all the other classes
 */
public class Resource {
    /**
     * @param folder : Name of folder in "res/" where image is located
     * @param name   : Name of file
     * @return The final image we Need
     */
    public static BufferedImage getResourceImage(String folder, String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/" + folder + "/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * @param dir : Full directory of the folder which contains the photos we want
     *            Directory starting from "res/" folder
     * @return All pictures in the requested folder
     */
    public static BufferedImage[] getFilesInDir(String dir) {
        File path1 = new File(dir);
        File[] all = path1.listFiles();
        assert all != null;//Make sure directory is not empty, or we will have NullPointerException
        BufferedImage[] images = new BufferedImage[(all.length)];
        for (int i = 0; i < all.length; i++) {
            try {
                images[i] = ImageIO.read(all[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }
}