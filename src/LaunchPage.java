import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
/* class LaunchPage
 * Provides The opening screen for our game
 * This class is used to open the starting window when the game is ran
 * when the user chooses a language the class shall open a new Window with the NewWindow class
 */
public class LaunchPage extends JFrame implements ActionListener {

    private final int WIDTH = 900;
    private final int HEIGHT = 650;

    JLabel troys_quest = new JLabel("Troy's Quest");
    JFrame frame = new JFrame("Troy's Quest");
    JButton greekButton = new JButton("Ellinika");
    JButton englishButton = new JButton("English");

    public LaunchPage() {
        //packing the frame,The pack method sizes the frame so that all its contents are at or above their preferred sizes
        frame.pack();
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);

        //position of greek button
        greekButton.setBounds(350,350,200,40);
        greekButton.setFocusable(false);
        greekButton.addActionListener(this);
        //position of eng button
        englishButton.setBounds(350,250,200,40);
        englishButton.setFocusable(false);
        englishButton.addActionListener(this);

        frame.setVisible(true);//makes the frame visible
        //adding buttons
        frame.add(greekButton);
        frame.add(englishButton);

        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==greekButton) {//if greek button pressed,open greek window
            frame.dispose();
            NewWindow myWindow = new NewWindow("Ellinika");
        }else if (e.getSource()==englishButton) {//if english button pressed open english window
            frame.dispose();
            NewWindow myWindow = new NewWindow("English");
        }
    }
}
