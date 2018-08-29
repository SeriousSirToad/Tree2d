package kingery.game.engine;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import kingery.game.menu.Settings;

public class Sound implements Runnable {

	Engine e;
	AudioInputStream currentTrack;
	AudioInputStream menuMusic;
	Clip clip;
	boolean running;

	public Sound(Engine e) {

		this.e = e;

		try {
			menuMusic = AudioSystem.getAudioInputStream(new File(
					"sound/music/Island_1.wav"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		running = e.running;

	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {

		while (running) {

			if (!e.menu.canStartGame()) {

				if (currentTrack != menuMusic) {
					currentTrack = menuMusic;
					music();
				}

			}

			if (e.menu.canStartGame()) {

				if (currentTrack == menuMusic) {

					stopMusic();

				}

			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}

	}

	public void music() {

		try {
			clip = AudioSystem.getClip();

			clip.open(currentTrack);
			FloatControl gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(0 + Settings.musicVolume);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void playClip(AudioInputStream audio) {

		try {
			Clip c = AudioSystem.getClip();
			c.open(audio);
			FloatControl gainControl = (FloatControl) c
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(0 + Settings.fxVolume);
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
