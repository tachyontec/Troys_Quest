import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindow extends Canvas implements ActionListener {

    //buttons and array for the language selection screen
    JButton greekbutton=new JButton("Ελληνικά");
    JButton englishButton=new JButton("English");
    public JButton[] LanguageButtons = {greekbutton, englishButton};

    //buttons and array of buttons for main menu
    JButton singButton=new JButton("");
    JButton multButton=new JButton("");
    JButton settButton=new JButton("");
    JButton exitButton=new JButton("");
    public JButton[] menuButtons ={singButton,multButton,settButton,exitButton};

    JFrame frame;

    public GameWindow (int width , int height , String title) {

        frame=new JFrame("Troy's Quest");
        frame.pack();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if(frame.getTitle().equals("Troy's Quest")) {
            showButtons(frame,LanguageButtons);
        }

        frame.setLayout(null);
        //frame.add(game);
        //main.start();
    }
    //configures the text of the buttons based on language
    public void configureButtons(JButton[] buttons,String language) {
        singButton.setText(language.equals("English")?"Singleplayer":"Παίξε Μόνος");
        multButton.setText(language.equals("English")?"Multiplayer":"Παίξε Με Παρέα");
        settButton.setText(language.equals("English")?"Options":"Ρυθμίσεις");
        exitButton.setText(language.equals("English")?"Exit":"Έξοδος");

    }
    //decides what is done when a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == greekbutton) {
            hideButtons(frame,LanguageButtons);
            configureButtons(menuButtons,"Greek");
            showButtons(frame,menuButtons);
        }else if (e.getSource() == englishButton) {
            hideButtons(frame,LanguageButtons);
            configureButtons(menuButtons,"English");
            showButtons(frame,menuButtons);
        }else if (e.getSource() == singButton){//if singleplayer button pressed,to sth

        }else if (e.getSource() == multButton){//if multiplayer button pressed,to sth

        }else if (e.getSource() == settButton){//if settings button pressed,to sth

        }else if (e.getSource() == exitButton){
            frame.dispose();//if exit button pressed,close frame
        }
    }
    //displays the array of buttons given to the frame given,up to five buttons
    public void showButtons(JFrame frame,JButton[] buttons) {
        for(int i=0;i<buttons.length;i++) {
            buttons[i].setBounds(350, 200 + 100*i, 200, 40);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
        }
    }
    //hides all the buttons in the array buttons from the jframe frame
    public void hideButtons(JFrame frame , JButton[] buttons){
        for(JButton button : buttons){
            button.setVisible(false);
            frame.repaint();
        }
    }


}
