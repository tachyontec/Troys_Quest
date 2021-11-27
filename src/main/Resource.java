package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Resource {

    //Get specific image
    public static BufferedImage getResourceImage(String folder, String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/" + folder + "/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    //get all Images in a specific folder
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