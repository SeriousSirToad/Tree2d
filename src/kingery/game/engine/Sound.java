package kingery.game.engine;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

import kingery.game.menu.Settings;

public class Sound implements Runnable {

	AudioInputStream currentTrack;
	String menuMusic;
	String mainTheme;
	Clip clip;
	boolean running;

	public Sound() {

		menuMusic = "res/sound/music/Island_1.wav";
		mainTheme = "res/sound/music/game.wav";

	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {

		//playSound(mainTheme);

	}

	public void playSound(String FileName) {

		try {
			File soundFile = new File(FileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Settings.musicVolume);
			clip.start();
			clip.loop(-1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public void playClip(AudioInputStream audio) {

		try {
			DataLine.Info info = null;
			Clip c = (Clip) AudioSystem.getLine(info);
			c.open(audio);
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * Settings.musicVolume) + gainControl.getMinimum();
			gainControl.setValue(gain);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stopMusic() {

		clip.close();
		clip.stop();
		clip.setFramePosition(0);

	}

}
