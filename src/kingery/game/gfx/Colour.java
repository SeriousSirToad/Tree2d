package kingery.game.gfx;

import java.awt.image.BufferedImage;

public class Colour {

	public static BufferedImage fixYoSelf(int[] colors, int[] newColors, BufferedImage Image) {

		BufferedImage image = Assets.deepCopy(Image);
		
		for (int i = 0; i < colors.length; i++) {
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					if (image.getRGB(x, y) == colors[i]) {
						image.setRGB(x, y, newColors[i]);
					}
				}
			}
		}
		return image;

	}

}