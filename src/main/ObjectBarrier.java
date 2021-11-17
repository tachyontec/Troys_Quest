package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ObjectBarrier extends SuperObject {

    public ObjectBarrier() {

        this.setName("Barrier");
        try {
            this.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/arrow.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}