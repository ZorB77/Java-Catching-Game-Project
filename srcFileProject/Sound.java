import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private Clip caught, music,gameover;

    public Sound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\music.wav"));
            music = AudioSystem.getClip();
            music.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        if (music != null) {
            music.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopMusic() {
        music.stop();
    }



    public void playCaughtSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\targy.wav"));
            caught = AudioSystem.getClip();
            caught.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        caught.start();
    }

    public void playGameOverSound(){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\gameover.wav"));
            gameover = AudioSystem.getClip();
            gameover.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        gameover.start();

    }
}