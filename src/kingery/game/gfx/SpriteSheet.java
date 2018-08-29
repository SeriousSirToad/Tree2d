package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	
	private static BufferedImage spriteSheet;
	public SpriteSheet(String file) {
		
		try {
			spriteSheet = ImageIO.read(new File("res/Images/" + file + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static BufferedImage getImage(int x, int y, int w, int h) {
		
		return spriteSheet.getSubimage(x, y, w, h);
		
	}
	
}
