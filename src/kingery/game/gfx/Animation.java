package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {

	public BufferedImage[] frames;
	public BufferedImage currentFrame;
	public byte frameSkip;
	private byte frameIndex;

	public Animation(short framecount, byte frameSkip, BufferedImage[] images) {

		frames = new BufferedImage[framecount];
		this.frameSkip = frameSkip;

		frames = images;

	}

	byte b = 0;

	public BufferedImage animate() {

		return frames[frameIndex];

	}

	public void update() {
		b++;

		if (b >= frameSkip) {

			frameIndex++;
			b = 0;

		}

		if (frameIndex >= frames.length) {
			frameIndex = 0;
		}
	}

	public void reset() {

		b = 0;
		frameIndex = 0;

	}

}
