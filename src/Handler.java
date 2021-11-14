/*import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    //creating a linkedlist that adds and removes Game Objects dynamically
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //loops through all Game Objects from list and ticks them
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.tick();
        }
    }

    //loops through all objects from the list and renders them
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.render(g);
        }
    }

    //adds new object to the list
    public void addObject (GameObject object){
        this.object.add(object);
    }
    //removes objects from list
    public void removeObject (GameObject object){
        this.object.remove(object);
    }
}*/
