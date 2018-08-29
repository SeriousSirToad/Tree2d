package kingery.game.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DayCycle {

	static int dayIndex = 1;
	
	public static BufferedImage cycleDay(BufferedImage image){
		
		BufferedImage image2 = image;
		
		if(dayIndex == 1){
			
			for (int y = 0; y < image2.getHeight(); y++) {

				for (int x = 0; x < image2.getWidth(); x++) {

					Color c = new Color(image2.getRGB(x, y));
					image2.setRGB(x, y, c.darker().getRGB());

				}

			}
			
		}
		
		return image2;
		
	}
	
}
