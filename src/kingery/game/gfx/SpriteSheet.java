package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(String file) {

		try {
			sheet = ImageIO.read(new File("res/Images/" + file + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public BufferedImage[] getAnimation(int startX, int startY, int cellW, int cellH, int numFrames) {

		BufferedImage[] frames = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++) {

			frames[i] = sheet.getSubimage(startX + (i * cellW), startY, cellW, cellH);

		}

		return frames;
	}
	

	public BufferedImage getImage(int x, int y, int w, int h) {

		return Assets.deepCopy(sheet.getSubimage(x, y, w, h));

	}

	public BufferedImage getImg(int x, int y, int w, int h) {

		return Assets.deepCopy(sheet.getSubimage(x, y, w, h));

	}

}
