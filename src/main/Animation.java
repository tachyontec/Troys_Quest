package main;

import java.awt.*;
import java.awt.image.BufferedImage;

//this class draws the animation in the screen
public class Animation {
    public int frames; // how many photos we have to change for the animation to be done
    public int speed; //the speed that we change the photos
    public int index = 0;
    public int count = 0;
    public BufferedImage[] images; //the images that are needed for the animation
    public BufferedImage currentImg;//used to refer to the current image while drawing the animation

    //constructor that gets speed and a list,or 1 or a table of BufferedImage
    //its initializes the images table
    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        //Copy args array into images:
        System.arraycopy(args, 0, images, 0, args.length);
        this.frames = args.length;
    }

    //calling this method makes the animation run in backend
    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    //this method changes the frame
    public void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i) {
                currentImg = images[i];
            }
        }
        count++;
        if (count > frames) {
            count = 0;
        }
    }

    public void drawAnimation(Graphics2D g, int x, int y, int scalex, int scaley) {
        g.drawImage(currentImg, x, y, scalex, scaley, null);
    }

}
