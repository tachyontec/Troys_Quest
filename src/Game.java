public class Game {

    private final int WIDTH = 900;
    private final int HEIGHT = 650;

    public Game() {
        GameWindow window = new GameWindow(WIDTH,HEIGHT,"Troy's Quest");
    }

    public static void main(String[] args) {new Game();}
}
