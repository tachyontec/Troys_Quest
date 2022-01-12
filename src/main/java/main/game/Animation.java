package main.game;

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

    //constructor that gets a list or a table of BufferedImage
    //its initializes the images table
    public Animation(int speed, BufferedImage... args) {
        images = new BufferedImage[args.length];
        //The speed will depend on how many images we have
        this.speed = 60 / args.length - speed; // bigger speed -> slower animation and vice versa
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

    public void drawAnimation(Graphics2D g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }
}
