import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//This is class is called whenever we press a key
//So it will EXTEND KeyAdapter
public class In extends KeyAdapter {
    //This class is referred only at a player
    //User will only be able to move its player
    Player p;
    public In(Player player) {
        this.p = player;
    }

    public void keyPressed(KeyEvent k) {
        double velocity = GameObject.getSpeed(); //The speed that we will be moving Player
        //We need to check after every button click because it is increasing through time
        int key = k.getKeyCode(); //get the code of key presses
        if (key == KeyEvent.VK_RIGHT) {
            p.setSpeedx(p.getSpeedx()+velocity);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setSpeedx(p.getSpeedx()-velocity);
        } else if (key == KeyEvent.VK_UP) {
            p.setSpeedy(p.getSpeedy()-velocity);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setSpeedy(p.getSpeedy()+velocity);
        }//we are below y-axis
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> p.setSpeedy(0);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT -> p.setSpeedx(0);
        }
    }
}
