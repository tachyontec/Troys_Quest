package sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

//class that provides us with sounds that are located in res/Sound
public class Sound {


    Clip clip; //used to open sound file
    URL[] musicURL = new URL[10];     //we store the sound file path

    public Sound() {
        //in each url of the array we store each sound, we will have to remember the index of each sound, it does not bother us for this many sounds
        musicURL[0] = getClass().getResource("/Sound/looperman-l-4155306-0255701-young-thug-gunna-wheezy-harp-greek-part-1.wav");
        musicURL[1] = getClass().getResource("/Sound/BOOM.wav");
        musicURL[2] = getClass().getResource("/Sound/HIT.wav");
        musicURL[3] = getClass().getResource("/Sound/JUMP.wav");
        musicURL[4] = getClass().getResource("/Sound/coinSound.wav");
        musicURL[5] = getClass().getResource("/Sound/bensound-anewbeginning.wav");
        musicURL[6] = getClass().getResource("/Sound/FINAL BOSS MUSIC.wav");
        musicURL[7] = getClass().getResource("/Sound/LEVEL COMPLETED.wav");
        musicURL[8] = getClass().getResource("/Sound/SWORD HIT.wav");
        musicURL[9] = getClass().getResource("/Sound/YOU LOSE.wav");
    }

    //setFile is used to signal which sound is going to be played, that's why we have an index as an argument
    public void setFile(int i) {

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    // in order to play the background music we need to select the file, start the clip, and loop it continuously
    public void playMusic(int i) {
        this.setFile(i);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playMusic(int i , boolean loop) {
        this.setFile(i);
        clip.start();
        if(loop){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }


    //we shut it up
    public void stopMusic() {

        clip.stop();
    }

    //the only difference with playMusic is that we do not loop it
    public void playSE(int i) {
        this.setFile(i);
        clip.start();
    }


}


