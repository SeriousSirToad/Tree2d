package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private static BufferedImage spriteSheet;
	private static BufferedImage spriteSheetB;

	public SpriteSheet(String file, String file2) {

		try {
			spriteSheet = ImageIO.read(new File("res/Images/" + file + ".png"));
			spriteSheetB = ImageIO.read(new File("res/Images/" + file2 + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static BufferedImage getImage(int x, int y, int w, int h) {

		return spriteSheet.getSubimage(x, y, w, h);

	}

	public static BufferedImage getImg(int x, int y, int w, int h) {

		return spriteSheetB.getSubimage(x, y, w, h);

	}

}
