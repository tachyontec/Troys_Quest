package sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {


        Clip clip; //used to open sound file
        URL musicURL[] = new URL[10];     //we store the sound file path

        public Sound() {

            musicURL[0] = getClass().getResource("/Sound/looperman-l-4155306-0255701-young-thug-gunna-wheezy-harp-greek-part-1.wav");
            musicURL[1] = getClass().getResource("/Sound/BOOM.wav");
            musicURL[2] = getClass().getResource("/Sound/HIT.wav");
            musicURL[3] = getClass().getResource("/Sound/JUMP.wav");
            musicURL[4] = getClass().getResource("/Sound/Coin.wav");
        }

        public void setFile(int i) {

            try {

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicURL[i]);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void play() {

            clip.start();
        }

        public void loop() {

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        public void stop() {

            clip.stop();
        }

}
