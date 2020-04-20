package kingery.game.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import kingery.game.entities.npcs.Lumberjack;

public class Assets {

	public static SpriteSheet sheet = new SpriteSheet("spriteSheet");
	public static SpriteSheet buildings = new SpriteSheet("buildings");
	
	// Entities
	public static BufferedImage PLAYER = sheet.getImage(144, 568, 16, 32);
	public static Animation PLAYER_UP = new Animation(true, 10, sheet.getAnimation(256, 568, 16, 32, 3));
	public static Animation PLAYER_HORIZONTAL = new Animation(true, 10, sheet.getAnimation(0, 568, 16, 32, 8));
	public static Animation PLAYER_DOWN = new Animation(true, 10, sheet.getAnimation(144, 568, 16, 32, 7));
	
	public static BufferedImage SHEEP_GENERIC = sheet.getImage(0, 504, 64, 32);
	private static BufferedImage LUMBERJACK1 = sheet.getImage(0, 448, 16, 32);

	public static BufferedImage getLumberjack(int a) {

		System.out.println(LUMBERJACK1.getWidth());

		BufferedImage LUMBERJACK = deepCopy(LUMBERJACK1);

		for (int y = 0; y < LUMBERJACK.getHeight(); y++) {
			for (int x = 0; x < LUMBERJACK.getWidth(); x++) {

				switch (a) {
				case Lumberjack.LUMBERJACK_FRIEND:
					if (LUMBERJACK.getRGB(x, y) == 0xff7f7f7f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.RED);
					}
					if (LUMBERJACK.getRGB(x, y) == 0xff3f3f3f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.BLUE);
					}

				case Lumberjack.LUMBERJACK_NUETRAL:
					if (LUMBERJACK.getRGB(x, y) == 0xff7f7f7f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.PURPLE);
					}
					if (LUMBERJACK.getRGB(x, y) == 0xff3f3f3f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.BLUE);
					}
				case Lumberjack.LUMBERJACK_VENDOR:
					if (LUMBERJACK.getRGB(x, y) == 0xff7f7f7f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.GREEN);
					}
					if (LUMBERJACK.getRGB(x, y) == 0xff3f3f3f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.BLUE);
					}
				}
			}
		}

		return LUMBERJACK;
	}

	public static BufferedImage[] treeImages = {

			sheet.getImage(0, 536, 8, 16), sheet.getImage(8, 520, 16, 32),
			sheet.getImage(24, 520, 16, 32)

	};

	public static BufferedImage NPC_HERMIT = sheet.getImage(0, 552, 8, 8);
	// Building
	public static BufferedImage BLDG_CABIN = sheet.getImg(0, 0, 96, 80);

	// Items
	public static BufferedImage WOOD = sheet.getImage(0, 96, 16, 7);
	public static BufferedImage MONEY = sheet.getImage(768, 0, 32, 16);

	// Inventory
	public static BufferedImage INVENTORY = sheet.getImage(63, 0, 34, 34);

	// Holy shit this is the holy grail

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
