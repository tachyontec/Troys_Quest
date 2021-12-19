package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
    public Animation(BufferedImage... args) {
        images = new BufferedImage[args.length];
        //The speed will depend on how many images we have
        this.speed =   60 / args.length - 10;
        //Copy args array into images:
        System.arraycopy(args, 0, images, 0, args.length);
        this.frames = args.length;
    }

    public Animation reverseAnimation(){
        BufferedImage[] reverse = new BufferedImage[this.images.length];
        AffineTransform affineTransform = new AffineTransform();
        for (int i=0;i<this.images.length;i++) {
            BufferedImage bimg = this.images[i];
            //We want the object to be looking right instead of left (or the opposite)
            /*int angle=180;
            double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                    cos = Math.abs(Math.cos(Math.toRadians(angle)));
            int w = bimg.getWidth();
            int h = bimg.getHeight();
            int neww = (int) Math.floor(w*cos + h*sin);
            int newh = (int) Math.floor(h*cos + w*sin);
            reverse[i] = new BufferedImage(neww, newh, bimg.getType());*/
            //affineTransform.translate(bimg.getWidth() / 2, bimg.getHeight() / 2);
            affineTransform.rotate(180);
            //affineTransform.translate(-bimg.getWidth() / 2, -bimg.getHeight() / 2);
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);

            reverse[i]=new BufferedImage(bimg.getHeight(), bimg.getWidth(), bimg.getType());

            affineTransformOp.filter(bimg, reverse[i]);
        }
        return new Animation(reverse);
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
