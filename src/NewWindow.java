/* class NewWindow
 * this class shall be used to open the game Window following language selection in the LaunchPage class
 * THE WINDOWS THIS CLASS PROVIDES WILL BE WHERE GAMEPLAY TAKES PLACE
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWindow extends JFrame implements ActionListener {

    private Canvas canvas;
    private final int WIDTH = 900;
    private final int HEIGHT = 650;

    //making all the buttons here and adding the language to them later
    // so that they are class objects that can be detected by ActionPerformed
    JButton singButton;
    JButton multButton;
    JButton settButton;
    JButton exitButton;

    public NewWindow(String language) {

        super("Troy's Quest ("+language+")");
        //configuring window placement and size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        //makes the window cisible
        this.setVisible(true);
        this.setLayout(null);
        //makes so that the program terminates when the red X is pressed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creating canvasobject to allow us to draw on the window
        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setFocusable(false);
        this.add(canvas);

        //making english menu
        if (language.equals("English")) {
            //making all of my buttons
            singButton = new JButton("Single Player");
            multButton = new JButton("Multiplayer");
            settButton = new JButton("Settings");
            exitButton = new JButton("Exit");

            //configuring singleplayer button
            singButton.setBounds(350,200,200,40);//position of buttons
            singButton.setFocusable(false);
            singButton.addActionListener(this);//you can now press the button
            this.add(singButton);//adds button on the frame
            //THE SAME IS TRUE FOR ALL FOLLOWING BUTTONS

            //configuring multiplayer button
            multButton.setBounds(350,300,200,40);
            multButton.setFocusable(false);
            multButton.addActionListener(this);
            this.add(multButton);

            //configuring Settings button
            settButton.setBounds(350,400,200,40);
            settButton.setFocusable(false);
            settButton.addActionListener(this);
            this.add(settButton);

            //configuring exit button
            exitButton.setBounds(350,500,200,40);
            exitButton.setFocusable(false);
            exitButton.addActionListener(this);
            this.add(exitButton);
        }else {
            singButton = new JButton("Παίξε Μόνος");
            multButton = new JButton("Παίξε Με Παρέα");
            settButton = new JButton("Ρυθμίσεις");
            exitButton = new JButton("Εξοδος");
            singButton.setBounds(350,200,200,40);//position of buttons
            singButton.setFocusable(false);
            singButton.addActionListener(this);//you can now press the button
            this.add(singButton);//adds button on the frame
            //THE SAME IS TRUE FOR ALL FOLLOWING BUTTONS

            //configuring multiplayer button
            multButton.setBounds(350,300,200,40);
            multButton.setFocusable(false);
            multButton.addActionListener(this);
            this.add(multButton);

            //configuring Settings button
            settButton.setBounds(350,400,200,40);
            settButton.setFocusable(false);
            settButton.addActionListener(this);
            this.add(settButton);

            //configuring exit button
            exitButton.setBounds(350,500,200,40);
            exitButton.setFocusable(false);
            exitButton.addActionListener(this);
            this.add(exitButton);
        }

    }//constructs the menu depending on the language , not much to look at gia esas
    @Override
    //This method determines what actions are to be performed when a button is pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singButton) {//if singleplayer button pressed,do something

        }else if(e.getSource() == multButton) {//if multiplayer button pressed,do something

        }else if(e.getSource() == settButton) {//if settings button pressed,do something

        }else if(e.getSource() == exitButton) {//if exit button pressed,do something
            this.dispose();
        }


    }
}
