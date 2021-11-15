package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    public int frames;
    public int speed;
    public int index =0;
    public int count =0;
    public BufferedImage[] images;
    public BufferedImage currentImg;

    public Animation(int speed,BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for(int i=0;i< args.length;i++) {
            images[i] = args [i];
        }
        this.frames = args.length;
    }

    public void runAnimation() {
        index++;
        if(index > speed){
            index=0;
            nextFrame();
        }
    }

    public void nextFrame() {
        for(int i=0;i<frames;i++) {
            if(count ==i) {
                currentImg = images[i];
            }
        }
        count++;
        if(count>frames) {
            count = 0;
        }
    }

    public void drawAnimation(Graphics2D g ,int x,int y ,int scalex ,int scaley){
        g.drawImage(currentImg,x,y,scalex,scaley,null);
    }

}
