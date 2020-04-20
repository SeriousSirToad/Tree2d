package kingery.game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	public BufferedImage[] frames;
	public BufferedImage currentFrame;
	public int frameSkip;
	private int frameIndex;
	boolean skipFirst = false;

	public Animation(boolean skipFirst, int frameSkip, BufferedImage[] images) {
		this.frameSkip = frameSkip;
		this.skipFirst = skipFirst;
		frames = images;
		currentFrame = images[0];
	}

	long lastTime = System.currentTimeMillis();

	public BufferedImage animate() {

		long now = System.currentTimeMillis();
		if ((now - lastTime) / 16 >= frameSkip) {
			frameIndex++;
			if (frameIndex >= frames.length) {
				if (skipFirst)
					frameIndex = 1;
				else
					frameIndex = 0;
			}
			currentFrame = frames[frameIndex];
			lastTime = now;
		}

		return frames[frameIndex];

	}

	public void reset() {
		frameIndex = 0;
		currentFrame = frames[0];
	}

}
