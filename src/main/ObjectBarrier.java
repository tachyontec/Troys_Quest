package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ObjectBarrier extends SuperObject {

    public ObjectBarrier() {

        this.setName("Barrier");
        try {
            this.setImage(ImageIO.read(getClass().getResourceAsStream("C:\\Users\\hmdis\\IdeaProjects\\Troys_Quest\\res\\tiles\\blockEdge.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}